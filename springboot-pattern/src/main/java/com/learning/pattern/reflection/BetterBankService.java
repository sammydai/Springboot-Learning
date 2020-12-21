package com.learning.pattern.reflection;

import com.learning.pattern.annotation.BankAPI;
import com.learning.pattern.annotation.BankAPIField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Package: com.learning.pattern.reflection
 * @Description: BetterBankService
 * @Author: Sammy
 * @Date: 2020/12/21 10:05
 */
@Slf4j
public class BetterBankService {

	public static String createUser(String name, String identity, String mobile, int age) throws IOException {
		CreateUserAPI createUserAPI = new CreateUserAPI();
		createUserAPI.setName(name);
		createUserAPI.setIdentity(identity);
		createUserAPI.setMobile(mobile);
		createUserAPI.setAge(age);
		return remoteCall(createUserAPI);
	}

	public static String pay(long userId, BigDecimal amount) throws IOException {
		PayAPI payAPI = new PayAPI();
		payAPI.setUserId(userId);
		payAPI.setAmount(amount);
		return remoteCall(payAPI);
	}


	private static String remoteCall(AbstractAPI api) throws IOException {
		BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
		bankAPI.url();
		StringBuilder stringBuilder = new StringBuilder();
		Arrays.stream(api.getClass().getDeclaredFields())//获得所有字段
				.filter(field -> field.isAnnotationPresent(BankAPIField.class))//查找标记了注解的字段
				.sorted(Comparator.comparingInt(a->a.getAnnotation(BankAPIField.class).order()))//根据注解中的order对字段排序
				.peek(field -> field.setAccessible(true))
				.forEach(field -> {
					BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
					Object value = "";
					try {
						value = field.get(api); //反射获取字段值
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					switch (bankAPIField.type()) {
						case "S": {
							stringBuilder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
							break;
						}
						case "N": {
							stringBuilder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
							break;
						}
						case "M": {
							if (!(value instanceof BigDecimal))
								throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
							stringBuilder.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
							break;
						}
						default:
							break;
					}
				});
		stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
		String param = stringBuilder.toString();
		long begin = System.currentTimeMillis();
		String result = Request.Post("http://localhost:45678/reflection" + bankAPI.url())
				.bodyString(param, ContentType.APPLICATION_JSON)
				.execute().returnContent().asString();
		log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
		return result;
	}
}

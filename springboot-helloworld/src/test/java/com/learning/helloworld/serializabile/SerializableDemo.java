package com.learning.helloworld.serializabile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import javax.persistence.MappedSuperclass;
import javax.security.auth.kerberos.KerberosKey;
import java.rmi.MarshalledObject;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @Package: com.learning.helloworld.serializabile
 * @Description: Object Mapper Test
 * @Author: Sammy
 * @Date: 2020/12/14 13:27
 */

public class SerializableDemo {

	private static ObjectMapper om = new ObjectMapper();

	@Test
	public void toJson() throws JsonProcessingException {
		Car car = new Car("RED", "BMW");
		String carJson = om.writeValueAsString(car);
		System.out.println(carJson);
	}

	@Test
	public void toJavaObject() throws JsonProcessingException {
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Car car = om.readValue(json, Car.class);
		System.out.println(car.toString());
	}

	/**
	 * com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field
	 * DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false 忽略不属于Car对象属性的字段
	 * @throws JsonProcessingException
	 */
	@Test
	public void toJavaObjectWithMoreAttribute() throws JsonProcessingException {
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String jsonString
  			= "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";
		Car car = om.readValue(jsonString, Car.class);
		System.out.println(car.toString());
		//转换成Map类型
		String jsonMap = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Map<String, Object> map = om.readValue(jsonMap, new TypeReference<Map<String, Object>>() {});
		map.forEach((kk,vv)-> System.out.println(kk+"-->"+vv));
	}

	/**
	 * 设置自定义的序列化转换器执行
	 * @throws JsonProcessingException
	 */
	@Test
	public void customCarSer() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
		module.addSerializer(Car.class, new CustomCarSerializer());
		objectMapper.registerModule(module);
		Car car = new Car("yellow", "renault");
		System.out.println(objectMapper.writeValueAsString(car));
	}

	/**
	 * 自定义反序列化执行器
	 * @throws JsonProcessingException
	 */
	@Test
	public void customCarDes() throws JsonProcessingException {
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(Car.class, new CustomCarDeserializer());
		objectMapper.registerModule(module);
		Car car = objectMapper.readValue(json, Car.class);
		System.out.println(car.toString());
	}

	@Test
	public void customDate() throws JsonProcessingException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		om.setDateFormat(df);
		Car car = new Car("yellow", "renault");
		Request request = new Request(car, new Date());
		System.out.println("before request: "+request.toString());
		String carAsString = om.writeValueAsString(request);
		System.out.println("after request:"+carAsString);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {
	private String color;
    private String type;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Request {
    private Car car;
    private Date datePurchased;
}



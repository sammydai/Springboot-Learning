package com.learning.helloworld.config;

import com.learning.helloworld.domain.City;
import com.learning.helloworld.domain.People;
import com.learning.helloworld.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.helloworld.config
 * @Description: Test @ConditionalOnBean与@ConditionalOnClass
 * @Author: Sammy
 * @Date: 2020/12/6 13:05
 */
@Slf4j
@Configuration
public class BeanConfig {

	@Bean
	public City city(){
		City city = new City();
        city.setCityName("千岛湖");
        return city;
	}


	/**
     * 这里加了ConditionalOnBean注解，
	 * 就代表如果city存在才实例化people
	 * initMethod不要加括号
	 * myInit方法写在Domain上
     */
	@Bean(initMethod = "myInit",destroyMethod = "myDestory")
	@ConditionalOnBean(name = "city")
	public People people(City city) {
        //这里如果city实体没有成功注入 这里就会报空指针
        city.setCityCode(301701);
        return new People("小小", 3, city);
    }
}

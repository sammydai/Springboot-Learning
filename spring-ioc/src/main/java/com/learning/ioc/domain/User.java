package com.learning.ioc.domain;

import com.learning.ioc.enums.City;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Package: com.learning.ioc.domain
 * @Description: User Domain
 * @Author: Sammy
 * @Date: 2021/7/22 15:03
 */

public class User implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean {
	private Long id;

	private String name;

	private City city;

    private City[] workCities;

    private List<City> lifeCities;

	private Resource configFileLocation;

	private Company company;

	private Properties context;

	private String contextAsText;

	private transient String beanName;

	private transient ApplicationContext applicationContext;

	public String getBeanName() {
		return beanName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public City[] getWorkCities() {
		return workCities;
	}

	public void setWorkCities(City[] workCities) {
		this.workCities = workCities;
	}

	public List<City> getLifeCities() {
		return lifeCities;
	}

	public void setLifeCities(List<City> lifeCities) {
		this.lifeCities = lifeCities;
	}

	public Resource getConfigFileLocation() {
		return configFileLocation;
	}

	public void setConfigFileLocation(Resource configFileLocation) {
		this.configFileLocation = configFileLocation;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Properties getContext() {
		return context;
	}

	public void setContext(Properties context) {
		this.context = context;
	}

	public String getContextAsText() {
		return contextAsText;
	}

	public void setContextAsText(String contextAsText) {
		this.contextAsText = contextAsText;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city=" + city +
				", workCities=" + Arrays.toString(workCities) +
				", lifeCities=" + lifeCities +
				", configFileLocation=" + configFileLocation +
				", company=" + company +
				", context=" + context +
				", contextAsText='" + contextAsText + '\'' +
				'}';
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("1【User初始化过程】BeanNameAware.setBeanName()" + "--->BeanName:" + name);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("2【User初始化过程】BeanFactoryAware.setBeanFactory()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("3【User初始化过程】执行InitializingBean.afterPropertiesSet()");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// this.applicationContext = applicationContext;
		System.out.println("4【User初始化过程】ApplicationContextAware.setApplicationContext()");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean.destroy()");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("执行@PostConstruct方法");
	}

    @PreDestroy
    public void preDestroy() {
        System.out.println("执行@PreDestroy 方法！！！！");
    }

}
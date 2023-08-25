package com.learning.aop.processor;

import com.learning.aop.handler.ProxyBeanHandler;
import com.learning.aop.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 扫描有注解的类
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 14:23]
 */
public class RegisterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
			//判断beanDefinition是否是一个注解AnnotatedBeanDefinition
			if (beanDefinition instanceof AnnotatedBeanDefinition) {
				//取得beanDefinition上的所有注解
				AnnotationMetadata metadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
				Set<String> Annotations = metadata.getAnnotationTypes();
				for (String annotation : Annotations) {
					if (annotation.equals(ConfigurationUtil.AOP_POINTCUT_ANNOTATION)) {
						doScan((GenericBeanDefinition) beanDefinition);
					}
				}
			}
		}
	}

	/**
	 * 扫描所有注解方法
	 *
	 * @param beanDefinition bean定义
	 */
	private void doScan(GenericBeanDefinition beanDefinition) {
		try {
			String beanClassName = beanDefinition.getBeanClassName();
			Class<?> beanDefinitionClazz = Class.forName(beanClassName);
			Method[] methods = beanDefinitionClazz.getMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					String annotationName = annotation.annotationType().getName();
					if (annotationName.equals(ConfigurationUtil.BEFORE) || annotationName.equals(ConfigurationUtil.AFTER) ||
							annotationName.equals(ConfigurationUtil.AROUND)) {
						doScan(beanClassName, method, annotation);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 扫描出所有被代理的类
	 *
	 * @param beanClassName bean类名称
	 * @param method        方法
	 * @param annotation    注释
	 */
	private void doScan(String beanClassName, Method method, Annotation annotation) {
		ProxyBeanHandler proxyBeanHandler = new ProxyBeanHandler();
		proxyBeanHandler.setAnnotationName(annotation.annotationType().getName());
		proxyBeanHandler.setClassName(beanClassName);
		proxyBeanHandler.setMethodName(method.getName());
		//获取注解上的所有方法
		Method[] annotationMethods = annotation.annotationType().getDeclaredMethods();
		String packagePath = null;
		for (Method annotationMethod : annotationMethods) {
			if (annotationMethod.getName().equals("value")) {
				try {
					packagePath = (String) annotationMethod.invoke(annotation, null);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		if (!packagePath.isEmpty()) {
			String rootPath = this.getClass().getResource("/").getPath();
			String targetPackagePath = rootPath + packagePath.replace(".", "/");
			File file = new File(targetPackagePath);
			File[] fileList = file.listFiles();
			List<ProxyBeanHandler> proxyBeanHandlerList = null;
			for (File temp : fileList) {
				if (temp.isFile()) {
					String targetClass = packagePath + "." + temp.getName().replace(".class", "");
					proxyBeanHandlerList = ConfigurationUtil.clazzProxyBeanHandler.get(targetClass);
					if (proxyBeanHandlerList == null) {
						proxyBeanHandlerList = new Vector<ProxyBeanHandler>();
					}
					proxyBeanHandlerList.add(proxyBeanHandler);
					ConfigurationUtil.clazzProxyBeanHandler.put(targetClass, proxyBeanHandlerList);
				}
			}
		}
	}
}

package com.learning.aop.selector;

import com.learning.aop.annotation.MyScanner;
import com.learning.aop.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/25 14:38]
 */
@Slf4j
public class MySelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());
		// String[] basePackages = (String[]) annotationAttributes.get("basePackages");
		// ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		// TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);
		// scanner.addIncludeFilter(helloServiceFilter);
		// Set<String> classes = new HashSet<>();
		// for (String basePackage : basePackages) {
		// 	scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
		// }
		// return cl
		// asses.toArray(new String[classes.size()]);
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScanner.class.getName());
		String[] basePackages = (String[]) annotationAttributes.get("basePackages");
		System.out.println("======================================>");
		log.info("获取到的注解类型:{}", importingClassMetadata.getAnnotationTypes().toArray());
		if (basePackages == null || basePackages.length == 0) {//HelloServiceScan的basePackages默认为空数组
			String basePackage = null;
			try {
				basePackage = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			basePackages = new String[]{basePackage};
		}
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);
		scanner.addIncludeFilter(helloServiceFilter);
		Set<String> classes = new HashSet<>();
		for (String basePackage : basePackages) {
			scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
		}
		return classes.toArray(new String[classes.size()]);
	}
}

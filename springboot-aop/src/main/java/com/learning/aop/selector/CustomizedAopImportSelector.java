package com.learning.aop.selector;

import com.learning.aop.processor.RealizedAopBeanPostProcessor;
import com.learning.aop.processor.RegisterBeanFactoryPostProcessor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 16:27]
 */
public class CustomizedAopImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		return new String[]{
				RealizedAopBeanPostProcessor.class.getName(), RegisterBeanFactoryPostProcessor.class.getName()
		};
	}
}

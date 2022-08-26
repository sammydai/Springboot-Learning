package com.learning.importl.inter;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Package: com.learning.importl.inter
 * @Description: MyImportSelector
 * @Author: Sammy
 * @Date: 2022/7/31 15:22
 */

public class MyImportSelector implements ImportSelector {
	/**
	 * 3.2 实现了ImportSelector接口的类注入Spring容器的方式
	 *
	 * @param annotationMetadata
	 * @return
	 */
	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		return new String[]{"com.learning.importl.domain.NorMal"};
	}
}

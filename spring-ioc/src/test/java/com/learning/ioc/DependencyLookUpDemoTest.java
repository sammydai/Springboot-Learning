package com.learning.ioc;

import com.learning.ioc.annotation.Super;
import com.learning.ioc.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static java.lang.System.out;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/dependency-lookup-context.xml")
public class DependencyLookUpDemoTest {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private GenericApplicationContext context;

	// @Before
	// public void init() {
	// 	out.println("开始初始化容器");
	// 	beanFactory= new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
	// 	out.println("容器初始化完毕");
	// }

	@Test
	public void validAliasBean() {
		User user = beanFactory.getBean("user", User.class);
		User sammyuser = beanFactory.getBean("sammy-user", User.class);
		Assert.assertTrue("this is true", user == sammyuser);
	}

	@Test
	//method1:通过 BeanDefinitionBuilder
	public void validBeanDefinitionBuilder() {
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		BeanDefinition bd = bdb.addPropertyValue("id", 1L).addPropertyValue("name", "sammy")
				.getBeanDefinition();
		context.registerBeanDefinition("bdbeanssm", bd);
		out.println(beanFactory.getBean("bdbeanssm"));
	}

	@Test
	//method2:验证bean definition: AbstractBeanDefinition派生方法
	public void validAbstractBeanDefinition() {
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(User.class);
		MutablePropertyValues propertyvalues = new MutablePropertyValues();
		propertyvalues.addPropertyValue("id", 1L);
		propertyvalues.addPropertyValue("name", "sssssm");
		genericBeanDefinition.setPropertyValues(propertyvalues);
	}

	@Test
	public void test() {
		lookupByType(beanFactory);
		lookupCollectionByType(beanFactory);
		lookupByAnnotationType(beanFactory);
		// ClassPathXmlApplicationContext classPathXmlApplicationContext = (ClassPathXmlApplicationContext) context;
		// classPathXmlApplicationContext.registerShutdownHook();
		// Runtime.getRuntime().addShutdownHook(new Thread(DependencyLookUpDemoTest::run));
		// AbstractApplicationContext context = (AbstractApplicationContext) beanFactory;
		// //添加钩子函数
		// context.registerShutdownHook();
		// Runtime.getRuntime().addShutdownHook(new Thread(DependencyLookUpDemoTest::run));
	}


	private static void lookupByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
			out.println("查找标注 @Super 所有的 User 集合对象：" + userMap);
		}
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			out.println("查找到的所有的 User 集合对象：" + users);
		}
	}

	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		out.println("实时查找：" + user);
	}

	private static void run() {
		System.out.println("执行钩子函数");
	}
}
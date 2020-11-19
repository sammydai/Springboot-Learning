import com.learning.mybatis.MyBatisApplication;
import com.learning.mybatis.domain.DeptExtObject;
import com.learning.mybatis.domain.User;
import com.learning.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: Sammy
 * @Date: 2020/9/24 15:16
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisApplication.class)
public class TestMybatisCache {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	// @Autowired
	// private UserMapper userMapper;

	@Test
	public void testCache() {
        SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> userList = mapper.getUserList();
		userList.forEach(i-> System.out.println(i));
        System.out.println("========>第二次执行");
        List<User> userList2 = mapper.getUserList();
        userList2.forEach(i-> System.out.println(i));
		session.commit();
	}

	/**
	 * 每个SqlSession都有自己的缓存
	 * 不同的SqlSession对同一个sql执行相同的查询结果，会对一级缓存产生影响
	 *
	 * 如果同一个SqlSession中，执行同一个sql，第一次会从db中读取数据，其他sql都是从一级缓存中查询的
	 */
	@Test
	public void testCache2() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println("========>第1次执行");
		User user = userMapper.getUser(2);
		System.out.println(user);
		SqlSession session2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = session2.getMapper(UserMapper.class);
		System.out.println("========>第2次执行");
		User user1 = userMapper2.getUser(2);
		System.out.println(user1);
		session.commit();
	}

		/**
	 * 每个SqlSession都有自己的缓存
	 * 不同的SqlSession对同一个sql执行相同的查询结果，会对一级缓存产生影响
	 *
	 * 如果同一个SqlSession中，执行同一个sql，第一次会从db中读取数据，其他sql都是从一级缓存中查询的
	 */
	@Test
	public void testCache3() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println("========>第1次执行");
		User user = userMapper.getUser(2);
		System.out.println(user);
		//手动清理缓存
		session.clearCache();
		UserMapper userMapper2 = session.getMapper(UserMapper.class);
		System.out.println("========>第2次执行");
		User user1 = userMapper2.getUser(2);
		System.out.println(user1);
		session.commit();
	}

	/**
	 *在两次查询语句中使用插入，会对一级缓存进行刷新，会导致一级缓存失效。
	 */
	@Test
	public void testCache4() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println("========>第1次执行");
		User user = userMapper.getUser(2);
		System.out.println(user);
		System.out.println("========>执行insert语句");
		//加入新增语句
		User usertepm = new User("logdog",2,"usa","it",new Date());
		int i = userMapper.insertUser(usertepm);
		UserMapper userMapper2 = session.getMapper(UserMapper.class);
		System.out.println("========>第2次执行");
		User user1 = userMapper2.getUser(2);
		System.out.println(user1);
		session.commit();
	}

	@Test
	public void testLazy() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println("========>第1次执行");
		List<DeptExtObject> deptFullInfo = userMapper.getDeptFullInfo();
		for (DeptExtObject deptExtObject : deptFullInfo) {
			System.out.println(deptExtObject.getUserList());
		}
	}

}

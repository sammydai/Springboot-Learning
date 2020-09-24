import ch.qos.logback.core.net.SyslogOutputStream;
import com.dwt.springbootmybatis.MyBatisApplication;
import com.dwt.springbootmybatis.domain.User;
import com.dwt.springbootmybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.resources.ga.LocaleNames_ga;

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

	@Test
	public void testCache2() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println("========>第1次执行");
		User user = userMapper.getUser(2);
		System.out.println(user);
		System.out.println("========>第2次执行");
		User user1 = userMapper.getUser(2);
		System.out.println(user1);
		session.commit();
	}
}

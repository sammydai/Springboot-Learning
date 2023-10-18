package com.learning.helloworld.controller;

import com.learning.door.annotation.DoDoor;
import com.learning.door.aspect.DoJoinPoint;
import com.learning.helloworld.filter.MyHttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: TestController
 * @Author: Sammy
 * @Date: 2020/12/6 18:58
 */

@RestController
public class TestController {

	@Autowired
	private ApplicationContext applicationContext;


	@GetMapping("addSession")
	public String addSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("name", "haixiang");
		return "当前在线人数" + session.getServletContext().getAttribute("sessionCount") + "人";
	}

	@GetMapping("removeSession")
	public String removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "当前在线人数" + session.getServletContext().getAttribute("sessionCount") + "人";
	}

	@GetMapping("online")
	public String online() {
		return "当前在线人数" + MyHttpSessionListener.userCount.get() + "人";
	}

	@DoDoor(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public Map queryUserInfo(@RequestParam String userId) {
		Map<String, DoJoinPoint> beansOfType = applicationContext.getBeansOfType(DoJoinPoint.class);
		Map resultMap = new HashMap<>();
		resultMap.put("虫虫:" + userId, "天津市南开区旮旯胡同100号");
		return resultMap;
	}

}

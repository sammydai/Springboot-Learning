package com.learning.helloworld.controller;

import com.learning.helloworld.filter.MyHttpSessionListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: TestController
 * @Author: Sammy
 * @Date: 2020/12/6 18:58
 */

@RestController
public class TestController {
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
}

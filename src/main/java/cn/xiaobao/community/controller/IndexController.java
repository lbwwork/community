package cn.xiaobao.community.controller;

import cn.xiaobao.community.entity.User;
import cn.xiaobao.community.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 打开主页面的控制器
 */
@Controller
public class IndexController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/")
	public String hello(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					String token = cookie.getValue();
					User user = userMapper.findByToken(token);
					if (user != null) {
						request.getSession().setAttribute("user", user);
					}
					break;
				}

			}
		}
		return "index";
	}
}

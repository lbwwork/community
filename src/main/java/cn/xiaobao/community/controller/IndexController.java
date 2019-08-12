package cn.xiaobao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 打开主页面的控制器
 */
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String hello() {
		return "index";
	}
}

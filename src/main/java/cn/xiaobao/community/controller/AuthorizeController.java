package cn.xiaobao.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xiaobao.community.dto.AccessTokenDTO;
import cn.xiaobao.community.dto.GithubUser;
import cn.xiaobao.community.provider.GithubProvider;

/**
 * Github登录的控制器
 */
@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@RequestMapping("callback")
	public String callback(String code,String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setRedirect_url("http://localhost:8888/callback");
		accessTokenDTO.setClient_id("4dbaa45bb8b1e66a024e");
		accessTokenDTO.setClient_secret("06790b3a70c146c74ce418638ba8b5242af5a204");
		String token = githubProvider.gitAccessToken(accessTokenDTO);
		GithubUser user = githubProvider.getUser(token);
		System.out.println(user.getBio());
		System.out.println(user.getId());
		System.out.println(user.getName());
		return "index";
	}
	

}

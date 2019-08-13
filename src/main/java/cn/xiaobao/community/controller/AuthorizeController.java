package cn.xiaobao.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xiaobao.community.dto.AccessTokenDTO;
import cn.xiaobao.community.dto.GithubUser;
import cn.xiaobao.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Github登录的控制器
 */
@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Value("${github.Redirect_url}")
	private String redirectUrl;
    @Value("${github.client_id}")
    protected String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
	@RequestMapping("callback")
    public String callback(String code, String state, HttpServletRequest request) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
        System.err.println(clientId);
		accessTokenDTO.setRedirect_url(redirectUrl);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		String token = githubProvider.gitAccessToken(accessTokenDTO);
		GithubUser user = githubProvider.getUser(token);
		if (user!=null){
		    //登录成功，记录Session和Cookie
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
	}
	

}

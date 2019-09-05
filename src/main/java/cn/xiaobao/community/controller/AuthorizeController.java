package cn.xiaobao.community.controller;

import cn.xiaobao.community.entity.User;
import cn.xiaobao.community.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xiaobao.community.dto.AccessTokenDTO;
import cn.xiaobao.community.dto.GithubUser;
import cn.xiaobao.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * Github登录的控制器
 */
@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;
	@Autowired
	private GithubProvider githubProvider;
	@Value("${github.Redirect_url}")
	private String redirectUrl;
    @Value("${github.client_id}")
    protected String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
	@RequestMapping("callback")
    public String callback(String code, String state, HttpServletRequest request, HttpServletResponse response) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setRedirect_url(redirectUrl);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		String accessToken = githubProvider.gitAccessToken(accessTokenDTO);
		GithubUser githubUseruser = githubProvider.getUser(accessToken);
		if (githubUseruser!=null){
			//将用户数据保存到数据库中
            User user = new User();
            user.setAccountId(String.valueOf(githubUseruser.getId()));
            user.setName(githubUseruser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
		    //登录成功，记录Session和Cookie
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
	}
	

}

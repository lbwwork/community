package cn.xiaobao.community.controller;

import cn.xiaobao.community.entity.Question;
import cn.xiaobao.community.entity.User;
import cn.xiaobao.community.mappers.QuesstionMapper;
import cn.xiaobao.community.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("publish")
    public String publish(){
        return "publish";
    }

    @RequestMapping("publish")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        quesstionMapper.create(question);
        return "redirect:/";
    }
}

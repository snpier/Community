package com.example.forum.controller;

import com.example.forum.dto.AccessTokenDTO;
import com.example.forum.dto.GithubUser;
import com.example.forum.mapper.UserMapper;
import com.example.forum.model.User;
import com.example.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.client_secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null){
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtMofifed(user.getGmtCreate());
            userMapper.inster(user);
            response.addCookie(new Cookie("token",token));
            //登录成功，写入cookies 和 session
//            request.getSession().setAttribute("user",githubUser);
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtMofifed(user.getGmtCreate());
            userMapper.inster(user);
            //登录成功，写入cookies 和 session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        } else {
            //登录失败，重新登陆
            return "redirect:/";
        }
    }
}

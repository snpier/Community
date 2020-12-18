package com.example.forum.controller;

import com.example.forum.mapper.UserMapper;
import com.example.forum.model.User;
import com.example.forum.service.QusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QusetionService qusetionService;

    @GetMapping("/profile/{action}")
    public String PersonalCenter(HttpServletRequest request,
                                 @PathVariable(name = "action") String action,
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size,
                                 Model model) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user==null){
            return "redirect:/";
        }

        if ("question".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }
        if ("collection".equals(action)) {
            model.addAttribute("section", "revert");
            model.addAttribute("sectionName", "最新回复");
        }
        if ("collection".equals(action)) {
            model.addAttribute("section", "collection");
            model.addAttribute("sectionName", "我的收藏");
        }
        if ("topics".equals(action)) {
            model.addAttribute("section", "topics");
            model.addAttribute("sectionName", "我关注的话题");
        }
        qusetionService.list(user.getId(),page,size);
        return "profile";
    }
}

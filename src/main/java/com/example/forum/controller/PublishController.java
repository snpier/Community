package com.example.forum.controller;

 import com.example.forum.mapper.QuestionMapper;
import com.example.forum.mapper.UserMapper;
import com.example.forum.model.Question;
import com.example.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired(required=false)
    private QuestionMapper questionMapper;

    @Autowired(required=false)
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "content",required = false) String content,
            @RequestParam(value = "tags",required = false) String tags,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("content",content);
        model.addAttribute("tags",tags);

        if (title == null || title == ""){
            model.addAttribute("error","请填写问题标题~~~");
            return "publish";
        }
        if (content == null || content == ""){
            model.addAttribute("error","请填写问题内容~~~");
            return "publish";
        }
        if (tags == null || tags == ""){
            model.addAttribute("error","请填写问题标签~~~");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        if (user == null){
            model.addAttribute("error","请先登录后在发起提问~~~");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTags(tags);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModifed(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}

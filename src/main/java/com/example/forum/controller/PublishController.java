package com.example.forum.controller;

import com.example.forum.dto.QuestionDTO;
import com.example.forum.model.Question;
import com.example.forum.model.User;
import com.example.forum.service.QusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QusetionService qusetionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDTO question = qusetionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("content",question.getContent());
        model.addAttribute("tags",question.getTags());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "tags") String tags,
            @RequestParam(value = "id") Integer id,
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

        User user = (User)request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error","请先登录后在发起提问~~~");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTags(tags);
        question.setCreator(user.getId());
        question.setId(id);
        qusetionService.createOrUpdaye(question);
        return "redirect:/";
    }
}

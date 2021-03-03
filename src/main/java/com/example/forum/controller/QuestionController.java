package com.example.forum.controller;

import com.example.forum.dto.QuestionDTO;
import com.example.forum.service.QusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QusetionService qusetionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = qusetionService.getById(id);
        qusetionService.incView(id);
        model.addAttribute("question","questionDTO");
        return "question";
    }
}

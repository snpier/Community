package com.example.forum.advice;

import com.example.forum.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeErrorHandler extends RuntimeException{
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        } else {
            model.addAttribute("message","服务器累坏了，要不过一会再来试试吧");
        }
        return new ModelAndView("error");
    }
}

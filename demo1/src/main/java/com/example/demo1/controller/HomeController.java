package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @RequestMapping(value="/hello.do", method=RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "안녕하세요 SpringBoot!!";
    }

    @RequestMapping(value="/hi.do")
    public String hi(Model model) {

        String name = "홍길동";
        model.addAttribute("name", name);

        return "hi";
    }


    @RequestMapping(value="/bye.do")
    public String bye(Model model) {

        String name = "홍길동";
        model.addAttribute("name", name);

        return "bye";
    }

    
}

package com.github.myoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping({"/","login"})
    public String login(){
        return "login";
    }

    @GetMapping("/toRegist")
    public String toRegist(){
        return "regist";
    }
    @GetMapping("/toSave")
    public String toSave(){
        return "addEmp";
    }
}

package com.fh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("userlogin")
    public String login(){
        return "login";
    }

    @RequestMapping("level1")
    public String level1(){
        return "level1/level1";
    }

    @RequestMapping("level2")
    public String level2(){
        return "level2/level2";
    }

    @RequestMapping("level3")
    public String level3(){
        return "level3/level3";
    }
}

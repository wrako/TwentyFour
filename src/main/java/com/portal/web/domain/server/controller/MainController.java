package com.portal.web.domain.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/start")
    public String index() {
        return "index";
    }

    @RequestMapping("/comments")
    public String comment() {
        return "comment";
    }

    @RequestMapping("/reg")
    public String reg() {return "register";}

    @RequestMapping("/login")
    public String test() {return "login";}

    @RequestMapping("/rating")
    public String rating() {return "rating";}

    @RequestMapping("/score")
    public String score() {return "score";}

}

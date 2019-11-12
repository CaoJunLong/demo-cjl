package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("toindex")
    public String toindex() {
        return "index";
    }

    @RequestMapping("tousershow")
    public String tousershow() {
        return "usershow";
    }

    @RequestMapping("toroleshow")
    public String toroleshow() {
        return "roleshow";
    }

    @RequestMapping("totreeshow")
    public String totreeshow() {
        return "treeshow";
    }

    @RequestMapping("tologin")
    public String tologin() {
        return "login";
    }
}

package com.jk.controller;

import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Treecontroller {

    @Autowired
    private TreeService treeService;

    @RequestMapping("queryTreeList")
    @ResponseBody
    public List<Tree> queryTreeList(HttpSession session) {
        /*return treeService.queryTreeList();*/
        User u = (User) session.getAttribute("user");
        return treeService.queryuserTree(-1, u.getUserId());
    }
}

package com.jk.controller;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //查询用户
    @RequestMapping("queryUserList")
    @ResponseBody
    public List<User> queryUserList() {
        return userService.queryUserList();
    }

    //删除用户
    @RequestMapping("del")
    @ResponseBody
    public void del(Integer id) {
        userService.del(id);
    }

    //新增用户
    @RequestMapping("adduser")
    @ResponseBody
    public void adduser(User user) {
        userService.adduser(user);
    }

    //根据Id查询一条用户
    @RequestMapping("updatebyid")
    @ResponseBody
    public User updatebyid(Integer id) {
        User user = userService.updatebyid(id);
        return user;
    }


    //查询角色
    @RequestMapping("queryRoleList")
    @ResponseBody
    public List<Role> queryRoleList() {
        return userService.queryRoleList();
    }

    //删除角色
    @RequestMapping("delrole")
    @ResponseBody
    public void delrole(Integer id) {
        userService.delrole(id);
    }

    //新增角色
    @RequestMapping("addrole")
    @ResponseBody
    public void addrole(Role role) {
        userService.addrole(role);
    }

    //根据Id查询一个角色
    @RequestMapping("updaterolebyid")
    @ResponseBody
    public Role updaterolebyid(Integer id) {
        Role role = userService.updaterolebyid(id);
        return role;
    }


    //权限

    @RequestMapping("queryMenuList")
    @ResponseBody
    public List<Tree> queryMenuList() {
        return userService.queryMenuList();
    }

    //新增
    @RequestMapping("addmenu")
    @ResponseBody
    public void addmenu(Tree tree) {
        userService.addmenu(tree);
    }

    //删除
    @RequestMapping("delmenu")
    @ResponseBody
    public void delmenu(Integer id) {
        userService.delmenu(id);
    }

    //修改：查询一条数据
    @RequestMapping("updatemenubyid")
    @ResponseBody
    public Tree updatemenubyid(Integer id) {
        return userService.updatemenubyid(id);
    }

    @RequestMapping("queryTree")
    @ResponseBody
    public List<Tree> queryTree() {
        return userService.queryTree();

    }


    //用户附角色
    @RequestMapping("toEditUserRole")
    public String toEditUserRole(Integer uid, Model model) {
        System.out.println(111);

        List<Role> rolelist = userService.queryuserrole(uid);
        model.addAttribute("rolelist", rolelist);
        return "userrole";
    }

    //附角色
    @RequestMapping("saveUserRole")
    @ResponseBody
    public String saveUserRole(Integer uid, String rids) {
        userService.saveUserRole(uid, rids);
        return "操作成功";

    }


    @RequestMapping("queryTrees")
    @ResponseBody
    public List<Tree> queryTrees() {
        return userService.queryTree();

    }

    @RequestMapping("queryRoleMenu")
    @ResponseBody
    public List<Tree> queryRoleMenu(int roleId) {
        return userService.queryRoleMenu(-1, roleId);
    }

    //登陆
    @RequestMapping("login")
    @ResponseBody
    public String login(String code, HttpSession session, User user) {
        User u = userService.queryusername(user.getUserName());
        if (u == null) {
            return "用户不存在！";
        }
        if (!u.getPassWord().equals(user.getPassWord())) {
            return "密码错误！";
        }
        session.setAttribute("user", u);
        return "登录成功！";

    }

    @RequestMapping("addRoleMenu")
    @ResponseBody
    public void addRoleMenu(Integer roleId, String menuIds) {
        userService.addRoleMenu(roleId, menuIds);
    }
}

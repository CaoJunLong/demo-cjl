package com.jk.service;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;

import java.util.List;

public interface UserService {
    List<User> queryUserList();

    void del(Integer id);

    void adduser(User user);

    User updatebyid(Integer id);

    List<Role> queryRoleList();

    void delrole(Integer id);

    void addrole(Role role);

    Role updaterolebyid(Integer id);

    List<Tree> queryMenuList();

    void addmenu(Tree tree);

    void delmenu(Integer id);

    Tree updatemenubyid(Integer id);

    List<Tree> queryTree();

    List<Role> queryuserrole(Integer uid);

    void saveUserRole(Integer uid, String rids);


    List<Tree> queryRoleMenu(int pid, int roleId);

    User queryusername(String userName);

    void addRoleMenu(Integer roleId, String menuIds);
}

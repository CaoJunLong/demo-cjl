package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //用户
    @Override
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    @Override
    public void del(Integer id) {
        userMapper.del(id);
    }

    @Override
    public void adduser(User user) {
        if (user.getUserId() == null) {
            userMapper.adduser(user);
        } else {
            userMapper.updateuser(user);
        }


    }

    @Override
    public User updatebyid(Integer id) {
        return userMapper.updatebyid(id);
    }


    //角色
    @Override
    public List<Role> queryRoleList() {
        return userMapper.queryRoleList();
    }

    @Override
    public void delrole(Integer id) {
        userMapper.delrole(id);
    }

    @Override
    public void addrole(Role role) {
        if (role.getRoleId() == null) {
            userMapper.addrole(role);
        } else {
            userMapper.updaterole(role);
        }
    }

    @Override
    public Role updaterolebyid(Integer id) {
        return userMapper.updaterolebyid(id);
    }


    //权限
    @Override
    public List<Tree> queryMenuList() {
        return userMapper.queryMenuList();
    }

    @Override
    public void addmenu(Tree tree) {
        if (tree.getId() == null) {
            userMapper.addmenu(tree);
        } else {
            userMapper.updatetree(tree);
        }
    }

    @Override
    public void delmenu(Integer id) {
        userMapper.delmenu(id);
    }

    @Override
    public Tree updatemenubyid(Integer id) {
        return userMapper.updatemenubyid(id);
    }

    @Override
    public List<Tree> queryTree() {
        int pid = -1;
        List<Tree> list = findNodes(pid);
        return list;
    }

    private List<Tree> findNodes(int pid) {
        List<Tree> list = userMapper.findOrg(pid);
        for (Tree org : list) {
            Integer id = org.getId();
            List<Tree> nodes = findNodes(id);
            org.setChildren(nodes);
        }
        return list;
    }

    //用户附角色
    @Override
    public List<Role> queryuserrole(Integer uid) {
        List<Role> rolelist1 = userMapper.queryroleList();

        List<Integer> rolelist2 = userMapper.queryrole(uid);
        for (int i = 0; i < rolelist1.size(); i++) {
            for (int j = 0; j < rolelist2.size(); j++) {
                if (rolelist1.get(i).getRoleId() == rolelist2.get(j)) {
                    rolelist1.get(i).setStatus("checked");
                }
            }
        }
        return rolelist1;
    }


    @Override
    public void saveUserRole(Integer uid, String rids) {
        userMapper.deletecenterbyid(uid);
        String[] ridArr = rids.split(",");
        userMapper.saveUserRole(uid, ridArr);
    }


    @Override
    public List<Tree> queryRoleMenu(int pid, int roleId) {
        List<Tree> treeList1 = userMapper.querytree(pid);

        List<Integer> rolemenuList = userMapper.queryTreeByid(roleId);
        if (treeList1 != null && treeList1.size() > 0) {
            for (int i = 0; i < treeList1.size(); i++) {
                List<Tree> treeList2 = queryRoleMenu(treeList1.get(i).getId(), roleId);
                treeList1.get(i).setChildren(treeList2);

                for (int j = 0; j < rolemenuList.size(); j++) {
                    if (treeList2.size() <= 0 && treeList1.get(i).getId() == rolemenuList.get(j)) {
                        treeList1.get(i).setChecked(true);
                    }
                }
            }
        }

        return treeList1;
    }


    public void addRoleMenu(Integer roleId, String menuIds) {
        userMapper.deleterolemenu(roleId);

        userMapper.addRoleMenu(roleId, menuIds.split(","));
    }

    @Override
    public User queryusername(String userName) {
        return userMapper.queryusername(userName);
    }


}


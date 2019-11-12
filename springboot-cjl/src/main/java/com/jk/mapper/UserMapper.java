package com.jk.mapper;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> queryUserList();

    void del(Integer id);

    void adduser(User user);

    User updatebyid(Integer id);

    void updateuser(User user);

    List<Role> queryRoleList();

    void delrole(Integer id);

    void addrole(Role role);

    void updaterole(Role role);

    Role updaterolebyid(Integer id);

    List<Tree> queryMenuList();

    void addmenu(Tree tree);

    void updatetree(Tree tree);

    void delmenu(Integer id);

    Tree updatemenubyid(Integer id);

    List<Tree> findOrg(int pid);

    List<Role> queryroleList();

    List<Integer> queryrole(Integer uid);

    void deletecenterbyid(Integer uid);

    void saveUserRole(@Param("uid") Integer uid, @Param("ridArr") String[] ridArr);

    List<Tree> querytree(int pid);

    List<Integer> queryTreeByid(int roleId);

    void deleterolemenu(Integer roleId);

    void addRoleMenu(@Param("roleId") Integer roleId, @Param("tids") String[] split);

    User queryusername(String userName);
}

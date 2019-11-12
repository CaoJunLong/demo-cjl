package com.jk.mapper;

import com.jk.model.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeMapper {
    List<Tree> findOrg(int pid);

    List<Tree> queryUserMenuTree(@Param("pid") int pid, @Param("userId") Integer userId);
}

package com.jk.service;

import com.jk.model.Tree;

import java.util.List;

public interface TreeService {
    List<Tree> queryTreeList();

    List<Tree> queryuserTree(int pid, Integer userId);
}

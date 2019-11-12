package com.jk.service;

import com.jk.mapper.TreeMapper;
import com.jk.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treemapper;

    @Override
    public List<Tree> queryTreeList() {
        int pid = -1;
        List<Tree> list = findNodes(pid);
        return list;
    }

    private List<Tree> findNodes(int pid) {
        List<Tree> list = treemapper.findOrg(pid);
        for (Tree org : list) {
            Integer id = org.getId();
            List<Tree> nodes = findNodes(id);
            org.setChildren(nodes);
        }
        return list;
    }

    @Override
    public List<Tree> queryuserTree(int pid, Integer userId) {
        List<Tree> tree = treemapper.queryUserMenuTree(pid, userId);
        if (tree != null && tree.size() > 0) {
            for (int i = 0; i < tree.size(); i++) {
                List<Tree> menus2 = queryuserTree(tree.get(i).getId(), userId);
                tree.get(i).setChildren(menus2);
            }
        }
        return tree;
    }
}

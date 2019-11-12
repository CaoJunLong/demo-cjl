package com.jk.mapper;

import com.jk.model.User;

public interface UserMapper {
    User queryUserByName(String userName);
}

package com.caychen.jwt.core.service;

import com.caychen.jwt.core.dto.UserDTO;
import com.caychen.jwt.core.entity.User;
import com.caychen.jwt.core.global.result.ResponseResult;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public interface IUserService {

    User findByUsername(String username);

    ResponseResult register(UserDTO userDTO);

    ResponseResult getUserList();

}

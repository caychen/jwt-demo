package com.caychen.jwt.core.service;

import com.caychen.jwt.core.entity.User;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public interface ITokenService {

    String generateToken(User userForBase);
}

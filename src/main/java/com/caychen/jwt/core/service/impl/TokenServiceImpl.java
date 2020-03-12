package com.caychen.jwt.core.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caychen.jwt.core.entity.User;
import com.caychen.jwt.core.service.ITokenService;
import org.springframework.stereotype.Service;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Override
    public String generateToken(User user) {
        return JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }
}

package com.caychen.jwt.core.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public class EncryUtil {

    public static String encry(String text){
        return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));
    }
}

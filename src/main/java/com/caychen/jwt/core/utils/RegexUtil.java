package com.caychen.jwt.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public class RegexUtil {

    public static boolean regex(Pattern pattern, String text){
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}

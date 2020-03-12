package com.caychen.jwt.core.constant;

import java.util.regex.Pattern;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public class Constant {
    public static final String ID_CARD_15_REGEX = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$";

    public static final String ID_CARD_18_REGEX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    public static final Pattern PATTERN15 = Pattern.compile(ID_CARD_15_REGEX);

    public static final Pattern PATTERN18 = Pattern.compile(ID_CARD_18_REGEX);
}

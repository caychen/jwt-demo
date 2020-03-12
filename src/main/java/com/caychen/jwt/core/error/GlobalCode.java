package com.caychen.jwt.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Getter
@AllArgsConstructor
public enum GlobalCode {
    SUCCESS("000000", "成功"),
    SYSTEM_INTERNAL_ERROR("500", "服务器内部错误"),
    INVALID_PARAM_ERROR("101", "参数无效"),
    METHOD_NOT_SUPPORT_ERROR("102", "请求方式不支持"),
    SQL_SYNTAX_ERROR("103", "SQL执行异常"),

    USER_NOT_EXISTS_ERROR("600", "用户不存在"),
    PASSWORD_NOT_CORRECT_ERROR("601", "用户密码不正确"),
    USER_REGISTER_ERROR("602", "用户注册异常"),
    USER_HAS_EXISTS_ERROR("603", "同名用户已存在，注册失败"),
    IDCARD_LENGTH_ERROR("604", "身份证号码长度不正确"),
    IDCARD_FORMAT_ERROR("605", "身份证号码格式不正确"),
    ;

    private String code;

    private String desc;

}

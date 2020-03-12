package com.caychen.jwt.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Data
public class UserDTO {

    @ApiModelProperty(value = "主键", hidden = true)
    private Long Id;

    @Length(max = 20, message = "用户名最大长度不超过20位")
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$", message = "密码强度不正确：必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间")
    @Length(min = 8, max = 20, message = "密码长度不正确，至少8位，至多20位")
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱不正确")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1]\\d{10}$", message = "手机号码不正确")//只要1开头就行
    @Length(min = 11, max = 11, message = "手机号码长度不正确")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    //15或者18位
    //15：/^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$/
    //18：/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
    @NotBlank(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号", required = true)
    private String idCard;

}

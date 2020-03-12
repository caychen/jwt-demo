package com.caychen.jwt.core.controller;

import com.caychen.jwt.core.annotation.TokenIgnore;
import com.caychen.jwt.core.dto.UserDTO;
import com.caychen.jwt.core.entity.User;
import com.caychen.jwt.core.error.GlobalCode;
import com.caychen.jwt.core.global.result.ResponseResult;
import com.caychen.jwt.core.service.ITokenService;
import com.caychen.jwt.core.service.IUserService;
import com.caychen.jwt.core.utils.EncryUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Caychen
 * @Date: 2020-03-10
 * @Describe:
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户相关Api")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    @TokenIgnore
    public ResponseResult login(@RequestBody UserDTO userDTO) {
        User userForBase = userService.findByUsername(userDTO.getUsername());
        if (userForBase == null) {
            return ResponseResult.error(GlobalCode.USER_NOT_EXISTS_ERROR);
        } else {

            String password = userDTO.getPassword();
            if (StringUtils.isNotBlank(password)) {
                password = EncryUtil.encry(password);

                if (!userForBase.getPassword().equals(password)) {
                    return ResponseResult.error(GlobalCode.PASSWORD_NOT_CORRECT_ERROR);
                } else {
                    String token = tokenService.generateToken(userForBase);
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("token", token);
                    return ResponseResult.success(result);
                }
            } else {
                return ResponseResult.error(GlobalCode.USER_NOT_EXISTS_ERROR);
            }

        }
    }

    @TokenIgnore
    @ApiOperation("用户申请账户")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody @Validated UserDTO userDTO,
                                   BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseResult.error(GlobalCode.INVALID_PARAM_ERROR, bindingResult.getAllErrors().get(0).getDefaultMessage());
            }

            return userService.register(userDTO);
        } catch (Exception e) {
            log.error("用户注册失败：", e);
            return ResponseResult.error(GlobalCode.USER_REGISTER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public ResponseResult getUserList() {
        return userService.getUserList();
    }

}

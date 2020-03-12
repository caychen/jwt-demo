package com.caychen.jwt.core.service.impl;

import com.caychen.jwt.core.constant.Constant;
import com.caychen.jwt.core.dao.IUserRepository;
import com.caychen.jwt.core.dto.UserDTO;
import com.caychen.jwt.core.entity.User;
import com.caychen.jwt.core.error.GlobalCode;
import com.caychen.jwt.core.global.result.ResponseResult;
import com.caychen.jwt.core.service.IUserService;
import com.caychen.jwt.core.utils.EncryUtil;
import com.caychen.jwt.core.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public ResponseResult register(UserDTO userDTO) {

        GlobalCode code = checkUserParam(userDTO);
        if (code != GlobalCode.SUCCESS) {
            return ResponseResult.error(code);
        }

        User dbUser = this.findByUsername(userDTO.getUsername());
        if (dbUser != null) {
            log.error("同名的账户已存在，注册失败");
            return ResponseResult.error(GlobalCode.USER_HAS_EXISTS_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        //密码加密
        user.setPassword(EncryUtil.encry(userDTO.getPassword()));
        user.setRegisterTime(new Date());
        user.setUpdateTime(new Date());
        userRepository.save(user);

        return ResponseResult.success();
    }

    @Override
    public ResponseResult getUserList() {
        return ResponseResult.success(userRepository.findUserList());
    }

    private GlobalCode checkUserParam(com.caychen.jwt.core.dto.UserDTO userDTO) {
        //身份证
        String idCard = userDTO.getIdCard();
        if (StringUtils.length(idCard) != 15 && StringUtils.length(idCard) != 18) {
            return GlobalCode.IDCARD_LENGTH_ERROR;
        }

        boolean bFlag = true;
        if (StringUtils.length(idCard) == 15) {
            bFlag = RegexUtil.regex(Constant.PATTERN15, idCard);
        } else if (StringUtils.length(idCard) == 18) {
            bFlag = RegexUtil.regex(Constant.PATTERN18, idCard);
        }
        if (!bFlag) {
            return GlobalCode.IDCARD_FORMAT_ERROR;
        }

        return GlobalCode.SUCCESS;
    }
}

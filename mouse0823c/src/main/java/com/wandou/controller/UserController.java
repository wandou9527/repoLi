package com.wandou.controller;

import com.alibaba.fastjson.JSON;
import com.wandou.annotation.XParam;
import com.wandou.enumeration.ReturnCodeEnum;
import com.wandou.model.dto.req.ReqLoginDTO;
import com.wandou.model.po.UserPO;
import com.wandou.model.vo.BaseRespVO;
import com.wandou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author liming
 * @date 2020-04-15
 * @description
 */

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseRespVO<String> login(@RequestBody @Validated ReqLoginDTO reqLoginDTO) {
        log.info("login req:{}", JSON.toJSONString(reqLoginDTO));
        String token = userService.login(reqLoginDTO);
        log.info("login resp: {}", token);
        if (StringUtils.isNotBlank(token)) {
            return BaseRespVO.success(token);
        } else {
            return BaseRespVO.error(ReturnCodeEnum.BAD_USERNAME_OR_PASSWORD);
        }
    }

    @GetMapping("/get_info_by_token")
    public BaseRespVO<UserPO> getInfoByToken(@XParam Long userId) {
        UserPO userPO = userService.getInfoById(userId);
        return BaseRespVO.success(userPO);
    }


}

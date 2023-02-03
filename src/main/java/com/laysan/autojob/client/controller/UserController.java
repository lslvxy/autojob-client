package com.laysan.autojob.client.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.laysan.autojob.client.constants.AccountType;
import com.laysan.autojob.client.dto.TypeDTO;
import com.laysan.autojob.client.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @GetMapping("menu")
    public MultiResponse<TypeDTO> menu(HttpServletRequest request) {
        AccountType[] values = AccountType.values();
        List<TypeDTO> list = Arrays.stream(values).map(at -> new TypeDTO(at.getCode(), at.getDesc(), at.getIcon())).collect(Collectors.toList());
        return MultiResponse.of(list);
    }

    @GetMapping("me")
    public SingleResponse<User> info(HttpServletRequest request) {
        User userId = getLoginUser(request);
        return SingleResponse.of((userId));
    }

    @PostMapping("me")
    public Response save(@RequestBody User user, HttpServletRequest request) {
        User userDb = getLoginUser(request);
        if (StrUtil.isBlank(user.getSctKey())) {
            userDb.setSctKey(null);
        } else {
            userDb.setSctKey(user.getSctKey());
        }
        userService.save(userDb);
        return Response.buildSuccess();
    }
}
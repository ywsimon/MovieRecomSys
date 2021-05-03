package com.movierecomsys.demo.controller;

import com.movierecomsys.demo.common.MyResponse;
import com.movierecomsys.demo.data.entity.UserEntity;
import com.movierecomsys.demo.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author YRC
 * @date 2021/4/20 10:53
 **/
@RestController(value = "/user")
public class AccountController {

    @Resource
    private UserService userService;
    @PostMapping("/api/login")
    public MyResponse login(@RequestBody UserEntity userEntity, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();
        String userid = userEntity.getUserid();
        String password = userEntity.getPassword();
        return MyResponse.ok(userService.login(sessionId, userid,password));
    }

    @PostMapping("/api/register")
    public MyResponse register(@RequestBody UserEntity userEntity, HttpServletRequest request, HttpServletResponse response){
        String userid = userEntity.getUserid();
        String password = userEntity.getPassword();
        String username = userEntity.getUsername();
        Integer status = userService.register(userid,password,username);
        return MyResponse.ok(status);
    }

    @PostMapping("api/usertags")
    public MyResponse setTags()
    {
        return MyResponse.ok(userService.setTags());
    }


}
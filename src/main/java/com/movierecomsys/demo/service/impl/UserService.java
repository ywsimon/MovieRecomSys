package com.movierecomsys.demo.service.impl;

import com.movierecomsys.demo.Repository.UserRepository;
import com.movierecomsys.demo.data.entity.UserEntity;
import com.movierecomsys.demo.redis.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author YRC
 * @date 2021/4/21 10:15
 **/

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private RedisApi redisApi;
    public Map<String, Object> login(String sessionId, String userId, String password){

        Map<String, Object> result = new HashMap<>(2, 1);
        if(userId==null || userId.equals("")){
            result.put("status",1);
            return result;
        }

        if(password == null || password.equals("")){
            result.put("status",2);
            return result;
        }

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if (!userEntity.isPresent())
        {
            result.put("status",1);
            return result;
        }

        if( !userEntity.get().getPassword() .equals(password))
        {
            result.put("status",2);
            return result;
        }
        System.out.println("login sucessful ...");
        redisApi.set(sessionId,userEntity.get().getUserid());
        System.out.println("login sucessful");

        result.put("status",0);
        result.put("username",userEntity.get().getUsername());
        return result;
    }

    public int register(String userId, String password, String username){

        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()){
            return 1;   //用户已存在
        }
        else{
            UserEntity userEntity1 = new UserEntity();
            userEntity1.setUserid(userId);
            userEntity1.setUsername(username);
            userEntity1.setPassword(password);
            userRepository.save(userEntity1);
        }
        return 0;
    }

    public int setTags( ){


        return 0;
    }

}

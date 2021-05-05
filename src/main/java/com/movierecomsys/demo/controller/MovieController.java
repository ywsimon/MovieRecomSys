package com.movierecomsys.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.movierecomsys.demo.common.MyResponse;
import com.movierecomsys.demo.service.impl.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YRC
 * @date 2021/4/27 15:26
 **/

@RestController
public class MovieController {
    @Resource
    MovieService movieService;
    @PostMapping("api/search")
    public MyResponse searchMovie(@RequestBody JSONObject param){
        String keys = param.getString("key");
        Integer num = param.getInteger("num");
        return MyResponse.ok(movieService.search(keys, num));
    }
    @PostMapping("api/top")
    public MyResponse highRatingMovie(){
        return MyResponse.ok(movieService.topRating());
    }

    @PostMapping("api/hot")
    public MyResponse HotMovie()
    {
        return MyResponse.ok(movieService.hot());
    }
    @PostMapping("api/recom")
    public MyResponse Recommend(){return  MyResponse.ok(movieService.recommend());}

    @PostMapping("api/classify")
    public  MyResponse MovieClassify(@RequestBody JSONObject param){
        String tag = param.getString("tag");
        Integer num = param.getInteger("num");
        return MyResponse.ok(movieService.movieClassify(tag,num));
    }
}

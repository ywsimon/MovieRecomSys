package com.movierecomsys.demo.service.impl;
import com.movierecomsys.demo.Repository.MovieRepository;
import com.movierecomsys.demo.Repository.TopRepository;
import com.movierecomsys.demo.data.entity.MovieEntity;
import com.movierecomsys.demo.data.entity.TopMovieEntity;
import com.movierecomsys.demo.redis.RedisApi;
import com.movierecomsys.demo.redis.RedisKeys;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author YRC
 * @date 2021/4/27 15:33
 **/
@Service
public class MovieService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private TopRepository topRepository;

    @Resource
    private RedisApi redisApi;

//    @Autowired
//    private MovieList movieList;

    public Map<String, Object> getMovie(String RedisKey) {
        return null;
    }

    //热门电影
    public Map<String, Object> hot()
    {
        String MovieCache = redisApi.get(RedisKeys.HOT_MOVIE);
        Map<String, Object> result = new HashMap<>(2, 1);
        List<MovieEntity> movieList;
        if (MovieCache == null) {
            movieList = movieRepository.getHotMovie();
            redisApi.set(RedisKeys.HOT_MOVIE, JSONObject.toJSONString(movieList));
        }
        else {
            movieList = JSONObject.parseArray(MovieCache, MovieEntity.class);
        }
        result.put("movieNum", movieList.size());
        result.put("movieList", movieList);
        return result;
    }

    // 高分电影
    public Map<String, Object> topRating() {
        String MovieCache = redisApi.get(RedisKeys.HIGH_MOVIE);
        Map<String, Object> result = new HashMap<>(2, 1);
        List<TopMovieEntity> movieList;
        if (MovieCache == null) {
            movieList = topRepository.findTopScore();
            redisApi.set(RedisKeys.HIGH_MOVIE, JSONObject.toJSONString(movieList));
        }
        else {
            movieList = JSONObject.parseArray(MovieCache, TopMovieEntity.class);
        }
        result.put("movieNum", movieList.size());
        result.put("movieList", movieList);
        return result;
    }
    //电影分类
    public Map<String, Object> movieClassify(String tag, Integer num )
    {
        Map<String, Object> result = new HashMap<>(2, 1);
        List<MovieEntity> movieList;
        movieList = movieRepository.findAllByTag(tag, num);
        result.put("movieNum", movieList.size());
        result.put("movieList", movieList);
        return result;
    }

    //电影搜索
    public Map<String, Object> search(String keys, int num)
    {
        Map<String, Object> result = new HashMap<>(2, 1);
        List<MovieEntity> movieList;
        movieList = movieRepository.findAllByName(keys, num);
        result.put("movieNum", movieList.size());
        result.put("movieList", movieList);
        return result;
    }
    //电影推荐
    public int recommend(){
        return 0;
    }
}

package com.movierecomsys.demo.Repository;
import com.movierecomsys.demo.data.entity.TopMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author YRC
 * @date 2021/5/3 17:05
 **/
public interface TopRepository extends JpaRepository<TopMovieEntity, String> {
    @Query(nativeQuery =true, value = "select *from topMovie ORDER BY mark DESC limit 12")
    List<TopMovieEntity> findTopScore();
}

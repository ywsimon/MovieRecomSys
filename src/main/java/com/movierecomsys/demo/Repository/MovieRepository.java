package com.movierecomsys.demo.Repository;

import com.movierecomsys.demo.data.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YRC
 * @date 2021/4/28 9:59
 **/
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    @Query(nativeQuery=true, value="select * from hotMovie")
    List<MovieEntity> getHotMovie();

    @Query(nativeQuery = true, value = "SELECT * FROM movie WHERE name LIKE CONCAT('%',?1,'%') limit ?2")
    List<MovieEntity> findAllByName(@Param("keys") String keys, @Param("total") int total);

    @Query(nativeQuery = true, value = "SELECT * FROM movie  WHERE type LIKE CONCAT('%',?1,'%') order  by rand() limit ?2")
    List<MovieEntity> findAllByTag(@Param("tag") String tag, @Param("total") int total);
}

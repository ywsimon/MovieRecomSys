package com.movierecomsys.demo.Repository;
import com.movierecomsys.demo.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YRC
 * @date 2021/4/21 14:49
 **/

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
//    @Query("SELECT u from  UserEntity u where u.id =: userId")
//    UserEntity findById(@Param("userId") String userId);
}

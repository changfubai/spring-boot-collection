package com.changfubai.springboot.database.jpa;

import com.changfubai.springboot.database.bean.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by changfubai on 2018/4/1
 */
//JpaRepository中定义了大量常用的操作方法，我们可以在自定义的接口中定义新的方法，也可以在实现接口后重写
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //可以忽略IDEA的提示
    @Query("from UserEntity u where u.name=:name")
    UserEntity findUser(@Param("name") String name);
}

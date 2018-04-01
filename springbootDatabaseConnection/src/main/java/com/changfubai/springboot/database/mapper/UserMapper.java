package com.changfubai.springboot.database.mapper;

import com.changfubai.springboot.database.bean.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by changfubai on 2018/4/1
 */
//IDEA不能自动识别bean，会有错误警告，不影响运行结果
// 加上该注解即可消除错误提示
@Repository
public interface UserMapper {
    @Insert("insert into users(username,password) values(#{uname},#{pwd})")
    public void add(UserBean userBean);

    @Select("select * from users")
    @Results({
            @Result(property = "uname", column = "username"),
            @Result(property = "pwd", column = "password")
    })
    public List<UserBean> getUsers();

    //这里的属性名和数据库表中的不相同，所以需要手动映射
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(property = "uname", column = "username"),
            @Result(property = "pwd", column = "password")
    })
    UserBean getUserByName(@Param("username") String name);
}

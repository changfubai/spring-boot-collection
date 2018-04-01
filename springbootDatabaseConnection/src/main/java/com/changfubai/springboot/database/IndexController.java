package com.changfubai.springboot.database;

import com.changfubai.springboot.database.bean.UserBean;
import com.changfubai.springboot.database.bean.UserEntity;
import com.changfubai.springboot.database.jdbc.UserService;
import com.changfubai.springboot.database.jpa.UserRepository;
import com.changfubai.springboot.database.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changfubai on 2018/4/1
 */
//只需要返回结果即可，不用做前端处理
@RestController
public class IndexController {
    //JdbcTempale 模板使用

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/jdbc/addUserBySql")
    public String addUserBySql() {
        for (int i = 0; i < 5; i++) {
            UserBean userBean = new UserBean("changfubai-jdbc-" + i,
                    "password-jdbc-" + i);
            userService.addUserBySql(userBean);
        }
        List<UserBean> users = userService.getUsersByQuery();
        StringBuffer bf = new StringBuffer("getUsersByQuery:-----");
        for (UserBean user : users) {
            bf.append(user.toString());
        }
        return bf.toString();
    }

    @GetMapping("/jdbc/addUserByPre")
    public String addUserByPre() {
        for (int i = 10; i < 15; i++) {
            UserBean userBean = new UserBean("changfubai-jdbc-" + i,
                    "password-jdbc-" + i);
            userService.addUserByPre(userBean);
        }
        List<UserBean> users = userService.getUsersByExcute();
        StringBuffer bf = new StringBuffer("getUsersByExcute:-----");
        for (UserBean user : users) {
            bf.append(user.toString());
        }
        return bf.toString();
    }

    //mybatis 模板使用
    @Autowired
    UserMapper userMapper;

    @GetMapping("/mybatis/addUser")
    public String addUser() {
        for (int i = 0; i < 5; i++) {
            UserBean bean = new UserBean("changfubai-mybatis-" + i, "pwd-mybatis-" + i);
            userMapper.add(bean);
        }
        List<UserBean> users = userMapper.getUsers();
        StringBuffer bf = new StringBuffer("mybatis-addUser:");
        for (UserBean user : users) {
            bf.append(user.toString());
        }
        return bf.toString();
    }

    @GetMapping("/mybatis/getUserByName")
    public String getUserByName() {
        UserBean userByName = userMapper.getUserByName("changfubai-mybatis-1");
        return userByName.toString();
    }

    //jpa 模板使用
    @Autowired
    UserRepository userRepository;

    @GetMapping("/jpa/addUserByJpa")
    public String addUserByJpa() {
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new UserEntity("changfubai-jpa-" + i, 20 + i));
        }
        userRepository.saveAll(list);
        List<UserEntity> all = userRepository.findAll();
        StringBuffer bf = new StringBuffer("JPA:");
        for (UserEntity entity : all) {
            bf.append(entity.toString());
        }
        return bf.toString();
    }

    @GetMapping("/jpa/findUser")
    public String findUser() {
        UserEntity user = userRepository.findUser("changfubai-jpa-1");
        return user.toString();
    }

}

package com.changfubai.springboot.database.jdbc;

import com.changfubai.springboot.database.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by changfubai on 2018/4/1
 */
@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //通过sql语句直接执行添加
    public void addUserBySql(UserBean userBean) {
        String sql = "insert into users(username,password) values('" + userBean.getUname() + "','" + userBean.getPwd() + "')";
        jdbcTemplate.update(sql);
    }

    //通过语句预处理执行操作
    public void addUserByPre(UserBean userBean) {
        String sql = "insert into users(username,password) values(?,?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userBean.getUname());
                ps.setString(2, userBean.getPwd());
            }
        });
    }

    //通过查询函数执行操作
    public List<UserBean> getUsersByQuery() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM USERS");
        List<UserBean> res = new ArrayList<>();
        for (Map<String, Object> map : list) {
            UserBean userBean = new UserBean();
            userBean.setUname((String) map.get("username"));
            userBean.setPwd((String) map.get("password"));
            res.add(userBean);
        }
        return res;
    }

    //通过语句预处理执行操作
    public List<UserBean> getUsersByExcute() {
        String sql = "select * from users";
        List<UserBean> list = jdbcTemplate.execute(sql, new PreparedStatementCallback<List<UserBean>>() {
            @Nullable
            @Override
            public List<UserBean> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet resultSet = ps.executeQuery();
                List<UserBean> list = new ArrayList<>();
                while (resultSet.next()) {
                    UserBean bean = new UserBean();
                    bean.setUname(resultSet.getString("username"));
                    bean.setPwd(resultSet.getString("password"));
                    list.add(bean);
                }
                return list;
            }
        });
        return list;
    }

}

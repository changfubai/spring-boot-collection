package com.changfubai.springboot.database.bean;

/**
 * Created by changfubai on 2018/4/1
 */
public class UserBean {

    private String uname;
    private String pwd;

    public UserBean() {
    }

    public UserBean(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}

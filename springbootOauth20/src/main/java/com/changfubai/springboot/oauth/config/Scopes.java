package com.changfubai.springboot.oauth.config;

/**
 * Created by changfubai on 2018/3/16
 */
public enum Scopes {
    // 利用构造函数传参,这里作为示例，将部分类型进行封装
    //完整类型参见： https://developer.github.com/apps/building-oauth-apps/scopes-for-oauth-apps/
    USER_READ("read:user"), EMAIL("user:email"), FELLOW("user:follow"), USER("user");

    // 定义私有变量
    private String scope;

    // 构造函数，枚举类型只能为私有
    Scopes(String _scope) {
        this.scope = _scope;
    }

    @Override
    public String toString() {
        return String.valueOf(this.scope);
    }
}

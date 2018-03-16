package com.changfubai.springboot.oauth.config;

/**
 * Created by changfubai on 2018/3/16
 */
public class GithubAppInfo {

    public static final String CLIENT_ID = "95d20899820b475a246f";
    public static final String CLIENT_SECRET = "03282abdc5bd7c41bdb460299023d0d962d61256";
    public static final String URL_USER_GETCODE = "https://github.com/login/oauth/authorize";
    public static final String URL_USER_GETTOKEN = "https://github.com/login/oauth/access_token";
    public static final String URL_USER_GETINFO = "https://api.github.com/user";

    public static String getUserCodeURL(String scope) {
        return URL_USER_GETCODE + "?scope=" + scope + "&client_id=" + CLIENT_ID;
    }

}

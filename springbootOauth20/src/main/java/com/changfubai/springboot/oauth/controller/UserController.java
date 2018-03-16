package com.changfubai.springboot.oauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.changfubai.springboot.oauth.config.GithubAppInfo;
import com.changfubai.springboot.oauth.config.Scopes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changfubai on 2018/3/16
 */
@Controller
public class UserController {

    //初始化第三方登陆链接，这里只有github。
    @RequestMapping("/")
    public String initLogin(Map<String, Object> model) {

        String url = GithubAppInfo.getUserCodeURL(Scopes.EMAIL.toString());

        System.out.println(url);
        model.put("github_url", url);

        return "index";
    }

    @RequestMapping("/getGithubCode")
    public String getCode(Model model, String code) {


        String token = getToken(code);
        if (token == null) {
            //如果没获取到，重定向到登陆页面，并提示错误
            //TODO 处理错误提示信息
            return "redirect:/";
        }
        //token可以使用多次，只要用户不取消授权。获取到用户信息后保存到自己到数据库里。
        String info = getUserInfo(token);

        Map map = JSONObject.parseObject(info);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> content = new ArrayList<>();
        for (Object obj : map.keySet()){
            name.add(obj.toString());
            Object str = map.get(obj);
            if (str== null) {
                content.add("null");
            } else {
                content.add(str.toString());
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("content", content);

        return "hello";
    }

    private String getUserInfo(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(GithubAppInfo.URL_USER_GETINFO + "?access_token=" + token, String.class);
        //此处可以处理获得的用户资料，保存到数据库中
        System.out.println(str);
        return str;
    }

    private String getToken(String code) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("client_id", GithubAppInfo.CLIENT_ID);
        hashMap.put("client_secret", GithubAppInfo.CLIENT_SECRET);
        hashMap.put("code", code);
        hashMap.put("accept", "json");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(hashMap, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(GithubAppInfo.URL_USER_GETTOKEN, HttpMethod.POST,requestEntity, String.class);

        //获取返回的header
        //List<String> val = resp.getHeaders().get("Set-Cookie");
        //System.out.println(val);

        //获得返回值
        String body = resp.getBody();
        System.out.println(body);

        int i = body.indexOf('=');
        String status = body.substring(0, i);
        String token = body.substring(i + 1, body.indexOf('&'));
        //code 只能使用一次，多次使用会报错，根据返回值可以看出来
        //正确情况：access_token=bd1386f1ef37d84062b67dde003d4def02e28f1b&scope=user%3Aemail&token_type=bearer
        //错误情况：error=bad_verification_code&error_description=The+code+passed+is+incorrect+or+expired.&error_uri=https%3A%2F%2Fdeveloper.github.com%2Fapps%2Fmanaging-oauth-apps%2Ftroubleshooting-oauth-app-access-token-request-errors%2F%23bad-verification-code
        if (status.equals("access_token")) {
            return token;
        }
        return null;
    }
}

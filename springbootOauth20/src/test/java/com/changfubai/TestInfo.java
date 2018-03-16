package com.changfubai;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by changfubai on 2018/3/16
 */


public class TestInfo {
    String info = "{\"login\":\"changfubai\",\"id\":20989201,\"avatar_url\":\"https://avatars3.githubusercontent.com/u/20989201?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/changfubai\",\"html_url\":\"https://github.com/changfubai\",\"followers_url\":\"https://api.github.com/users/changfubai/followers\",\"following_url\":\"https://api.github.com/users/changfubai/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/changfubai/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/changfubai/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/changfubai/subscriptions\",\"organizations_url\":\"https://api.github.com/users/changfubai/orgs\",\"repos_url\":\"https://api.github.com/users/changfubai/repos\",\"events_url\":\"https://api.github.com/users/changfubai/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/changfubai/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":null,\"company\":null,\"blog\":\"\",\"location\":null,\"email\":null,\"hireable\":null,\"bio\":null,\"public_repos\":11,\"public_gists\":0,\"followers\":1,\"following\":2,\"created_at\":\"2016-08-12T10:49:43Z\",\"updated_at\":\"2018-03-16T01:02:18Z\"}";
    @Test
    public void test() {
        System.out.println(info);
        Map map = JSONObject.parseObject(info);
        for (Object obj : map.keySet()){
            System.out.println("key为："+obj+"值为："+map.get(obj));
        }
    }
}

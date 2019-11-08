package com.order.orderzuulservice.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * created by zhangdingping at 2019/10/29
 */
@Component
public class WebRpcRemote {


    @Autowired
    private RestTemplate restTemplate;

    public Boolean checkToken(String token) {
        JSONObject result = restTemplate.getForObject("http://order/zzshx/token/check/{token}", JSONObject.class, token);
        HttpResult httpResult = JSON.parseObject(result.toJSONString(), HttpResult.class);
        Boolean ans = (Boolean) httpResult.getResultBody();
        return ans;
    }
}

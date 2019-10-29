package com.order.orderzuulservice.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * created by zhangdingping at 2019/10/29
 */
@Component
public class WebRpcRemote {

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean checkToken(String token) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        JSONObject result = restTemplate.getForObject(orderServiceUrl + "/token/check/{token}", JSONObject.class, params);
        HttpResult httpResult = JSON.parseObject(result.toJSONString(), HttpResult.class);
        Boolean ans = (Boolean) httpResult.getResultBody();
        return ans;
    }
}

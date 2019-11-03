package com.order.orderzuulservice.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.order.orderzuulservice.remote.HttpResult;
import com.order.orderzuulservice.remote.WebRpcRemote;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * created by zhangdingping at 2019/10/28
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    private WebRpcRemote webRpcRemote;

    private List<String> unAuthUrl = Arrays.asList("/zzshx/user/login/", "/zzshx/user/regist/");

    private Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.set("logic-is-success", true);

        String path = request.getServletPath();
        logger.info("path:{}", path);

        for (String url : unAuthUrl) {
            if (path.contains(url)) return null;
        }

        String token = request.getHeader("token");
        logger.info("token:{}", token);
        if (StringUtils.isEmpty(token)) {
            return processInvalidToken(ctx);
        }

        if (!webRpcRemote.checkToken(token).equals(Boolean.TRUE)) {
            return processInvalidToken(ctx);
        }

        return null;
    }

    private Object processInvalidToken(RequestContext ctx) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(JSON.toJSONString(HttpResult.invalidToken("无效的token")));
        HttpServletResponse response = ctx.getResponse();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ctx.set("logic-is-success", false);
        return null;
    }
}

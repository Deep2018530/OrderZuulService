package com.order.orderzuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.order.orderzuulservice.remote.WebRpcRemote;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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

    private List<String> unAuthUrl = Arrays.asList("/zzshx/user/login/");

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
            if (url.startsWith(path)) return null;
        }


        String token = request.getHeader("token");
        logger.info("token:{}", token);
        if (StringUtils.isEmpty(token)) {
            logger.warn("token为空！token:{}", token);
            ctx.setSendZuulResponse(false);
            ctx.set("logic-is-success", false);
            return null;
        }


        if (!webRpcRemote.checkToken(token).equals(Boolean.TRUE)) {
            logger.warn("token失效,token:{}", token);
            ctx.setSendZuulResponse(false);
            ctx.set("logic-is-success", false);
            return null;
        }

        return null;
    }
}

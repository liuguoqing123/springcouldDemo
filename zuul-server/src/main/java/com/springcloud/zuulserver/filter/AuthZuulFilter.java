package com.springcloud.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class AuthZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //什么类型的filter
        /*pre：路由之前
        routing：路由之时
        post： 路由之后
        error：发送错误调用*/
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        System.out.println("HttpServletRequest:"+request.getRequestURI());
        if(request.getRequestURI().startsWith("/product/")){
            return true;
        }
        return false;
    }

    /**
     * 过滤的时候做什么样的事情
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("token");
        System.out.println(request.getRequestURI());
        if (StringUtils.isEmpty(token)){
            //处理地址栏中的token
            token = request.getParameter("token");
        }
        if(StringUtils.isEmpty(token)){
            context.setSendZuulResponse(false);
            //401没有认证权限
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}

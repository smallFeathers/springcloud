package com.xiaoyumao.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {
    /**
     * 规定过滤器类型的方法
     * pre 表示在路由前执行
     * 除此外还有post error route static 及自定义
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器启动顺序，有多个时，越靠前值越小
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否要执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器具体执行逻辑
     * 试验将在这里判断是否有token
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        //异常测试
        int a = 10 / 0;

        //1、获取request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //2、通过上下文获取request对象
        HttpServletRequest request = currentContext.getRequest();
        //3、从对象中获取token
        String token = request.getParameter("token");
        if (token == null) {
            currentContext.setSendZuulResponse(false);  //转发设为false
            currentContext.setResponseStatusCode(401);  //401表示没有权限
            //设置响应头信息，类型为html
            currentContext.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            currentContext.setResponseBody("非法访问。。。");
        }

        return null;    //试验暂时无用，返回空
    }
}

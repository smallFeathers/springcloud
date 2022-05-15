package com.xiaoyumao.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.management.ReflectionException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常过滤类,用来处理自定义的异常处理逻辑
 */
@Component
public class ErrorFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
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
        try {
            RequestContext currentContext = RequestContext.getCurrentContext();
            ZuulException throwable = (ZuulException) currentContext.getThrowable();
            logger.error("系统进入异常：" + throwable.getMessage());
            HttpServletResponse response = currentContext.getResponse();
            response.setContentType("application/json;charset=utf8");
            response.setStatus(throwable.nStatusCode);
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
                pw.print("code:" + throwable.nStatusCode + ";message:" + throwable.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}

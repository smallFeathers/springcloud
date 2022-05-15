package com.xiaoyumao.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义全局错误页面
 * 通过实现ErrorController来实现全局异常处理
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    /**
     * 出异常后进入该方法，交由下面方法处理
     * StringBoot项目发生异常时通常返回“/error”这个路径，然后我们把这个路径重新指定下，在里边写入处理逻辑
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("/error")
    public Object error() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        ZuulException zuulException = (ZuulException) currentContext.getThrowable();
        return zuulException.nStatusCode + "--" + zuulException.getMessage();

    }
}

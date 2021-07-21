package com.abo.jt.filter;

import com.abo.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.cloud.netflix.ribbon.apache.HttpClientStatusCodeException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.nio.charset.Charset;

/**
 * 模拟判断是否登录
 * http://localhost:3001/item-service/asdasd                    没有登录，不允许访问
 * http://localhost:3001/item-service/asdasd?token=qwerqwer    已登录，允许访问
 *
 * 只检查对商品访问的权限
 * 用户和订单的访问不检查权限
 *
 * @author Abo
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 指定过滤器的类型
     *      1.pre
     *      2.routing
     *      3.post
     *      4.error
     * 一般自定义过滤器均为pre过滤器
     * @return
     */
    @Override
    public String filterType() {
        //前置过滤器
        // return "pre"; //下面形式的简写
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 设置过滤器的顺序号
     * 以pre过滤器为例，在自定义pre过滤器后，其内部其实已经有一组pre过滤器，
     * filterOrder()决定我们自定义的过滤器放在哪个位置
     *
     * 上下文对象是在第五个过滤器中设置的，所以我们自定义设置的过滤器要在5之后
     * @return
     */
    @Override
    public int filterOrder() {
        // 放在第六个执行
        return 6;
    }

    /**
     * 判断针对当前请求，是否执行过滤代码
     * true -> 执行run()
     * false -> 不执行run()
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 调用商品检查权限
        // 调用用户和订单，不检查权限

        // 判断当前请求调用的是否是item-service
        // 1.获得请求上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 2.从上下文对象获取调用的服务id
        Object serviceId = currentContext.get(FilterConstants.SERVICE_ID_KEY);
        // 3.判断服务id
        String targetService = "item-service";
        if (targetService.equals(serviceId)) {
            return true;
        }
        return false;
    }

    /**
     * 过滤代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 1.获得上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 2.获得request对象
        HttpServletRequest request = currentContext.getRequest();
        // 3.接收token参数
        String token = request.getParameter("token");
        // 4.判断token是否为空，如果没有token，阻止继续调用
        if (StringUtils.isBlank(token)) {
            // 阻止继续调用
            currentContext.setSendZuulResponse(false);
            // 直接返回响应  {code:400,msg:not login,data:null}
            String jsonResult = JsonResult.err().code(HttpServletResponse.SC_UNAUTHORIZED).msg("未登录！").toString();
            currentContext.addZuulResponseHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            currentContext.setResponseBody(jsonResult);
        }
        // 在当前zuul版本中，这个返回值没有任何作用
        return null;
    }
}

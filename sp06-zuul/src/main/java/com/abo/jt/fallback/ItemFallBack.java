package com.abo.jt.fallback;

import com.abo.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 调用商品服务失败时，执行这里的降级代码，返回降级结果
 * @author Abo
 */
@Component
public class ItemFallBack implements FallbackProvider {
    /**
     * 针对哪个后台服务调用失败，应用当前降级类
     * 返回值：
     *      "item-service"  只针对商品服务降级
     *      "*"             对所有后台服务，都应用当前降级类
     *      null            对所有后台服务，都应用当前降级类(同*)
     * @return
     */
    @Override
    public String getRoute() {
        return "item-service";
    }

    /**
     * 返回给客户端的降级响应数据
     * 封装到response对象
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        ClientHttpResponse clientHttpResponse = new ClientHttpResponse() {
            // 500 internal error
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            // 500
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            // internal error
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {
                // 用来关闭下面的流
                // ByteArrayInputStream --> 内存数组流，不占用低层系统资源，不需要关闭
                // 文件流，占用系统文件资源
                // 网络流，占用网络资源
                // 数据库流，占用数据库资源
            }

            @Override
            public InputStream getBody() throws IOException {
                // JsonResult --> {code:500, msg:调用后台服务出错, data:null}
                String json = JsonResult
                                .err()
                                .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                                .msg("调用后台服务出错")
                                .toString();
                // 将json封装到 ByteArrayInputStream
                ByteArrayInputStream body =
                        new ByteArrayInputStream(json.getBytes("UTF-8"));
                return body;
            }

            @Override
            public HttpHeaders getHeaders() {
                // Content-type:application/json;character=utf-8
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
                return httpHeaders;
            }
        };
        return clientHttpResponse;
    }
}

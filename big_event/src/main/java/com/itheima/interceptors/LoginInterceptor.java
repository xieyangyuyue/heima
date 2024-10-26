package com.itheima.interceptors;

import com.itheima.util.JwtUtil;
import com.itheima.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/*您提供的代码是一个Spring框架中的拦截器（Interceptor），用于在请求处理之前执行一些操作。这个拦截器实现了 HandlerInterceptor 接口，
并重写了 preHandle 方法。这里是方法的详细解释：
preHandle 方法在请求到达实际处理器（如Controller）之前被调用。这个方法必须返回一个布尔值，表示是否继续执行链中的]下一个拦截器或处理器。
在 preHandle 方法中，首先从HTTP请求的头信息中获取了名为 “Authorization” 的令牌（token）。
然后，使用一个名为 JwtUtil 的工具类来解析这个token。这个 parseToken 方法应该是用来验证token的有效性，并提取出token中的载荷（payload）数据。
如果token验证成功，提取的载荷数据被存储到一个名为 ThreadLocalUtil 的工具类的 ThreadLocal 变量中。ThreadLocal 用于存储线程局部变量，
这样在同一个线程中的其他部分可以访问这些数据。
如果token验证失败，拦截器会设置HTTP响应的状态码为401（未授权），并返回 false，从而阻止请求继续传递到处理器。
此外，您还需要在Spring的配置中注册这个拦截器，使其能够拦截相应的请求。这通常是通过实现 WebMvcConfigurer 接口并重写 addInterceptors 方法来完成的。*/
//拦截器
//@Component 注解将 LoginInterceptor 类标记为一个Spring组件，使其能够被Spring容器自动检测并注册为一个Bean。
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * preHandle 方法在请求到达实际处理器（如Controller）之前被调用。这个方法必须返回一个布尔值，表示是否继续执行链中的下一个拦截器或处理器。
     *
     * @param request  这是Java Servlet API的一部分，代表客户端发出的HTTP请求。
     *                 通过这个对象，您可以访问请求的所有信息，包括请求参数、头信息、请求方法（GET、POST等）、请求URL等。
     * @param response 代表服务器返回给客户端的HTTP响应。通过这个对象，您可以操作响应的状态码、头信息以及发送响应内容。
     * @param handler  这个参数表示当前请求被调用的处理器对象。 在Spring MVC中，这通常是一个 HandlerMethod 实例，
     *                 它代表了处理请求的控制器方法。通过这个对象，您可以获得方法的相关信息，如方法参数、返回类型、注解等。
     *
     */

    /**
     *
     preHandle：请求到达接口之前执行，afterCompletion：请求（接口）结束后执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据头部获取令牌
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //解析请求头（token），返回载荷
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //把业务数据存储到TreadLocal
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //校验失败，http状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    //响应完后清除线程
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空TreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}

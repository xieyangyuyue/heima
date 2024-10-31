package com.itheima.config;

import com.itheima.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 网页拦截， 登录接口和注册接口不拦截

 在您提供的代码中，WebConfig类实现了WebMvcConfigurer接口，
 并重写了 addInterceptors方法。
 这个方法的目的是注册拦截器，

通过这种方式，您可以在应用程序中自定义哪些请求应该被拦截器处理，哪些请求应该被放过。
这对于不需要身份验证的公共端点（如登录和注册）非常有用，因为您不希望在这些端点上拦截用户。
请注意addInterceptors方法还可以接受其他参数，例如addPathPatterns，用于指定拦截器应该拦截哪些请求。
如果您不调用addPathPatterns，默认情况下，拦截器会拦截所有请求，除非被excludePathPatterns排除。

*/

//1. `@Configuration` 注解表示 `WebConfig` 类是一个Spring配置类，它会被Spring容器处理，以注册beans和进行其他配置。
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * @param registry 3. addInterceptors`方法接受一个 InterceptorRegistry 参数，这个参数用于注册拦截器。
     *                 4. 在 addInterceptors方法中，通过调用 registry.addInterceptor(loginInterceptor) 方法来注册 LoginInterceptor。
     *                 5. excludePathPatterns("/user/login", "/user/register"` 方法调用表示，对于路径为 /user/login 和 /user/register的请求，
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register");
    }
}

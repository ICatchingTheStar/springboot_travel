package com.travel.mybootdemo.config;

import com.travel.mybootdemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-08-57
 * @Description: 拦截器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/myfavorite","/userIsFavorite");//在需要登录才能显示的页面进行拦截即可
//                .excludePathPatterns("/","/login","/register","register_ok"
//                        ,"/css/**","/fonts/**","/images/**","/js/**","/img/**","/error/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

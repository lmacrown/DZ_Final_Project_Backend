package com.douzone;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.interceptor.LoginMemberInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//    	System.out.println("인터셉터 추가");
//        registry.addInterceptor(new LoginMemberInterceptor())
//                .addPathPatterns("/**") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
//                .excludePathPatterns(); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//    	System.out.println("인터셉터 추가!");
//    	 registry.addMapping("/**")
//    	 .allowedOrigins("*");
//    }
}
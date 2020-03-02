package com.example.hotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description TODO
 * @Author
 * @Date 2020/1/29 21:07
 **/
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问（可选）
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 直接在浏览器访问：根目录/swagger-ui.html
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 需要用到的webjars（包含js、css等）
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 解决外部图片无法访问问题
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///E:/images/");
    }
}

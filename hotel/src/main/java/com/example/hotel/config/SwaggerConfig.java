package com.example.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2
 * @Description TODO(Swagger2配置类,要与Application同级)
 * @Author
 * @Date 2020/1/20 14:51
 **/

/**
 * @Configuration让Spring加载该类配置
 * @EnableSwagger2启用Swagger2
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    /**
     * 创建API应用
     * apiInfo()增加API相关信息
     * 通过select()函数返回ApiSelectorBuilder实例，用来空值哪些接口暴露给Swagger展现
     * 这里采用指定扫描包路径来定义指定要建立API的目录
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.hotel"))// 指定扫描包下面的注解
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该Api的基本信息（该基本信息会展现到文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    /*
    定义展示的信息，例如标题、描述、版本等
     */

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("智慧点餐管理系统").description("智慧点餐管理系统的api文档")
                .termsOfServiceUrl("http://127.0.0.1:8080/api")
                .version("1.0")
                .build();
    }

}

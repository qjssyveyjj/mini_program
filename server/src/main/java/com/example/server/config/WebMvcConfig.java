package com.example.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类，映射上传文件静态资源
 *
 * @author Java1234
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 文件上传根目录 */
    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * 配置静态资源映射，将 /uploads/** 映射到本地目录
     *
     * @param registry 资源处理器注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + uploadDir.replace("\\", "/") + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}

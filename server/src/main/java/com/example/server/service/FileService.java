package com.example.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传业务接口
 *
 * @author Java1234
 */
public interface FileService {

    /**
     * 上传文件到本地目录
     *
     * @param file 上传文件
     * @return 文件访问URL
     */
    String upload(MultipartFile file);
}

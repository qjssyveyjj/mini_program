package com.example.server.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 明文密码编码器（毕业设计演示用，不对密码加密）
 *
 * @author Java1234
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {

    /**
     * 明文存储密码
     *
     * @param rawPassword 原始密码
     * @return 原始密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 明文比对密码
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 存储密码
     * @return 是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}

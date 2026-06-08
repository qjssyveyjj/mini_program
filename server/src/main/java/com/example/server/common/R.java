package com.example.server.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一API响应结果封装类
 *
 * @author Java1234
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 默认构造方法
     */
    public R() {
        put("code", 200);
        put("msg", "操作成功");
    }

    /**
     * 返回成功结果
     *
     * @return 成功响应
     */
    public static R ok() {
        return new R();
    }

    /**
     * 返回成功结果并携带数据
     *
     * @param data 响应数据
     * @return 成功响应
     */
    public static R ok(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }

    /**
     * 返回成功结果并携带消息
     *
     * @param msg  提示消息
     * @param data 响应数据
     * @return 成功响应
     */
    public static R ok(String msg, Object data) {
        R r = new R();
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    /**
     * 返回失败结果
     *
     * @param msg 错误消息
     * @return 失败响应
     */
    public static R error(String msg) {
        R r = new R();
        r.put("code", 500);
        r.put("msg", msg);
        return r;
    }

    /**
     * 返回失败结果并指定状态码
     *
     * @param code 状态码
     * @param msg  错误消息
     * @return 失败响应
     */
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 链式设置响应字段
     *
     * @param key   字段名
     * @param value 字段值
     * @return 当前响应对象
     */
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 链式设置多个响应字段
     *
     * @param map 字段映射
     * @return 当前响应对象
     */
    public R putAll(Map<String, Object> map) {
        super.putAll(map);
        return this;
    }
}

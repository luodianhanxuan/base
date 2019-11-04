package com.wangjg.cache;

/**
 * @author wangjg
 * 2019/10/17
 * <p>
 * 缓存服务
 */
public interface Cache {

    boolean expire(long expire);

    boolean exists(String key);

    boolean set(String key, Object data);

    boolean set(String key, Object data, long expire);

    <T> T get(String key);

    boolean del(String key);

    boolean hset(String hkey, String key, Object data);

    boolean hget(String hkey, String key, Object data);

    boolean hdel(String key);
}

package com.wangjg.cache.local;

import com.wangjg.cache.Cache;

/**
 * @author wangjg
 * 2019/10/17
 */
public class MapLocalCacheImpl implements Cache {
    @Override
    public boolean expire(long expire) {
        return false;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public boolean set(String key, Object data) {
        return false;
    }

    @Override
    public boolean set(String key, Object data, long expire) {
        return false;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public boolean del(String key) {
        return false;
    }

    @Override
    public boolean hset(String hkey, String key, Object data) {
        return false;
    }

    @Override
    public boolean hget(String hkey, String key, Object data) {
        return false;
    }

    @Override
    public boolean hdel(String key) {
        return false;
    }
}

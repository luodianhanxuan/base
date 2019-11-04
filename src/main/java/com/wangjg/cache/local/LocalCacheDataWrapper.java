package com.wangjg.cache.local;

/**
 * @author wangjg
 * 2019/10/17
 */
class LocalCacheDataWrapper {
    /**
     * 缓存开始时间
     */
    long timestamp;

    /**
     * 多少秒之后过期
     */
    long expire;

    /**
     * 是否已过期
     */
    volatile boolean expired;

    /**
     * 缓存数据
     */
    Object data;
}

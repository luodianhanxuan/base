package com.wangjg.cache;

/**
 * @author wangjg
 * 2020/3/25
 */
public class CacheEntity<T> {

    /**
     * 要存储的数据
     */
    private T value;

    /**
     * 创建的时间 单位ms
     */
    private long createTime = System.currentTimeMillis();

    /**
     * 缓存的有效时间 单位ms （小于等于0表示永久保存）
     */
    private long cacheTime;

    public CacheEntity() {
        super();
    }

    public CacheEntity(T value, long cacheTime) {
        this.value = value;
        this.cacheTime = cacheTime;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }
}

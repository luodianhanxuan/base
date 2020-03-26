package com.wangjg.cache;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjg
 * 2020/3/25
 */
@SuppressWarnings("UnusedReturnValue")
public class CacheManager {

    /**
     * 缓存Map对象
     */
    private static ConcurrentHashMap<String, CacheEntity<?>> cacheMap = new ConcurrentHashMap<>();

    static {
        // 创建定时任务每分钟清理一次缓存
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 60000);
    }

    /**
     * 缓存刷新,清除过期数据
     */
    public static void refresh() {
        for (String key : cacheMap.keySet()) {
            if (isExpire(key)) {
                remove(key);
            }
        }
    }

    /**
     * 加入缓存
     */
    public static boolean put(String key, Object value) {
        if (key.isEmpty()) {
            return false;
        }
        CacheEntity<Object> cacheEntity = new CacheEntity<>();
        cacheEntity.setCacheTime(0);
        cacheEntity.setValue(value);
        cacheMap.put(key, cacheEntity);
        return true;
    }

    /**
     * 加入缓存，包含过期时间
     *
     * @param key       缓存数据的关键字
     * @param value     缓存数据
     * @param cacheTime 要缓存的时间
     * @param timeUnit  时间单位
     */
    public static boolean put(String key, Object value, long cacheTime, TimeUnit timeUnit) {
        if (key.isEmpty()) {
            return false;
        }
        CacheEntity<Object> cacheEntity = new CacheEntity<>();
        cacheEntity.setCacheTime(timeUnit.toMillis(cacheTime));
        cacheEntity.setValue(value);
        cacheMap.put(key, cacheEntity);
        return true;
    }

    /**
     * 移除缓存数据
     */
    public static boolean remove(String key) {
        if (key.isEmpty()) {
            return false;
        }
        if (!cacheMap.containsKey(key)) {
            return true;
        }
        cacheMap.remove(key);
        return true;
    }

    /**
     * 获取缓存数据
     */
    public static Object get(String key) {
        if (key.isEmpty() || isExpire(key)) {
            return null;
        }
        CacheEntity<?> cacheEntity = cacheMap.get(key);
        if (null == cacheEntity) {
            return null;
        }
        return cacheEntity.getValue();
    }

    /**
     * 判断当前数据是否已过期
     */
    private static boolean isExpire(String key) {
        if (key.isEmpty()) {
            return false;
        }
        if (cacheMap.containsKey(key)) {
            CacheEntity<?> cacheEntity = cacheMap.get(key);
            long createTime = cacheEntity.getCreateTime();
            long currentTime = System.currentTimeMillis();
            long cacheTime = cacheEntity.getCacheTime();
            return cacheTime > 0 && currentTime - createTime > cacheTime;
        }
        return false;
    }

    /**
     * 获取当前缓存大小（包含已过期但未清理的数据）
     */
    public static int getCacheSize() {
        return cacheMap.size();
    }
}

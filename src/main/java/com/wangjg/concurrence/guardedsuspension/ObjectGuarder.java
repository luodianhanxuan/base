package com.wangjg.concurrence.guardedsuspension;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author wangjg
 * 2019/10/30
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ObjectGuarder<T> {

    private ObjectGuarder() {
    }

    /**
     * 要守卫的值对象
     */
    private T v;
    /**
     * 可重入锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 条件队列
     */
    private Condition done = lock.newCondition();

    /**
     * 获取受保护的值对象
     */
    public T get() {
        return get(Objects::nonNull);
    }

    /**
     * 获取受保护的值对象
     *
     * @param predicate 取值条件
     */
    public T get(Predicate<T> predicate) {
        lock.lock();
        try {
            while (!predicate.test(v)) {
                done.await(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return v;
    }

    /**
     * 将值对象放入 guarder 中
     *
     * @param v 受保护的值对象
     */
    public void onChange(T v) {
        lock.lock();
        try {
            this.v = v;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }


    private final static Map<Object, ObjectGuarder<?>> map = new ConcurrentHashMap<>();

    @SuppressWarnings({"unchecked"})
    public static <T> ObjectGuarder<T> getObjectGuarder(Object key, Class<T> objClazz) {
        if (map.containsKey(key)) {
            return (ObjectGuarder<T>) map.get(key);
        }
        ObjectGuarder<T> tObjectGuarder = new ObjectGuarder<>();
        map.put(key, tObjectGuarder);
        return tObjectGuarder;
    }

}

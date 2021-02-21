package com.wangjg.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangjg
 * 2020/7/27
 */
public class Aqs {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ReentrantLock reentrantLock = new ReentrantLock();
        for (int i = 0; i < 1; i++) {
            executorService.execute(() -> {
                reentrantLock.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            });
        }
        System.out.println("ok");
    }
}

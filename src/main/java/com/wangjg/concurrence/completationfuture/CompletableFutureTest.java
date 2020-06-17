package com.wangjg.concurrence.completationfuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangjg
 * 2020/6/17
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        /*
            任务：
                        拿水壶  ->  洗水壶 -> 烧开水  -
                                                   |
                                                   |   上茶（茶名）
              拿茶叶（龙井）   ->  洗茶  ->  泡茶     -
         */

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("拿水壶");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            try {
                System.out.println("洗水壶");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            try {
                System.out.println("烧开水");
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                String teaName = "龙井";
                System.out.println("拿茶叶：" + teaName);
                Thread.sleep(1000);
                return teaName;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        }).thenApply(t -> {
            try {
                System.out.println("洗茶：" + t);
                Thread.sleep(5000);
                return t;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        }).thenApply(t -> {
            try {
                System.out.println("泡茶：" + t);
                Thread.sleep(10000);
                return t;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        });

        CompletableFuture<Void> future = f1.thenAcceptBoth(f2, (__, t) -> {
            System.out.println("上茶：" + t);
        });

        future.join();
    }

}

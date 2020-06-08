package com.wangjg.concurrence.test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * @author wangjg
 * 2020/5/18
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletionService<String> cs = new ExecutorCompletionService<>(Executors.newFixedThreadPool(3));

        cs.submit(() -> {
            Thread.sleep(3000);
            return "3";
        });

        cs.submit(() -> {
            Thread.sleep(1000);
            return "1";
        });

        cs.submit(() -> {
            Thread.sleep(2000);
            return "2";
        });

        System.out.println(cs.take().get());

    }
}

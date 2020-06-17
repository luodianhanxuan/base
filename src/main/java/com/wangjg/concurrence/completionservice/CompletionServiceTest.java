package com.wangjg.concurrence.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangjg
 * 2020/5/18
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> cs = new ExecutorCompletionService<>(executor);

        List<Future<String>> list = new ArrayList<>();

        list.add(cs.submit(() -> {
            Thread.sleep(3000);
            return "3";
        }));

        list.add(cs.submit(() -> {
            Thread.sleep(1000);
            return "1";
        }));

        list.add(cs.submit(() -> {
            Thread.sleep(2000);
            return "2";
        }));

        System.out.println(cs.take().get());
    }
}

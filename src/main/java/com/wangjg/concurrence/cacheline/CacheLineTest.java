package com.wangjg.concurrence.cacheline;

import sun.misc.Contended;

/**
 * @author wangjg
 * 2020/7/1
 */
@SuppressWarnings("DuplicatedCode")
public class CacheLineTest {

    private static class GeneralStyle {
        private volatile long l0;
        private volatile long l00;

        public void test() throws InterruptedException {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l0 = i;
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l00 = i;
                }
            });

            long begin = System.currentTimeMillis();
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            long end = System.currentTimeMillis();

            System.out.println(end - begin);
        }
    }

    private static class Jdk7Style {
        private long l1, l2, l3, l4, l5, l6, l7;

        private volatile long l0;

        private long l8, l9, l10, l11, l12, l13, l14;

        private volatile long l00;

        public void test() throws InterruptedException {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l0 = i;
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l00 = i;
                }
            });

            long begin = System.currentTimeMillis();
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            long end = System.currentTimeMillis();

            System.out.println(end - begin);

        }
    }

    private static class Jdk8Style {

        /**
         * 使用此注解须添加 jvm 运行参数：-XX:-RestrictContended
         */
        @Contended
        private volatile long l0;
        @Contended
        private volatile long l00;

        public void test() throws InterruptedException {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l0 = i;
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10_0000_0000; i++) {
                    l00 = i;
                }
            });

            long begin = System.currentTimeMillis();
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            long end = System.currentTimeMillis();

            System.out.println(end - begin);

        }
    }


    public static void main(String[] args) throws InterruptedException {
        GeneralStyle generalStyle = new GeneralStyle();
        generalStyle.test();

        Jdk7Style jdk7Style = new Jdk7Style();
        jdk7Style.test();

        Jdk8Style jdk8Style = new Jdk8Style();
        jdk8Style.test();

        /*
        3908
        944
        940
         */
    }
}

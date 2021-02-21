package com.wangjg.concurrence.cpuunorder;

/**
 * @author wangjg
 * 2020/7/1
 */
public class UnOrderProve {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;

            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread one = new Thread(() -> {
                // 由于线程 one 先启动，下面这句话让它等一等线程 two，可根据自己电脑的实际性能调整等待时间
                // shortWait(10000);
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();
            other.start();

            one.join();
            other.join();

            String result = String.format("第%s次（x = %s，y = %s）", i, x, y);

            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            }
        }
        /*
            第1138733次（x = 0，y = 0）
         */
    }
}

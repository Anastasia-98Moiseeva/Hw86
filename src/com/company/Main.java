package com.company;

public class Main {

    public static void main(String[] args) {
        int threadNum = 10;

        System.out.println("\n************************");
        System.out.println("Execute TTAS barrier");
        System.out.println("************************");
        Barrier ttasBarrier = new TTASBarrier(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                foo();
                ttasBarrier.await();
                bar();
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n************************");
        System.out.println("Execute array barrier");
        System.out.println("************************");
        Barrier arrayBarrier = new ArrayBarrier(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                foo();
                arrayBarrier.await();
                bar();
            }).start();
        }
    }


    private static void foo() {
        System.out.println("Execute foo");
    }

    private static void bar() {
        System.out.println("Execute bar");
    }
}

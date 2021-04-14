package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBarrier implements Barrier {
    private final int threadNum;
    private volatile int[] arr;
    private final AtomicInteger num;

    public ArrayBarrier(int threadNum) {
        this.threadNum = threadNum;
        this.arr = new int[threadNum];
        this.num = new AtomicInteger(0);
    }

    @Override
    public void await() {
        int currNum = num.getAndIncrement();
        if (currNum != 0) {
            while (arr[currNum - 1] != 1) {
                Thread.onSpinWait();
            }
        }
        arr[currNum] = 1;
        if (currNum != threadNum - 1) {
            while (arr[currNum + 1] != 2) {
                Thread.onSpinWait();
            }
        }
        arr[currNum] = 2;
    }
}

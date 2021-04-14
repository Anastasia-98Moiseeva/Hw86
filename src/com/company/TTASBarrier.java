package com.company;

public class TTASBarrier implements Barrier{
    private final Lock lock;
    private final int threadNum;
    private int counter;

    public TTASBarrier(int threadNum){
        lock = new TTASLock();
        this.threadNum = threadNum;
        counter = 0;
    }

    @Override
    public void await() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }

        synchronized (lock) {
            if (counter < threadNum) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                lock.notifyAll();
            }
        }
    }
}

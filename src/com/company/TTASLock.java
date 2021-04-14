package com.company;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock{
    private final AtomicBoolean isLocked = new AtomicBoolean(false);

    @Override
    public void lock() {
        while (true) {
            while (!isLocked.get()) {
                if (!isLocked.getAndSet(true)) {
                    return;
                }
            }
        }
    }

    @Override
    public void unlock() {
        isLocked.set(false);
    }
}

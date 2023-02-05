package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO
 * @date : 2023-02-02
 */
public class Dubbo {

    // 创建锁与条件变量
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();

    Object response;

    // 调用方通过该方法等待结果
    Object get(int timeout) throws TimeoutException, InterruptedException {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()) {
                done.await(timeout, TimeUnit.SECONDS);
                long cur=System.nanoTime();
                if (isDone() || cur-start > timeout){
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
        if (!isDone()) {
            throw new TimeoutException();
        }
        return null;
    }

    // RPC结果是否已经返回
    boolean isDone() {
        return response != null;
    }

    // RPC结果返回时调用该方法
    private void doReceived(Object res) {
        lock.lock();
        try {
            response = res;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}

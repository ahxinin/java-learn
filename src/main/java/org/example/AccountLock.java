package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: lock锁
 * @date : 2023-02-02
 */
class AccountLock {

    private int balance;
    private final Lock lock = new ReentrantLock();

    // 转账
    void transfer(AccountLock tar, int amt){
        while (true) {
            if(this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer
}

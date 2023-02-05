package org.example;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @description: CyclicBarrier 实现线程同步
 * @date : 2023-02-05
 */
public class CyclicBarrierTest {

    // 订单队列
    Vector<?> pos;
    // 派送单队列
    Vector<?> dos;

    // 执行回调的线程池
    Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier = new CyclicBarrier(2, ()->{
        executor.execute(()-> check());
    });

    void check(){
        //P p = pos.remove(0);
        //D d = dos.remove(0);
        // 执行对账操作
        //diff = check(p, d);
        // 差异写入差异库
        //save(diff);
    }

    void checkAll(){
        // 循环查询订单库
        Thread T1 = new Thread(()->{
            while(true){
                // 查询订单库
                //pos.add(getPOrders());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(()->{
            while(true){
                // 查询运单库
                //dos.add(getDOrders());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }
}

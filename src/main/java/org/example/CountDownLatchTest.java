package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @description: CountDownLatch 线程等待协调
 * @date : 2023-02-05
 */
public class CountDownLatchTest {

    public void caltule() throws InterruptedException {
        // 创建2个线程的线程池
        Executor executor = Executors.newFixedThreadPool(2);

        while(true){
            // 计数器初始化为2
            CountDownLatch latch = new CountDownLatch(2);

            // 查询未对账订单
            executor.execute(()-> {
                //pos = getPOrders();
                latch.countDown();
            });

            // 查询派送单
            executor.execute(()-> {
                //dos = getDOrders();
                latch.countDown();
            });

            // 等待两个查询操作结束
            latch.await();

            // 执行对账操作
            //diff = check(pos, dos);
            // 差异写入差异库
            //save(diff);
        }
    }
}

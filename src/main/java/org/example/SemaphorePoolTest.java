package org.example;

/**
 * @description: TODO
 * @date : 2023-02-05
 */
public class SemaphorePoolTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        SemaphorePool<Long, String> semaphorePool = new SemaphorePool<Long, String>(10, 2L);
        // 通过对象池获取t，之后执行
        semaphorePool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}

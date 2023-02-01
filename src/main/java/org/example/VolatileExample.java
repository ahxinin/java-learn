package org.example;

/**
 * @author : Kane
 * @description: TODO
 * @date : 2023-01-31
 */

// 以下代码来源于【参考1】
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v == true) {
            System.out.println("x:"+x);
        }
    }

    public static void main(String[] args){
        VolatileExample example = new VolatileExample();
        example.writer();
        example.reader();
    }
}
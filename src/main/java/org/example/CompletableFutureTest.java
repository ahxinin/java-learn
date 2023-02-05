package org.example;

import java.util.concurrent.CompletableFuture;

/**
 * @description: TODO
 * @date : 2023-02-05
 */
public class CompletableFutureTest {

    public void test(){
        Object po = null;
        CompletableFuture<Boolean> cf = CompletableFuture.supplyAsync(()->{
            //在数据库中查询规则
            return findRuleByJdbc();
        }).thenApply(r -> {
            //规则校验
            return check(po, r);
        });
        Boolean isOk = cf.join();
    }

    public boolean check(Object o, Object r){
        return false;
    }

    public Object findRuleByJdbc(){
        return new Object();
    }

}

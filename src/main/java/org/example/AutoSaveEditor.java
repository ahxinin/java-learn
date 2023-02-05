package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO
 * @date : 2023-02-05
 */
class AutoSaveEditor{
    //文件是否被修改过
    boolean changed=false;

    //定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    //定时执行自动保存
    void startAutoSave(){
        ses.scheduleWithFixedDelay(()->{
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }

    //自动存盘操作
    void autoSave(){
        synchronized (this){
            if (!changed) {
                return;
            }
            changed = false;
        }
        //执行存盘操作
        //省略且实现
        this.execSave();
    }

    //编辑操作
    void edit(){
        //省略编辑逻辑
        synchronized (this){
            changed = true;
        }
    }

    void execSave(){

    }
}

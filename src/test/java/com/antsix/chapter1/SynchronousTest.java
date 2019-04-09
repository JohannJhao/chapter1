package com.antsix.chapter1;

import com.antsix.common.synchronous.AsynchronousCallbackTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

/**
 * 同步，异步调用，异步回调测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SynchronousTest {

    //同步调用
    //@Autowired
    //private SynchronousTask synchronousTask;

    //异步调用
    //@Autowired
    //private AsynchronousTask asynchronousTask;

    //异步回调
    @Autowired
    private AsynchronousCallbackTask asynchronousCallbackTask;


    /**
     * 同步调用
     * @throws Exception
     */
//    @Test
//    public void synchronousTest() throws Exception {
//        synchronousTask.doTaskOne();
//        synchronousTask.doTaskTwo();
//        synchronousTask.doTaskThree();
//    }


    /**
     * 异步调用
     * @throws Exception
     */
//    @Test
//    public void asynchronousTest() throws Exception {
//        asynchronousTask.doTaskOne();
//        asynchronousTask.doTaskTwo();
//        asynchronousTask.doTaskThree();
//    }


    /**
     * 异步回调
     * @throws Exception
     */
    @Test
    public void asynchronousCallbackTest() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = asynchronousCallbackTask.doTaskOne();
        Future<String> task2 = asynchronousCallbackTask.doTaskTwo();
        Future<String> task3 = asynchronousCallbackTask.doTaskThree();

        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }
}

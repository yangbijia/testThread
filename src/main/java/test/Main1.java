package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main1 {
    public static void main(String[] args) {
        int corePoolSize = 10,maximumPoolSize = 20;
        long keepAliveTime = 10;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, workQueue, new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return null;
            }
        });


    }

    public int  test(){
        List<String> list = new ArrayList<String>();
        return 0;
    }
}

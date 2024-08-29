package org.sigar.designPatterns.CreationalPatterns.Singleton;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class SingletonDemoWithThreads {

    public static void main(String[] args) throws InterruptedException {
        //testSingletonWithRunnable();
        testSingletonWithExecutorService();
    }
    static public void testSingletonWithExecutorService() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int ind = 0; ind < 100; ind++) {
            executorService.submit(()->{
                SingletonEx ex = SingletonEx.getInstance();
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        }
        static public void testSingletonWithRunnable() throws InterruptedException {

            SingletonEx[] ex = new SingletonEx[10];
            Runnable task = ()->{
                 //int ind  = Math.random(10);
                int randomValue = (int) (Math.random() * 10);
                Random random = new Random();
                int ind = random.nextInt(10);
                 ex[ind]= SingletonEx.getInstance();
            };
            Thread[] threads = new Thread[10];
            for(int ind = 0;ind<10;ind++) {
                threads[ind] = new Thread(task);
                threads[ind].start();
            }
            sleep(300);
            for(Thread t: threads) t.join();
        }
}

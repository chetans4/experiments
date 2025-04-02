package org.wigs.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    private BlockingQueue<Runnable> taskQueue;
    private List<WorkerThread> workerThreads;
    private volatile boolean isShutdown = false;

    public MyThreadPool(int poolSize, int maxNoOfTasks){
        taskQueue = new LinkedBlockingQueue(maxNoOfTasks);
        workerThreads = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThreads.add(workerThread);
            workerThread.start();
        }

    }

    public void submitTask(Runnable task){
        if(!isShutdown){
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown(){
        isShutdown = true;
        for(WorkerThread workerThread : workerThreads){
            workerThread.interrupt();
        }
    }

    private class WorkerThread extends Thread{

        @Override
        public void run() {

            while(!isShutdown || !taskQueue.isEmpty()){
                try {
                    Runnable task = taskQueue.poll(1, TimeUnit.SECONDS);
                    if(task != null){
                        task.run();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}

class UseMyThreadPool{
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(3, 5);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            myThreadPool.submitTask(() -> {
                System.out.println("Executing task "+ taskId +", by Thread : "+Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            });
        }

        Thread.sleep(5000);
        myThreadPool.shutdown();
    }
}

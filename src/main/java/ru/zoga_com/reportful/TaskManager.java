package ru.zoga_com.reportful;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {
    private static int CPU_COUNT = Integer.valueOf(Runtime.getRuntime().availableProcessors() / 2);
    private static ExecutorService threadPool = Executors.newFixedThreadPool(CPU_COUNT);

    public TaskManager(Runnable runnable) {
        this.queueTask(runnable);
    }

    private void queueTask(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public static int getUsedThreads() {
        return CPU_COUNT;
    }
}

package java8_advantage.concurent_collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RunBlocking {
    public static void main(String[] args) {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
        new Thread() {
            public void run() {
                for (int i = 1; i < 10; i++) {
                    try {
                        queue.put("Java" + i); // добавление 3-х
                        System.out.println("Element " + i + " added" + "; current capacity is " + queue.size());
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    // извлечение одного
                    for (int i = 0; i < 9; i++) {
                        Thread.sleep(700);
                        System.out.println("Element " + queue.take() + " took");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}


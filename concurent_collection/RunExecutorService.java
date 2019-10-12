package java8_advantage.concurent_collection;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class RunExecutorService {

    static class BargainSale {
        private Queue<String> goods = new LinkedList<>(Arrays.asList("TV", "PC", "Laptop", "Watch", "Samsung", "Nokia"));

        public String takeGood() {
            return goods.poll();
        }

    }

    static class HungryCustomer implements Callable<String> {
        private BargainSale mBargainSale;
        private String threadName;

        public HungryCustomer(int threadNumber, BargainSale bargainSale) {
            this.threadName = "thread_" + threadNumber;
            this.mBargainSale = bargainSale;
        }

        public String getThreadName() {
            return threadName;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(100);
            String good = null;
            synchronized (this.mBargainSale) {
                good = threadName + "has taken " + this.mBargainSale.takeGood();
            }
            return good;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Future<String>> list = new ArrayList<Future<String>>();
        ExecutorService es = Executors.newFixedThreadPool(2);
        BargainSale bargainSale = new BargainSale();
        System.out.println(bargainSale.goods);
        list.add(es.submit(new HungryCustomer(1, bargainSale)));
        list.add(es.submit(new HungryCustomer(2, bargainSale)));
        list.add(es.submit(new HungryCustomer(1, bargainSale)));
        list.add(es.submit(new HungryCustomer(2, bargainSale)));
        list.add(es.submit(new HungryCustomer(1, bargainSale)));
        list.add(es.submit(new HungryCustomer(2, bargainSale)));
        list.add(es.submit(new HungryCustomer(1, bargainSale)));
        list.add(es.submit(new HungryCustomer(2, bargainSale)));
        es.shutdown();
        for (Future<String> future : list) {
            System.out.println(future.get());
        }
    }
}

package java8_advantage.concurent_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Class demonstrates work of CountDownLatch
 */
public class RunCountDownLatch {
    static class Cashier extends Thread {
        private List<Customer> customers;

        public Cashier(List<Customer> customers) {
            this.customers = customers;
        }

        @Override
        public void run() {
            for (Customer customer : customers) {
                for (int i = 1; i <= customer.mGoods; i++) {
                    customer.getCountDownLatch().countDown();
                }
                try {
                    sleep(300*customer.mGoods);
                } catch (InterruptedException ie) {
                    System.err.println(customer.getName() + " seems doesn't have money");
                }
                System.out.println("Cashier counted price for " + customer.getName());
            }
        }
    }

    static class Customer extends Thread {
        private CountDownLatch countDownLatch;
        int mGoods;

        public Customer(int number, int goods) {
            super("Customer_" + number);
            this.mGoods = goods;
            this.countDownLatch = new CountDownLatch(goods);
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + " in the queue");
                TimeUnit.MILLISECONDS.sleep(600*mGoods);
                this.countDownLatch.await();
                System.out.println(this.getName() + " paid the bill and left");
            } catch (InterruptedException ie) {
                System.out.println(this.getName() + " left queue");
            }
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public int getmGoods() {
            return mGoods;
        }
    }

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            customers.add(new Customer(i, 2 + i));
            customers.get(i - 1).start();
        }
        new Cashier(customers).start();
    }
}

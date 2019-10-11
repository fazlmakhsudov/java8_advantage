package java8_advantage.concurent_collection;

import java.security.spec.ECField;
import java.util.concurrent.Exchanger;

public class RunExchanger {
    private static Exchanger<String> exchanger = new Exchanger<>();

    static class Alex extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Alex has seen acquaintance suddenly");
                String introduce = "My name is Alex";
                String friendName;
                synchronized (introduce) {
                    friendName = exchanger.exchange(introduce);
                }
                System.out.println("Nice to meet you " + friendName + ", told Alex");
            } catch (InterruptedException ie) {
                System.err.println("Friend hasn't told his name to Alex");
            }
        }
    }

    static class John extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("John has seen acquaintance suddenly");
                String introduce = "My name is John";
                String friendName;
                synchronized (introduce) {
                    friendName = exchanger.exchange(introduce);
                }
                System.out.println("Nice to meet you " + friendName + " too, told John");
            } catch (InterruptedException ie) {
                System.err.println("Friend hasn't told his name to Alex");
            }
        }
    }

    public static void main(String[] args) {
        new Alex().start();
        new John().start();
    }
}

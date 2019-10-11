package java8_advantage.concurent_collection;

import javax.sound.midi.Soundbank;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RunCyclicBarrier {
    static class Runner extends Thread {
        private CyclicBarrier mCyclicBarrier;

        Runner(String name, CyclicBarrier cyclicBarrier) {
            super(name);
            this.mCyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + " has come and is ready to run");
                this.mCyclicBarrier.await();
                System.out.println(this.getName() + " has started running");
            } catch (BrokenBarrierException | InterruptedException bbeIe) {
                System.out.println("some problems has occured with " + this.getName());
            }
        }
    }

    static class JudgeOfRunners {
        private CyclicBarrier mCyclicBarrier;

        public JudgeOfRunners(int runnersNumber) {
            this.mCyclicBarrier = new CyclicBarrier(runnersNumber, new Runnable() {
                @Override
                public void run() {
                    System.err.println("Judge blows the whistle to start race");
                }
            });
            ;
        }

        public CyclicBarrier getmCyclicBarrier() {
            return mCyclicBarrier;
        }
    }

    public static void main(String[] args) {
        JudgeOfRunners judgeOfRunners = new JudgeOfRunners(4);
        for (int i = 1; i <= 4; i++) {
            new Runner("Runner_" + i, judgeOfRunners.getmCyclicBarrier()).start();
        }
    }
}

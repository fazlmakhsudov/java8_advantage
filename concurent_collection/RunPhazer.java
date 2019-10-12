package java8_advantage.concurent_collection;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class RunPhazer {

    static class MultiCompetion extends Thread {
        private Phaser mPhaser;

        public MultiCompetion(Phaser phaser) {
            this.mPhaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.print("Competion_1 begins with: ");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print("\nCompetion_1 ended.\nCompetion_2 begins with: ");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print("\nCompetion_2 ended.\nCompetion_3 begins with: ");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print("\nCompetion_3 ended.\nCompetion_4 begins with: ");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print("\nCompetion_4 ended.\nCompetion_5 begins with: ");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.println("\nCompetion_5 ended.\nCompetitions are over.");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndDeregister();
            } catch (InterruptedException ie) {
                System.out.println("competions have failed");
            }

        }
    }

    static class Member extends Thread {
        private Phaser mPhaser;

        public Member(Phaser phaser, int memberNumber) {
            super("Member_" + memberNumber);
            this.mPhaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.print(" " + this.getName() + ",");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print(" " + this.getName() + ",");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print(" " + this.getName() + ",");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print(" " + this.getName() + ",");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.print(" " + this.getName() + ",");
                TimeUnit.SECONDS.sleep(4);
                this.mPhaser.arriveAndAwaitAdvance();
                System.out.println(this.getName() + " is finished competions.");
                this.mPhaser.arriveAndDeregister();
            } catch (InterruptedException ie) {
                System.out.println(this.getName() + " has failed");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Phaser phaser = new Phaser();
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        new MultiCompetion(phaser).start();
        for (int i = 1; i <= 10; i++) {
            new Member(phaser, i).start();
        }
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndDeregister();
    }
}

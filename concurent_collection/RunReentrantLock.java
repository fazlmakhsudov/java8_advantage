package java8_advantage.concurent_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunReentrantLock {

    static class Note {
        StringBuilder sSb = new StringBuilder();

        public StringBuilder getsSb() {
            return sSb;
        }

        public void addLetter(String letter) {
            this.sSb.append(letter);
        }

        @Override
        public String toString() {
            return "Note{" +
                    "sSb=" + sSb +
                    '}';
        }
    }

    static class LetterWriter extends Thread {
        private int mLetter;
        private Note sNote;

        LetterWriter(int letter, Note note) {
            this.mLetter = letter;
            this.sNote = note;
        }

        @Override
        public void run() {
            Lock reentrantLock = new ReentrantLock();
            Condition condition = reentrantLock.newCondition();
            for (int i = 0; i < 10; i++) {
                if (reentrantLock.tryLock()) {
                    sNote.addLetter((char) mLetter + "#");
                    reentrantLock.unlock();
                }
            }
        }
    }

    public static void collectLetters(int threadNumber, Note sNote) {
        for (int i = 97; i < 97 + threadNumber; i++) {
            new LetterWriter(i, sNote).start();
        }
    }

    public static void main(String[] args) {
        Note note = new Note();
        collectLetters(5, note);
        System.out.println(note);
    }
}

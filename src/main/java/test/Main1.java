package test;

import java.util.concurrent.atomic.AtomicInteger;

public class Main1 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        i.incrementAndGet();

    }
}

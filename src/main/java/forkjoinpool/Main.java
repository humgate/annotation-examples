package forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] arr = initArray(20);
        ForkJoinSummator fjSummator = new ForkJoinSummator(arr);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        System.out.println(forkJoinPool.invoke(fjSummator));
    }

    private static int[] initArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 1;
        }
        return array;
    }
}

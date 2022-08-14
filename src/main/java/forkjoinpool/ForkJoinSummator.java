package forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSummator extends RecursiveTask<Integer> {
    private final int[] array;

    public ForkJoinSummator(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {

        if (array.length > 5) {
            var f1 = new ForkJoinSummator(Arrays.copyOfRange(array, 0, array.length / 2));
            var f2 = new ForkJoinSummator(Arrays.copyOfRange(array, array.length / 2, array.length));
            f1.fork();
            f2.fork();
            return f1.join() + f2.join();
        }
        return Arrays.stream(array).sum();
    }
}

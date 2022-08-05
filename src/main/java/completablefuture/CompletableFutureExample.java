package completablefuture;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Blocking way
        final FutureTask<String> stringFutureTask = new FutureTask<>(() -> calcSomeString());
        executorService.submit(stringFutureTask);
        System.out.println(stringFutureTask.get() + " " + LocalDateTime.now()); //blocks here
        System.out.println("Started separate thread calculation " + LocalDateTime.now());


        // Non-blocking way (async)
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> calcSomeString(), executorService)
                .thenAccept(s -> System.out.println(s + " " + LocalDateTime.now()));
        System.out.println("Started separate thread calculation " + LocalDateTime.now());

        executorService.shutdown();
    }

    public static String calcSomeString()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Done";
    }
}

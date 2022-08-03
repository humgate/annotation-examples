package volatileexample;

/*
volatile guarantees:
1 - every read and write of a volatile variable will be from/to RAM, not from/to local CPU cache
2 - No optimization of execution order is done, capable to break "happens-before" condition
3 - So all variables visible to thread1 prior to volatile variable write, will be also
written to RAM. As well as all variables visible to thread2 before volatile variable read, will be reread from RAM.
 */
public class VolatileExample {
    /*volatile*/ static boolean val;
    static int num;

    /*
     * This method executes in thread t1.
     * If val is not volatile:
     * 1 - t1 can be started to execute on CPU1
     * 2 - local copy of val is created in CPU1 cash with default value = false
     * 3 - local copy of val at CPU1 is set to true
     * 4 - thread terminates
     * 5 - val value in RAM is never changed to true
     *
     *
     * if val is volatile:
     * 1 - t1 can be started to execute on CPU1
     * 2 - local copy of val is created in CPU1 cash
     * 3 - local copy of val at CPU1 is set to true and
     * 4 - val, along-with num are IMMEDIATELY WRITTEN TO RAM
     * 5 - thread terminates
     *
     */
    public static void writeValues (boolean val, int num)  {
        VolatileExample.num = num;
        VolatileExample.val = val;
    }

    /*
     * This method executes in thread t2.
     * If val is not volatile:
     * 1 - t2 can be started to execute on CPU2
     * 2 - local copy of val is created in CPU2 local cash with default value (false)
     * 3 - t2 enters in infinite while cycle checking value of its own local copy of val
     * 4 - t2 will run forever because val will be always false
     *
     * If val is volatile:
     * 1 - t2 can be started to execute on CPU2
     * 2 - local copy of val is created in CPU2 cash with default value (false)
     * 3 - t2 enters in while cycle checking value of val IN RAM
     * 4 - sooner or later val in RAM is set to true by t1 along with num value
     * 5 - t2 printlns num value (3)
     * 6 - t2 terminates
     */

    public static void readValues () {
        while (!val);
        System.out.println("num == " + num);
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()-> writeValues(true,3));
        Thread t2 = new Thread(()-> readValues());

        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}

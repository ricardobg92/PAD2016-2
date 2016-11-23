/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo7;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rgomes
 */
public class Exemplo7 {

    /**
     * @param args the command line arguments
     */
    private static List<String> list = new CopyOnWriteArrayList<String>();

    public static void main(String[] args) throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(5);
        e.execute(new WriterTask());
        e.execute(new WriterTask());
        e.execute(new WriterTask());
        e.execute(new WriterTask());
        e.execute(new WriterTask());

        e.awaitTermination(2, TimeUnit.SECONDS);
    }

    static class WriterTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 25000; i ++) {
                System.out.println(i);
                list.add("a");
            }
        }
    }
}

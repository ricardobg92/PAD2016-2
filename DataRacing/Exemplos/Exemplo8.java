/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo8;

/**
 *
 * @author rgomes
 */
public class Exemplo8 extends Thread {

    private static int common = 0;

    public synchronized void run(){
        int local = common;
        local++;
        common = local;
    }


    public static void main(String[] args) throws InterruptedException {
        Exemplo8[] allThreads = new Exemplo8[20000];

        for(int i = 0; i < allThreads.length; i++){
            allThreads[i] = new Exemplo8();
        }

        for(Exemplo8 d: allThreads){
            d.start();
        }

        for(Exemplo8 d: allThreads){
            d.join();
        }
        System.out.println(common);
    }
}

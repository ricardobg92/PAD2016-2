/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos_data_race;

/**
 *
 * @author Ricardo
 * adaptado de https://www.cs.umd.edu/class/fall2005/cmsc433/lectures/threadsPart2.pdf
 */
public class exemplo1 extends Thread {
    
    private static int cnt = 0; // shared state
    public void run() {
        int y = cnt;
        cnt = y + 1;
        System.out.println(cnt);
    }
    
    public static void main(String[] args) {
        Thread T1 = new exemplo1(); 
        Thread T2 = new exemplo1();
        T1.start();
        T2.start();
    }
}

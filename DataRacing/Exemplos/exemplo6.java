class BenignDataRace {
   final String s = "blah";

   public void main() {
     class CalcHash implements Runnable {
       public void run() {
         int h = s.hashCode();
       }
     }
     Thread t = new Thread(new CalcHash());
     Thread t2 = new Thread(new CalcHash());

     t.start();
     t2.start();
   }

}
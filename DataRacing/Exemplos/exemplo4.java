//código obtido em
//http://stackoverflow.com/questions/10309186/why-does-this-thread-data-race

public class SyncMethodDataRace extends Thread {

    private static int common = 0;

    public synchronized void run(){
        int local = common;
        local++;
        common = local;
    }


    public static void main(String[] args) throws InterruptedException {
        SyncMethodDataRace[] allThreads = new SyncMethodDataRace[20000];

        for(int i = 0; i < allThreads.length; i++){
            allThreads[i] = new SyncMethodDataRace();
        }

        for(SyncMethodDataRace d: allThreads){
            d.start();
        }

        for(SyncMethodDataRace d: allThreads){
            d.join();
        }

        System.out.println(common);
    }
}
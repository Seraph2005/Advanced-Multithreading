package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {

    Semaphore smf;

    public Operator(String name, Semaphore s) {
        super(name);
        smf = s;
    }

    @Override
    public void run() {
        try {
            smf.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++)
        {
            Resource.accessResource();
            //just checking
            //System.out.println(Thread.currentThread().getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        smf.release();
    }
}

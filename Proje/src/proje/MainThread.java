/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gozde
 */
public class MainThread extends MultiThreading implements Runnable {

    int kapasite;
    public int doluluk;

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public int getDoluluk() {
        return doluluk;
    }

    public void setDoluluk(int doluluk) {
        this.doluluk = doluluk;
    }
    
    public MainThread() {
        this.kapasite = 10000;
        this.doluluk = 0;
    }

   public static AtomicInteger x = new AtomicInteger(0);
   ReentrantLock lock=new  ReentrantLock();
   
   public synchronized void mainThreadIslemi()
   {

        lock.lock();
        int sayi = (int) (Math.random()*10000);

       int a=x.get();
        a=a+sayi;
        if(a>10000)
        {
            x.set(10000);
        }
        else
        {
            x.set(a);

        }

        
        lock.unlock();
        try {

            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        lock.lock();
        int sayi2 = (int) (Math.random()*50);
        if(a>0)
        {
            if(sayi2>=a)
            {
                
                a=0;
            }
            else
            {
                a=a-sayi2;
            }
            x.set(a);
            try {

            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        lock.unlock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
   }
        
   

    @Override
    public void run() {
        int istek =0;
        while(x.get() <=10000)
        {
            
            if(Thread.currentThread().getName().equals("Thread-0"))
            {
                mainThreadIslemi();
            }
        }
        
    }
 
}
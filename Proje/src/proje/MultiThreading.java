/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gozde
 */

public  class MultiThreading {
    
    public static ArrayList<Thread> subthreads = new ArrayList<Thread>();
    public static ArrayList<Integer> subkapasite = new ArrayList<Integer>();
    public static ArrayList<Integer> subdoluluk = new ArrayList<Integer>();
    
    public  int maindoluluk=0;
    public int mainkapasite=10000;
    
    //static Arayuz arayuz = new Arayuz();
    
    
    public synchronized static void main(String[] args) throws InterruptedException {
        

        //arayuz.setVisible(true);

        MainThread mT = new MainThread();
        SubThread sT;

        Thread mainThread = new Thread(mT);
        mainThread.setPriority(Thread.MAX_PRIORITY);

        mainThread.start();
        
        for(int i=1;i<5;i++)
        {
            SubThread st = new SubThread();
            //st.run();
            Thread t2=new Thread(st);
            subkapasite.add(5000);
            subdoluluk.add(0);
            t2.start();
            subthreads.add(t2);

        }
        
      }
        

    public int getMaindoluluk() {
        return maindoluluk;
      
    }

    public void setMaindoluluk(int maindoluluk) {
        this.maindoluluk = maindoluluk;
    }
   
    
}

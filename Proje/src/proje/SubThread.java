/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author gozde
 */
public class SubThread extends MultiThreading implements Runnable {

    private int kapasite2;
    private int doluluk2;
    ReentrantLock lock1=new  ReentrantLock();
    ReentrantLock lock2=new  ReentrantLock();
    ReentrantLock lock3=new  ReentrantLock();
    ReentrantLock lock4=new  ReentrantLock();

    public SubThread() {
    }
    
    public synchronized void subTreadIslemler2() throws InterruptedException
    {
          int id=0;
         while(true)
        {
           
            boolean kontrol=true;
            int t=0;
            while(kontrol && t<subthreads.size())
            {
                if(subthreads.get(t).getName().equals(Thread.currentThread().getName()))
                {
                    id=t;
                    //System.out.println("ID"+id);
                    kontrol=false;
                }
                t++;
            }
           
           
            doluluk2 = MainThread.x.get();
            int a = doluluk2; 
          if( id!=2 && id!=3)
          {
            lock1.lock();
            lock2.lock();
              if(a>0)
              {
                  
                  try{
                  int sayi2 = (int) (Math.random()*3000);
                  if(sayi2>a)
                  {
                    subdoluluk.set(id,a);
                    a=0;
                    MainThread.x.set(a);
                  }
                  else
                  {
                      a = a-sayi2;
                    MainThread.x.set(a);
                    subdoluluk.set(id, subdoluluk.get(id)+sayi2);
                  }
                   Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(subdoluluk.get(id)>0){
                    int sayi3 = (int) (Math.random()*50);
                    if(sayi3>subdoluluk.get(id))
                    {
                        subdoluluk.set(id,0);
                    }
                    else
                    {
                        subdoluluk.set(id, subdoluluk.get(id)-sayi3);
                    }
                    Thread.sleep(300);
                    
                }
                Thread.sleep(1000);
                lock1.unlock();
                lock2.unlock();
                
          }
          }
          else if(id==2)
          {
              lock1.lock();
              lock2.lock();
              lock3.lock();
              lock4.lock();
              for(int i=0;i<subdoluluk.size();i++)
              {
                  if(subdoluluk.get(i)>=3500)
                  {                     
                    int d=subdoluluk.get(i)/2;
                    subdoluluk.set(i,subdoluluk.get(i)-d);
                    SubThread st = new SubThread();
                    Thread t2=new Thread(st);
                    subkapasite.add(5000);
                    subdoluluk.add(d);
                    t2.start();
                    subthreads.add(t2);
                    
                  }
                  if(subdoluluk.get(i)==0 && (subdoluluk.size()-2)>2)
                  {
                      
                      if(!(subthreads.get(i).getName().equals("Thread-3") && subthreads.get(i).getName().equals("Thread-4"))){
                          subdoluluk.remove(i);
                          subkapasite.remove(i);
                          subthreads.remove(i);
                      }
                  }
              }
              Thread.sleep(500);
              lock1.unlock();
              lock2.unlock();
              lock3.unlock();
              lock4.unlock();
          }
          else if(id==3)
          {
              lock1.lock();
              lock2.lock();
              lock3.lock();
              lock4.lock();
              for(int i=0;i<subdoluluk.size();i++)
              {
                  if(i!=2 && i!=3)
                  {
                  int d=(int)(((float)subdoluluk.get(i)/5000)*100);
                  String threadIsım="Alt sunucu "+(i+1);
                  System.out.println(threadIsım+":  %"+d);
                  }
                  
              }
              Thread.sleep(1000);
              lock1.unlock();
              lock2.unlock();
              lock3.unlock();
              lock4.unlock();
          }
            
    }
          
    }
    
    
    @Override
    public void run() {

        try {
            subTreadIslemler2();
        } catch (InterruptedException ex) {
            Logger.getLogger(SubThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
          
        
        
    }
    


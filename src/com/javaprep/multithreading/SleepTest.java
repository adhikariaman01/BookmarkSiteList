/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.javaprep.multithreading;

/**
 * Created by amanadhikari on 10/30/16.
 */
public class SleepTest extends Thread{
    public void run(){
        for (int i=0;i<=10;i++){
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("e = " + e);
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SleepTest st = new SleepTest();
        SleepTest s2 = new SleepTest();

        st.start();
        s2.start();
    }
}

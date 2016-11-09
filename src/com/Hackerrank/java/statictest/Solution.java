/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.Hackerrank.java.statictest;

import java.util.Scanner;

/**
 * Created by amanadhikari on 11/3/16.
 */
public class Solution {
    static int B;
    static int H;
    static boolean flag = false;
    public Solution(){}
    public Solution(int B, int H){
        this.B = B;
        this.H = H;
        this.flag = true;
    }
    static {
        Scanner in = new Scanner(System.in);
        int b = in.nextInt();
        int h = in.nextInt();
        if(b<=0 || h<=0){
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }else{
            new Solution(b,h);
        }
        in.close();
    }

    public static void main(String []arg){
        if (Solution.flag){
            int area = B*H;
            System.out.println(area);
        }
    }
}

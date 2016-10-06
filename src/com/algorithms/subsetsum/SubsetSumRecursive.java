/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.algorithms.subsetsum;

/**
 * Created by amanadhikari on 10/5/16.
 */
public class SubsetSumRecursive {
    public static void main(String[] args) {
        int[] a = new int[]{3,5,8,9,11};
        boolean res = recSubset(a,15,0,0);
        System.out.println("res = " + res);
    }

    //i is the index of the next element to consider
    //sumSoFar is the sum of elements included in the solution so far.
    static boolean recSubset(int[] a, int target, int i, int sumSoFar) {
        if (sumSoFar == target) return true;
        if (i == a.length) return false;
        boolean with = recSubset(a, target, i+1, sumSoFar + a[i]);
        boolean without = recSubset(a, target, i+1, sumSoFar);
        return (with || without);
    }
}

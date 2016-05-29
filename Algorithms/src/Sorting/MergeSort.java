/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import java.util.Arrays;

/**
 *
 * @author sumittal
 */
public class MergeSort {

    public static void main(String args[]) {
        int[] arr = new int[]{10, 20, 5, 4, 25, 3, 35, 1, 55};
        int[] result = mergesort(arr);
        int length = result.length;
        for (int i = 0; i < length; i++) {
            System.out.println(result[i]);
        }
    }

    private static int[] mergesort(int[] arr) {
        int start = 0;
        int end = arr.length;
        int mid = end / 2;

        if (mid == 0) {
            return arr;
        } else {
            return combine(mergesort(Arrays.copyOfRange(arr, start, mid)),
                    mergesort(Arrays.copyOfRange(arr, mid, end)));
        }
    }

    private static int[] combine(int[] arr1, int[] arr2) {
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;
        int arr1Index = 0;
        int arr2Index = 0;
        int index = 0;
        int[] arr = new int[arr1Length + arr2Length];

        while (arr1Index < arr1Length && arr2Index < arr2Length) {
            if (arr1[arr1Index] < arr2[arr2Index]) {
                arr[index] = arr1[arr1Index];
                arr1Index++;
            } else {
                arr[index] = arr2[arr2Index];
                arr2Index++;
            }
            index++;
        }

        if (arr1Index < arr1Length) {
            for (int i = arr1Index; i < arr1Length; i++) {
                arr[index] = arr1[i];
                index++;
            }
        }

        if (arr2Index < arr2Length) {
            for (int i = arr2Index; i < arr2Length; i++) {
                arr[index] = arr2[i];
                index++;
            }
        }
        return arr;
    }
}
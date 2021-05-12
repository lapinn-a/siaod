package com.company;

import java.util.Random;

public class Matrix {
    public static int[][] matrix(int m, int n, int minLimit, int maxLimit) {
        maxLimit++;
        int[][] matr = new int[n][m];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matr[i][j] = random.nextInt(maxLimit - minLimit) + minLimit;
            }
        }
        return matr;
    }

    public static int[] selectionSort(int[] arr){
        int temp;
        for(int pos = 0; pos < arr.length - 1; pos++) {
            int min = arr[pos];
            int index = pos;
            for (int i = pos + 1; i < arr.length; i++) {
                if (arr[i] < min) {
                    index = i;
                    min = arr[i];
                }
            }
            temp = arr[pos];
            arr[pos] = arr[index];
            arr[index] = temp;
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr){
        int temp;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j > 0; j--){
                if(arr[j] > arr[j - 1]){
                    break;
                } else {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr){
        int temp;
        for(int i = arr.length - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] ShellSort(int[] arr){
        int temp;
        int d = arr.length;
        boolean order = false;
        while(!order || d > 1){
            order = true;
            d = (int)Math.ceil((double)d / 2);
            for(int j = 0; j < arr.length - d; j++){
                if(arr[j] > arr[j + d]){
                    temp = arr[j];
                    arr[j] = arr[j + d];
                    arr[j + d] = temp;
                    order = false;
                }
            }
        }
        return arr;
    }

    public static int[] tournamentSort(int[] arr){
        int num = 1;
        int len = arr.length;
        while(len > 1){
            len = (int)Math.ceil((float)len / 2);
            num++;
        }
        int[][] tree = new int[num][];
        len = arr.length;
        for(int i = 0; i < num; i++){
            if(len != 1 && len % 2 != 0){
                len++;
                tree[i] = new int[len];
                tree[i][tree[i].length - 1] = Integer.MAX_VALUE;
            } else {
                tree[i] = new int[len];
            }
            len = (int)Math.ceil((float)len / 2);
        }
        System.arraycopy(arr, 0, tree[0], 0, arr.length);
        num = Integer.MAX_VALUE;
        for(int k = 0; k < arr.length; k++) {
            for (int i = 0; i < tree.length - 1; i++) {
                for (int j = 0; j < tree[i].length; j += 2) {
                    if(i == 0 && tree[i][j] == num){
                        tree[0][j] = Integer.MAX_VALUE;
                        num = Integer.MAX_VALUE;
                    }
                    if(i == 0 && tree[i][j + 1] == num){
                        tree[0][j + 1] = Integer.MAX_VALUE;
                        num = Integer.MAX_VALUE;
                    }
                    if (tree[i][j] > tree[i][j + 1]) {
                        tree[i + 1][j / 2] = tree[i][j + 1];
                    } else {
                        tree[i + 1][j / 2] = tree[i][j];
                    }
                }
            }
            num = tree[tree.length - 1][0];
            arr[k] = num;
        }
        return arr;
    }

    public static int[] quickSort(int[] arr){
        if(arr.length <= 1){
            return arr;
        }
        int index = (int)Math.floor((float)arr.length / 2);
        int temp;
        for(int i = 0; i < index; i++){
            if(arr[i] > arr[index]){
                temp = arr[i];
                for(int j = i + 1; j <= index; j++){
                    arr[j - 1] = arr[j];
                }
                arr[index] = temp;
                index--;
                i--;
            }
        }
        for(int i = arr.length - 1; i > index; i--){
            if(arr[i] < arr[index]){
                temp = arr[i];
                for(int j = i - 1; j >= index; j--){
                    arr[j + 1] = arr[j];
                }
                arr[index] = temp;
                index++;
                i++;
            }
        }
        int[] left = new int[index];
        int[] right = new int[arr.length - index - 1];
        System.arraycopy(arr, 0, left, 0, index);
        System.arraycopy(arr, index + 1, right, 0, arr.length - index - 1);
        System.arraycopy(quickSort(left), 0, arr, 0, index);
        System.arraycopy(quickSort(right), 0, arr, index + 1, arr.length - index - 1);
        return arr;
    }

    public static int[] heapSort(int[] arr){
        int temp;
        int state = arr.length - 1;
        while(state > 1) {
            for (int i = state; i > 0; i--) {
                if (arr[i] > arr[(i - 2 + (i % 2)) / 2]) {
                    temp = arr[i];
                    arr[i] = arr[(i - 2 + (i % 2)) / 2];
                    arr[(i - 2 + (i % 2)) / 2] = temp;
                }
            }
            temp = arr[0];
            arr[0] = arr[state];
            arr[state] = temp;
            state--;
        }
        return arr;
    }
}

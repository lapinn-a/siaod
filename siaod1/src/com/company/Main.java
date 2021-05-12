package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        String[] strArray;
        int[] intArray;
        while(true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Треугольник с максимальным периметром");
            System.out.println("2. Максимальное число");
            System.out.println("3. Сортировка диагоналей");
            System.out.println("0. Выход");
            sc = new Scanner(System.in);
            int operation = sc.nextInt();
            switch (operation) {
                case 1:
                    System.out.print("Введите массив чисел через пробел: ");
                    sc.useDelimiter("\n");
                    strArray = sc.next().split(" ");
                    intArray = new int[strArray.length];
                    for(int i = 0; i < strArray.length; i++){
                        intArray[i] = Integer.parseInt(strArray[i]);
                    }
                    System.out.println(maxPerimeter(intArray));
                    break;
                case 2:
                    System.out.print("Введите массив чисел через пробел: ");
                    sc.useDelimiter("\n");
                    strArray = sc.next().split(" ");
                    intArray = new int[strArray.length];
                    for(int i = 0; i < strArray.length; i++){
                        intArray[i] = Integer.parseInt(strArray[i]);
                    }
                    System.out.println(maxNumber(intArray));
                    break;
                case 3:
                    int[][] matrix = Matrix.matrix(6, 4, 1, 9);
                    System.out.println("Исходная матрица:");
                    for(int i = 0; i < matrix.length; i++){
                        for(int j = 0; j < matrix[0].length; j++){
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                    }
                    matrix = diagSort(matrix);
                    System.out.println("Результат:");
                    for(int i = 0; i < matrix.length; i++){
                        for(int j = 0; j < matrix[0].length; j++){
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                    return;
            }
            sc.useDelimiter("\n");
            sc.next();
        }
    }

    public static int maxPerimeter(int[] arr){
        int[] tempArr = new int[arr.length];
        for(int cnt = 0; cnt < arr.length - 2; cnt++) {
            System.arraycopy(arr, 0, tempArr, 0, arr.length);
            int[] maxTriangle = new int[3];
            int[] maxTriangleIndex = new int[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < tempArr.length; j++) {
                    if (tempArr[j] > maxTriangle[i]) {
                        maxTriangle[i] = tempArr[j];
                        maxTriangleIndex[i] = j;
                    }
                }
                tempArr[maxTriangleIndex[i]] = 0;
            }
            if (maxTriangle[0] < maxTriangle[1] + maxTriangle[2]) {
                return maxTriangle[0] + maxTriangle[1] + maxTriangle[2];
            } else {
                arr[maxTriangleIndex[0]] = 0;
            }
        }
        return 0;
    }

    public static String maxNumber(int[] arr){
        StringBuilder out = new StringBuilder();

        for(int i = 0; i < arr.length; i++){ // по количеству элементов массива
            int largestNumberIndex = 0;
            int len = Integer.MAX_VALUE;
            int firstDigit = 0;

            for(int j = 0; j < arr.length; j++){ // отбор подходящего числа из оставшихся
                int newFirstDigit = arr[j];
                int newLen = 1;

                while(newFirstDigit / 10 > 0) { // первый разряд и длина нового числа
                    newFirstDigit /= 10;
                    newLen++;
                }

                if(newFirstDigit > firstDigit){ // если первый разряд нового больше, выбираем новое
                    len = newLen;
                    firstDigit = newFirstDigit;
                    largestNumberIndex = j;

                } else if(newFirstDigit == firstDigit){      // если первые разряды чисел равны, выравниваем оба числа
                    int oldNumber = arr[largestNumberIndex]; // по левому разряду и выбираем из них наибольшее
                    int newNumber = arr[j];
                    if(len > newLen){
                        for(int k = 0; k < len - newLen; k++){
                            if(newNumber == 0){
                                break;
                            }
                            newNumber *= 10;
                        }
                    } else {
                        for(int k = 0; k < newLen - len; k++){
                            if(oldNumber == 0){
                                break;
                            }
                            oldNumber *= 10;
                        }
                    }
                    if((oldNumber == newNumber) && (len > newLen)) {
                        len = newLen;
                        firstDigit = newFirstDigit;
                        largestNumberIndex = j;
                    }
                    if(oldNumber < newNumber) {
                        len = newLen;
                        firstDigit = newFirstDigit;
                        largestNumberIndex = j;
                    }
                }
            }
            out.append(arr[largestNumberIndex]);
            arr[largestNumberIndex] = -1;
        }
        return out.toString();
    }

    public static int[][] diagSort(int[][] arr){
        //int[][] out = new int[arr.length][arr[0].length];
        int len = 1;
        int change = 1;
        for(int i = arr.length - 1; i > 0; i--){
            int[] diag = new int[len];
            for(int j = 0; j < len; j++){
                diag[j] = arr[i + j][j];
                if((i + j == arr.length - 1) && (j == arr[0].length - 1)){
                    change--;
                }
            }
            diag = Matrix.quickSort(diag);
            for(int j = 0; j < len; j++){
                arr[i + j][j] = diag[j];
            }
            len += change;
        }
        change--;
        for(int i = 0; i < arr[0].length; i++){
            int[] diag = new int[len];
            for(int j = 0; j < len; j++){
                diag[j] = arr[j][i + j];
                if((j == arr.length - 1) && (i + j == arr[0].length - 1)){
                    change--;
                }
            }
            diag = Matrix.quickSort(diag);
            for(int j = 0; j < len; j++){
                arr[j][i + j] = diag[j];
            }
            len += change;
        }
        return arr;
    }
}

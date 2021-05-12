import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] intArr = Matrix.matrix(1000, 1, 0, 100000)[0];
        BinaryTree tree = new BinaryTree();
        for(int i = 0; i < intArr.length; i++){
            tree.addElement(intArr[i]);
        }
        Matrix.quickSort(intArr);

        System.out.println("\n\u001B[47m\u001B[30mИсходный массив:\u001B[0m");
        for(int i = 0; i < intArr.length; i++){
            System.out.print(intArr[i] + " ");
        }

        System.out.println("\n\nДобавление элемента");
        int toAdd = 1234;
        intArr = addToArray(intArr, toAdd);
        tree.addElement(toAdd);
        System.out.print("Элемент " + toAdd + " добавлен в массив ");

        System.out.println("\n\n\u001B[47m\u001B[30mБинарный поиск:\u001B[0m");
        int toFind = intArr[753];
        long time = System.nanoTime();
        int found = binarySearch(intArr, toFind);
        time = System.nanoTime() - time;
        if(found != -1){
            System.out.print("Элемент " + toFind + " найден на позиции " + found);
        } else {
            System.out.print("Элемент " + toFind + " не найден");
        }
        System.out.print("\n" + time + " ns");

        System.out.println("\n\n\u001B[47m\u001B[30mБинарное дерево:\u001B[0m");
        time = System.nanoTime();
        tree.searchElement(toFind);
        time = System.nanoTime() - time;
        System.out.print("Путь к элементу " + toFind + ": " + tree.pathToElement(toFind));
        System.out.print("\n" + time + " ns");

        System.out.println("\n\n\u001B[47m\u001B[30mФибоначчиев поиск:\u001B[0m");
        time = System.nanoTime();
        found = FibonacciSearch(intArr, toFind);
        time = System.nanoTime() - time;
        if(found != -1){
            System.out.print("Элемент " + toFind + " найден на позиции " + found);
        } else {
            System.out.print("Элемент " + toFind + " не найден");
        }
        System.out.print("\n" + time + " ns");

        System.out.println("\n\n\u001B[47m\u001B[30mИнтерполяционный поиск:\u001B[0m");
        time = System.nanoTime();
        found = interpolationSearch(intArr, toFind);
        time = System.nanoTime() - time;
        if(found != -1){
            System.out.print("Элемент " + toFind + " найден на позиции " + found);
        } else {
            System.out.print("Элемент " + toFind + " не найден");
        }
        System.out.print("\n" + time + " ns");

        String[] arr = new String[]{"apple", "banana", "strawberry", "lemon", "cherry", "pear", "watermelon", "orange", "pineapple"};

        System.out.println("\n\n\u001B[47m\u001B[30mПростое рехеширование:\u001B[0m");
        String[] hashTable = new String[9];
        Arrays.fill(hashTable, "");

        System.out.println("Результат первичного хеширования:");
        for(int i = 0; i < arr.length; i++){
            System.out.println("\"" + arr[i] + "\": " + hashFunction(arr[i], 0));
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < hashTable.length; j++){
                int hash = hashFunction(arr[i], j);
                if(hashTable[hash].equals("")){
                    hashTable[hash] = arr[i];
                    break;
                }
                if(j == hashTable.length - 1){
                    System.out.println("Невозможно записать элемент \"" + arr[i] + "\" в хеш-таблицу!");
                }
            }
        }

        System.out.println("\nХеш-таблица:");
        for(int i = 0; i < hashTable.length; i++){
            System.out.println(i + ": \"" + hashTable[i] + "\"");
        }

        System.out.println("\nПоиск элемента");
        String element = "strawberry";
        for(int j = 0; j < hashTable.length; j++){
            int hash = hashFunction(element, j);
            if(hashTable[hash].equals(element)){
                System.out.println("Элемент \"" + element + "\" найден с индексом " + hash);
                break;
            }
            if(j == hashTable.length - 1){
                System.out.println("Элемент не найден");
            }
        }

        System.out.println("\nУдаление элемента");
        element = "watermelon";
        for(int j = 0; j < hashTable.length; j++){
            int hash = hashFunction(element, j);
            if(hashTable[hash].equals(element)){
                hashTable[hash] = "";
                System.out.println("Элемент \"" + element + "\" удалён по индексу " + hash);
                break;
            }
            if(j == hashTable.length - 1){
                System.out.println("Элемент не найден");
            }
        }

        System.out.println("\nДобавление элемента");
        element = "raspberry";
        for(int j = 0; j < hashTable.length; j++){
            int hash = hashFunction(element, j);
            if(hashTable[hash].equals("")){
                hashTable[hash] = element;
                System.out.println("Элемент \"" + element + "\" добавлен по индексу " + hash);
                break;
            }
            if(j == hashTable.length - 1){
                System.out.println("Невозможно записать элемент \"" + element + "\" в хеш-таблицу!");
            }
        }

        System.out.print("\n\u001B[47m\u001B[30mМетод цепочек:\u001B[0m");
        LinkedList<String>[] list = new LinkedList[9];

        for(int i = 0; i < arr.length; i++){
            int hash = hashFunction(arr[i], 0);
            if (list[hash] == null) {
                list[hash] = new LinkedList<>();
            }
            list[hash].add(arr[i]);
        }

        for(int i = 0; i < list.length; i++){
            System.out.print("\n" + i + ": ");
            if(list[i] != null){
                for(int j = 0; j < list[i].size(); j++){
                    System.out.print(list[i].get(j) + " -> ");
                }
            }
            System.out.print("null");
        }

        System.out.println("\n\nПоиск элемента");
        element = "pineapple";
        int hash = hashFunction(element, 0);
        if(list[hash] != null){
            System.out.print(hash + ": ");
            for(int j = 0; j < list[hash].size(); j++){
                String str = list[hash].get(j);
                System.out.print(str + " -> ");
                if(str.equals(element)){
                    System.out.println("Элемент найден.");
                    break;
                }
            }
        } else {
            System.out.println("Элемент не найден.");
        }

        System.out.println("\nУдаление элемента");
        element = "pear";
        hash = hashFunction(element, 0);
        if(list[hash] != null){
            System.out.print(hash + ": ");
            for(int j = 0; j < list[hash].size(); j++){
                String str = list[hash].get(j);
                System.out.print(str + " -> ");
                if(str.equals(element)){
                    list[hash].remove(j);
                    System.out.println("Элемент удалён.");
                    break;
                }
            }
        } else {
            System.out.println("Элемент не найден.");
        }

        System.out.println("\nДобавление элемента");
        element = "raspberry";
        hash = hashFunction(element, 0);
        if (list[hash] == null) {
            list[hash] = new LinkedList<>();
        }
        list[hash].add(element);
        System.out.println("Элемент " + element + " добавлен.");

        System.out.print("\n\u001B[47m\u001B[30mЗадача о восьми ферзях:\u001B[0m\n");
        int[][] field = eightQueens();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] addToArray(int[] arr, int elem){
        int[] out = new int[arr.length + 1];
        System.arraycopy(arr, 0, out, 0, arr.length);
        out[arr.length] = elem;
        return Matrix.quickSort(out);
    }

    public static int binarySearch(int[] sortedArray, int elem){
        int low = 0;
        int high = sortedArray.length;
        int mid;
        while(low < high){
            mid = (low + high) / 2;
            if(sortedArray[mid] == elem){
                return mid;
            } else if(sortedArray[mid] > elem){
                high = mid;
            } else if(sortedArray[mid] < elem){
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int FibonacciSearch(int[] sortedArray, int elem) {
        int low = 0;
        int high = sortedArray.length - 1;
        int f0 = 0;
        int f1 = 1;
        int f2 = 1;
        if(elem < sortedArray[low] || elem > sortedArray[high]){
            return -1;
        }
        if(sortedArray[0] == elem){
            return sortedArray[0];
        }
        while(low < high){
            if(sortedArray[low + f2] == elem){
                return low + f2;
            }
            f0 = f1;
            f1 = f2;
            f2 = f0 + f1;
            if(f2 > sortedArray.length){
                f2 = sortedArray.length - 1;
            }
            if(elem < sortedArray[f2]){
                low += f1 - 1;
                f1 = 0;
                f2 = 1;
            }
        }
        return -1;
    }

    public static int interpolationSearch(int[] sortedArray, int elem) {
        int i = 0;
        int j = sortedArray.length - 1;
        int d;
        while(i < j){
            d = i + ((j - i) * (elem - sortedArray[i])) / (sortedArray[j] - sortedArray[i]);
            if(sortedArray[d] == elem){
                return d;
            } else if(sortedArray[d] > elem){
                j = d - 1;
            } else if(sortedArray[d] < elem){
                i = d + 1;
            }
        }
        return -1;
    }

    public static int hashFunction(String str, int pi){
        return (str.charAt(0) + str.charAt(str.length() - 1) + str.charAt((str.length() - 1) / 2) + pi) % 9;
    }

    public static int[][] eightQueens(){
        int[] field = new int[64];
        field = placeQueen(field, 0);
        int[][] out = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(field[i * 8 + j] == 1){
                    out[i][j] = 1;
                } else {
                    out[i][j] = 0;
                }
            }
        }
        return out;
    }

    public static int[] placeQueen(int[] fieldc, int line){
        for(int i = 0; i < 8; i++){
            int[] field = fieldc.clone();
            if(field[line * 8 + i] == 0){
                for(int j = 0; j < 8; j++){
                    field[line * 8 + j] = 2;
                    field[j * 8 + i] = 2;
                    if((line - i + j >= 0) && (line - i + j < 8)){
                       field[(line - i + j) * 8 + j] = 2;
                    }
                    if((line + i - j >= 0) && (line + i - j < 8)){
                        field[(line + i - j) * 8 + j] = 2;
                    }
                }
                field[line * 8 + i] = 1;
                if(line == 7){
                    return field;
                }
                if(placeQueen(field, line + 1) != null){
                    return placeQueen(field, line + 1);
                }
            }
        }
        return null;
    }
}




















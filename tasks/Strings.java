import java.util.ArrayList;
import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {
        String str;
        Scanner sc;
        while(true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Может ли перестановка строки победить перестановку другой строки");
            System.out.println("2. Самая длинная палиндромная подстрока");
            System.out.println("3. Количество подстрок, которые могут быть записаны как конкатенация");
            System.out.println("0. Выход");
            sc = new Scanner(System.in);
            int operation = sc.nextInt();
            switch (operation) {
                case 1:
                    System.out.print("Введите первую строку: ");
                    sc.useDelimiter("\n");
                    str = sc.next();
                    System.out.print("Введите вторую строку: ");
                    System.out.println("Результат: " + stringWin(str, sc.next()));
                    break;
                case 2:
                    System.out.print("Введите строку: ");
                    sc.useDelimiter("\n");
                    System.out.println("Результат: " + longestPalindrome(sc.next()));
                    break;
                case 3:
                    System.out.print("Введите строку: ");
                    sc.useDelimiter("\n");
                    System.out.println("Результат: " + numConcat(sc.next()));
                    break;
                case 0:
                    return;
            }
            sc.useDelimiter("\n");
            sc.next();
        }
    }

    public static boolean stringWin(String s1, String s2){
        if(s1.length() != s2.length()){
            throw new IllegalArgumentException();
        }
        int[] c1 = new int[s1.length()];
        int[] c2 = new int[s2.length()];
        for(int i = 0; i < s1.length(); i++){
            c1[i] = s1.charAt(i);
            c2[i] = s2.charAt(i);
        }
        c1 = Matrix.insertionSort(c1);
        c2 = Matrix.insertionSort(c2);
        boolean firstWins = true;
        boolean secondWins = true;
        for(int i = 0; i < c1.length; i++){
            if(c1[i] < c2[i]){
                firstWins = false;
            }
            if(c2[i] < c1[i]){
                secondWins = false;
            }
        }
        return firstWins || secondWins;
    }

    public static String longestPalindrome(String s1){
        for(int length = s1.length(); length > 1; length--){
            for(int pos = 0; pos <= s1.length() - length; pos++){
                boolean isPalindrome = true;
                for(int k = 0; k < length / 2; k++){
                    if(s1.charAt(pos + k) != s1.charAt(pos + length - k - 1)){
                        isPalindrome = false;
                        break;
                    }
                }
                if(isPalindrome){
                    return s1.substring(pos, pos + length);
                }
            }
        }
        return "";
    }

    public static int numConcat(String s1){
        ArrayList<String> list = new ArrayList<>();
        for(int length = 2; length <= (s1.length() / 2) * 2; length += 2){ // длины искомых двойных подстрок
            for(int pos = 0; pos <= s1.length() - length; pos++){ // начальная позиция
                boolean isConcat = true;
                for(int k = 0; k < length / 2; k++){
                    if(s1.charAt(pos + k) != s1.charAt(pos + length / 2 + k)){
                        isConcat = false;
                        break;
                    }
                }
                if(isConcat && !list.contains(s1.substring(pos, pos + length))){
                    list.add(s1.substring(pos, pos + length));
                }
            }
        }
        return list.size();
    }
}

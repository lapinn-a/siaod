import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /*Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Введите строку: ");
        String str = sc.next();
        System.out.print("Введите подстроку: ");
        String subStr = sc.next();
        int[] res = KMP(str, subStr);*/
        int[] res = KMP("aabaabaaaabaabaaab", "aabaa");
        System.out.print("Алгоритм КМП: Найдено совпадений: " + res.length + ", ");
        if(res.length > 0){
            System.out.print("в позициях: ");
        }
        for(int i = 0; i < res.length; i++){
            System.out.print(res[i] + ", ");
        }
        System.out.print("\b\b.\n");

        /*System.out.print("Введите строку: ");
        str = sc.next();
        System.out.print("Введите подстроку: ");
        subStr = sc.next();
        res = BoyerMoore(str, subStr);*/
        res = BoyerMoore("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam", "error");
        System.out.print("\nАлгоритм Бойера-Мура: Найдено совпадений: " + res.length + ", ");
        if(res.length > 0){
            System.out.print("в позициях: ");
        }
        for(int i = 0; i < res.length; i++){
            System.out.print(res[i] + ", ");
        }
        System.out.print("\b\b.\n");

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 13, 9, 11, 12, 10, 14, 15, 0};
        Random rnd = new Random();
        for(int k = 0; k < 2; k++) {
            for (int i = 0; i < arr.length; i++) {
                if(k == 0){
                    break;
                }
                if(k == 1){
                    arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 14, 0};
                    break;
                }
                int index = rnd.nextInt(i + 1);
                int a = arr[index];
                arr[index] = arr[i];
                arr[i] = a;
            }
            System.out.print("\nИсходное состояние: \n");
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(arr[i * 4 + j] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("Решение: ");
            res = solveFifteenPuzzle(arr);
            for(int i = 0; i < res.length; i++){
                System.out.print(res[i] + ", ");
            }
            System.out.print("\b\b");
            if(res.length == 0){
                System.out.print(" не существует");
            }
            System.out.print(".\n");
        }
    }

    public static int[] KMP(String str, String subStr){
        str = subStr.concat("\0").concat(str);
        int[] prefix = prefix(str);
        int cnt = 0;
        for(int i = 0; i < prefix.length; i++){
            if(prefix[i] == subStr.length()){
                cnt++;
            }
        }
        int[] out = new int[cnt];
        cnt = 0;
        for(int i = 0; i < prefix.length; i++){
            if(prefix[i] == subStr.length()){
                out[cnt] = i - subStr.length() * 2;
                cnt++;
            }
        }
        return out;
    }

    public static int[] prefix(String str){
        char[] ch = str.toCharArray();
        int[] prefix = new int[ch.length];
        for(int i = 0; i < ch.length; i++){ // для каждого символа
            int maxCnt = 0;
            for(int j = 0; j < i; j++){ // длина от 0 до позиции символа
                int cnt = 0;
                for(int k = 0; k < j + 1; k++){ // нахождение совпадений
                    if(ch[k] == ch[i - j + k]){
                        cnt++;
                    } else {
                        cnt = 0;
                        break;
                    }
                }
                if(cnt > maxCnt){
                    maxCnt = cnt;
                }
            }
            prefix[i] = maxCnt;
        }
        return prefix;
    }

    public static int[] BoyerMoore(String string, String subString){
        char[] str = string.toCharArray();
        char[] subStr = subString.toCharArray();
        int[] d = new int[256];
        int[] subStringNumbers = new int[subStr.length];
        Arrays.fill(d, subStr.length);
        for(int i = subStr.length - 1; i >= 0; i--){
            if(d[subStr[i]] == subStr.length){
                subStringNumbers[i] = subStr.length - i - 1;
                d[subStr[i]] = subStr.length - i - 1;
            } else {
                subStringNumbers[i] = d[subStr[i]];
            }
        }
        int offset = 0;
        int[] out = new int[0];
        while(offset + subStr.length <= str.length){
            for(int j = subStr.length - 1; j >= 0; j--){
                if(str[j + offset] != subStr[j]){
                    offset += d[str[j + offset]];
                    break;
                }
                if(j == 0){
                    int[] temp = new int[out.length + 1];
                    System.arraycopy(out, 0, temp, 0, out.length);
                    out = temp;
                    out[out.length - 1] = j + offset;
                    //System.out.println("найден");
                    offset++;
                }
            }
        }
        return out;
    }

    public static int[] solveFifteenPuzzle(int[] arr){
        Fifteen fifteen = new Fifteen(arr);
        return fifteen.solve();
    }
}

import java.util.Scanner;

public class Coins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Введите массив чисел через пробел: ");
        String str = sc.next();
        String[] arr = str.split(" ");
        int[] coinArray = new int[arr.length];
        for(int i = 0; i < coinArray.length; i++){
            coinArray[i] = Integer.parseInt(arr[i]);
        }
        System.out.println("Результат: " + coins(coinArray));
    }

    public static int coins(int[] piles){
        if(piles.length % 3 != 0){
            throw new IllegalArgumentException();
        }
        piles = Matrix.quickSort(piles);
        if(piles[0] < 1){
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for(int i = piles.length - 2; i >= piles.length / 4; i -= 2){
            sum += piles[i];
        }
        return sum;
    }
}

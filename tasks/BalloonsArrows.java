import java.util.Scanner;

public class BalloonsArrows {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Введите координаты шариков через пробел: ");
        String str = sc.next();
        String[] arr = str.split(" ");
        int[][] coords = new int[arr.length / 2][2];
        for(int i = 0; i < coords.length; i++){
            coords[i][0] = Integer.parseInt(arr[i * 2]);
            coords[i][1] = Integer.parseInt(arr[i * 2 + 1]);
        }
        System.out.println("Результат: " + minArrowNumber(coords));
    }

    public static int minArrowNumber(int[][] arr){
        int[][] coords = new int[arr.length * 2][2]; // [координата, количество шаров]
        int out = 0;
        boolean[] destroyed = new boolean[arr.length];
        for(int i = 0; i < arr.length; i++){
            coords[i * 2][0] = arr[i][0];
            coords[i * 2 + 1][0] = arr[i][1];
        }
        while(true) {
            // Выбор новой координаты
            int coordMaxNumBalloons = 0;
            int maxNumBalloons = 0;
            for (int i = 0; i < coords.length; i++) {
                int k = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (coords[i][0] >= arr[j][0] && coords[i][0] <= arr[j][1] && !destroyed[j]) {
                        k++;
                    }
                }
                coords[i][1] = k;
                if (k > maxNumBalloons) {
                    coordMaxNumBalloons = coords[i][0];
                    maxNumBalloons = k;
                }
            }
            if(maxNumBalloons == 0) {
                return out;
            }

            // Выпуск стрелы из координаты coordMaxNumBalloons
            for (int i = 0; i < arr.length; i++) {
                if (coordMaxNumBalloons >= arr[i][0] && coordMaxNumBalloons <= arr[i][1] && !destroyed[i]) { // если стрела сбивает шар
                    destroyed[i] = true;
                    for (int j = 0; j < coords.length; j++) {
                        if (coords[j][0] >= arr[i][0] && coords[j][0] <= arr[i][1]) {
                            coords[j][1]--;
                        }
                    }
                }
            }
            out++;
        }
    }
}

import java.io.*;
import java.util.Scanner;

public class Main {
    public static int numHanoi = 0;

    public static void main(String[] args) {
        sortAlphabet("in1.txt", "out1.txt");
        decrypt("in2_1.txt", "in2_2.txt", "out2.txt");
        Hanoi("in3.txt", "out3.txt");
        roundBalance("in4.txt", "out4.txt");
        squareBalance("in5.txt", "out5.txt");
        printSymbols("in6.txt", "out6.txt");
        printNumbers("in7.txt", "out7.txt");
        reverseStrings("in8.txt", "out8.txt");
        logicEquation("in9.txt", "out9.txt");
        equation2("in10.txt", "out10.txt");
        equation3("in11.txt", "out11.txt");
    }

    public static void sortAlphabet(String inputFile, String outputFile) {
        System.out.println("\n\u001B[47m\u001B[30m1. Сортировка строк:\u001B[0m");
        Deque d1 = new Deque(1000);
        Deque d2 = new Deque(1000);
        try {
            FileReader reader = new FileReader(inputFile);
            int c;
            System.out.println("Исходные строки:");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                d2.pushRight(c);
                if (c == 10) {
                    if (d1.peekLeft() == null) {
                        while (d2.peekRight() != null) {
                            d1.pushLeft(d2.popRight());
                        }
                    } else {
                        d1.pushRight(-1);
                        while (d1.peekLeft() != -1 && d1.peekLeft() < d2.peekLeft()) {
                            do {
                                d1.pushRight(d1.popLeft());
                            } while (d1.peekRight() != -1 && d1.peekRight() != 10);
                        }
                        while (d1.peekLeft() != -1 && d1.peekLeft().equals(d2.peekLeft())) {
                            d2.pushRight(-1);
                            int num = 0;
                            while (!(d1.peekLeft() == -1 || d2.peekLeft() == -1 || !d1.peekLeft().equals(d2.peekLeft()))) {
                                num++;
                                d1.pushRight(d1.popLeft());
                                d2.pushRight(d2.popLeft());
                            }
                            if (d1.peekLeft() >= d2.peekLeft()) {
                                while (num != 0) {
                                    d1.pushLeft(d1.popRight());
                                    d2.pushLeft(d2.popRight());
                                    num--;
                                }
                                d2.popRight();
                                break;
                            } else if (d1.peekLeft() < d2.peekLeft()) {
                                do {
                                    d1.pushRight(d1.popLeft());
                                } while (d1.peekRight() != -1 && d1.peekRight() != 10);
                            }
                        }
                        while (d2.peekRight() != null) {
                            d1.pushLeft(d2.popRight());
                        }
                        while (d1.peekRight() != -1) {
                            d1.pushLeft(d1.popRight());
                        }
                        d1.popRight();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\nОтсортированные строки:");
            while (d1.peekLeft() != null) {
                int c = d1.popLeft();
                System.out.print((char)c);
                writer.append((char)c);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(String keyFile, String encryptedFile, String decryptedFile) {
        System.out.println("\n\u001B[47m\u001B[30m2. Расшифровать сообщение:\u001B[0m");
        Deque d = new Deque(1000);
        String out = "";
        try {
            FileReader reader = new FileReader(keyFile);
            int c;
            System.out.println("Ключ:");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                d.pushRight(c);
            }
            reader = new FileReader(encryptedFile);
            System.out.println("\n\nЗашифрованное сообщение:");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                int i = 0;
                while (d.peekRight() != c && i < 28) {
                    d.pushLeft(d.popRight());
                    i++;
                }
                if(i == 28){
                    out = out.concat(Character.toString(c));
                } else {
                    d.pushLeft(d.popRight());
                    d.pushLeft(d.popRight());
                    out = out.concat(Character.toString(d.peekRight()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nРасшифрованное сообщение:");
        System.out.println(out);
        try {
            FileWriter writer = new FileWriter(decryptedFile, false);
            writer.write(out);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Hanoi(String inputFile, String outputFile){
        int c, col;
        System.out.println("\n\u001B[47m\u001B[30m3. Ханойская башня:\u001B[0m");
        Stack s1, s2, s3 = null;
        try {
            FileReader reader = new FileReader(inputFile);
            col = reader.read() - 48;
            System.out.println("Количество дисков: " + col);
            s1 = new Stack(col);
            s2 = new Stack(col);
            s3 = new Stack(col);
            for(int i = col; i > 0; i--){
                s1.push(i);
            }
            HanoiSolver(col, s1, s2, s3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("Решено за " + numHanoi + " ходов");
            writer.write("Решено за " + numHanoi + " ходов");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HanoiSolver(int q, Stack from, Stack buf, Stack to){
        if(q == 0){
            return;
        }
        numHanoi++;
        HanoiSolver(q - 1, from, to, buf);
        to.push(from.pop());
        HanoiSolver(q - 1, buf, from, to);
    }

    public static void roundBalance(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m4. Проверить баланс круглых скобок:\u001B[0m");
        boolean b = true;
        Stack s = new Stack(100);
        try {
            FileReader reader = new FileReader(inputFile);
            int c;
            System.out.println("Исходный файл: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                if(c == 40){
                    s.push(1);
                }
                if(c == 41){
                    if(s.peek() != null){
                        s.pop();
                    } else {
                        b = false;
                        break;
                    }
                }
            }
            if(s.peek() != null){
                b = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res;
        try {
            System.out.println("");
            FileWriter writer = new FileWriter(outputFile, false);
            if(b){
                res = "Баланс круглых скобок соблюдается";
            } else {
                res = "Баланс круглых скобок нарушен";
            }
            writer.write(res);
            System.out.println(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void squareBalance(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m5. Проверить баланс квадратных скобок:\u001B[0m");
        boolean b = true;
        Deque s = new Deque(100);
        try {
            FileReader reader = new FileReader(inputFile);
            int c;
            System.out.println("Исходный файл: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                if(c == 40){
                    s.pushRight(1);
                }
                if(c == 41){
                    if(s.peekRight() != null){
                        s.popRight();
                    } else {
                        b = false;
                        break;
                    }
                }
            }
            if(s.peekRight() != null){
                b = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res;
        try {
            System.out.println("");
            FileWriter writer = new FileWriter(outputFile, false);
            if(b){
                res = "Баланс квадратных скобок соблюдается";
            } else {
                res = "Баланс квадратных скобок нарушен";
            }
            writer.write(res);
            System.out.println(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSymbols(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m6. Вывести сначала цифры, затем буквы, затем символы:\u001B[0m");
        Stack numbers = new Stack(1000);
        Stack letters = new Stack(1000);
        Stack symbols = new Stack(1000);
        Stack reverse = new Stack(1000);
        Integer c;
        try {
            FileReader reader = new FileReader(inputFile);
            System.out.println("Исходный файл: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)(int)c);
                if (c > 47 && c < 58) {
                    numbers.push(c);
                } else if (c > 64 && c < 91 || c > 96 && c < 123 || c > 1039 && c < 1104 || c == 1105 || c == 1025) {
                    letters.push(c);
                } else {
                    symbols.push(c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\n\nРезультат: ");
            while((c = numbers.pop()) != null){
                reverse.push(c);
            }
            while((c = reverse.pop()) != null){
                System.out.print((char)(int)c);
                writer.append((char)(int)c);
            }
            while((c = letters.pop()) != null){
                reverse.push(c);
            }
            while((c = reverse.pop()) != null){
                System.out.print((char)(int)c);
                writer.append((char)(int)c);
            }
            while((c = symbols.pop()) != null){
                reverse.push(c);
            }
            while((c = reverse.pop()) != null){
                System.out.print((char)(int)c);
                writer.append((char)(int)c);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\n");
    }

    public static void printNumbers(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m7. Вывести сначала положительные, затем отрицательные числа:\u001B[0m");
        Deque pos = new Deque(1000);
        Deque neg = new Deque(1000);
        Integer c;
        try {
            Scanner sc = new Scanner(new File(inputFile));
            System.out.println("Исходный файл: ");
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    c = sc.nextInt();
                    System.out.print(c + " ");
                    if (c < 0) {
                        neg.pushLeft(c);
                    } else {
                        pos.pushLeft(c);
                    }
                } else {
                    System.out.print(sc.next());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\n\nРезультат: ");
            while((c = neg.popRight()) != null){
                System.out.print((int)c + " ");
                writer.append(String.valueOf(c));
                writer.append(" ");
            }
            while((c = pos.popRight()) != null){
                System.out.print((int)c + " ");
                writer.append(String.valueOf(c));
                writer.append(" ");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void reverseStrings(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m8. Вывести строки в обратном порядке:\u001B[0m");
        Stack s1 = new Stack(1000);
        Stack s2 = new Stack(1000);
        int c;
        try {
            FileReader reader = new FileReader(inputFile);
            System.out.println("Исходные строки: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                s1.push(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\nРезультат: ");
            while(s1.peek() != null){
                do{
                    s2.push(s1.pop());
                } while(s1.peek() != null && s1.peek() != 10);
                while(s2.peek() != null){
                    c = s2.pop();
                    System.out.print((char)c);
                    writer.append((char)c);
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logicEquation(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m9. Вычислить значение логического выражения:\u001B[0m");
        String res = "";
        String str = "";
        Stack s1 = new Stack(1000);
        int c;
        try {
            FileReader reader = new FileReader(inputFile);
            System.out.println("Выражение: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                if(c == 'T' || c == 'F'){
                    str = str.concat(String.valueOf((char)c));
                }
                if(c == 'N' || c == '('){
                    s1.push(c);
                }
                if(c == ')'){
                    while(s1.peek() != null && s1.peek() != 40){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    if(s1.peek() == null){
                        res = "Ошибка в выражении";
                        break;
                    }
                    if(s1.peek() == '('){
                        s1.pop();
                    }
                }
                if(c == 'A' || c == 'X' || c == 'O'){
                    while(s1.peek() != null && (s1.peek() == 'N' || s1.peek() == 'A' || s1.peek() == 'X' || s1.peek() == 'O')){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    s1.push(c);
                }
            }
            while(s1.peek() != null){
                if(s1.peek() == '('){
                    res = "Ошибка в выражении";
                    break;
                }
                str = str.concat(String.valueOf((char)(int)s1.pop()));
            }
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == 'T' || str.charAt(i) == 'F'){
                    s1.push((int)str.charAt(i));
                }
                if(str.charAt(i) == 'N'){
                    if(s1.pop() == 'T'){
                        s1.push((int)'F');
                    } else {
                        s1.push((int)'T');
                    }
                }
                if(str.charAt(i) == 'A'){
                    boolean b1, b2;
                    b1 = s1.pop() == 'T';
                    b2 = s1.pop() == 'T';
                    s1.push(b1 && b2 ? (int)'T' : (int)'F');
                }
                if(str.charAt(i) == 'X'){
                    boolean b1, b2;
                    b1 = s1.pop() == 'T';
                    b2 = s1.pop() == 'T';
                    s1.push(b1 ^ b2 ? (int)'T' : (int)'F');
                }
                if(str.charAt(i) == 'O'){
                    boolean b1, b2;
                    b1 = s1.pop() == 'T';
                    b2 = s1.pop() == 'T';
                    s1.push(b1 || b2 ? (int)'T' : (int)'F');
                }
            }
            if(res.equals("")){
                res = String.valueOf((char)(int)s1.pop());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\n\nРезультат: ");
            System.out.print(res);
            writer.append(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void equation2(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m10. Вычислить значение выражения:\u001B[0m");
        String str = "";
        String res = "";
        Stack s1 = new Stack(1000);
        int c;
        try {
            FileReader reader = new FileReader(inputFile);
            System.out.println("Выражение: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                if(c > 47 && c < 58){
                    str = str.concat(String.valueOf((char)c));
                }
                if(c == 'M' || c == 'N' || c == '('){
                    s1.push(c);
                }
                if(c == ','){
                    while(s1.peek() != null && s1.peek() != '('){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    if(s1.peek() == null){
                        res = "Ошибка в выражении";
                        break;
                    }
                }
                if(c == ')'){
                    while(s1.peek() != null && s1.peek() != 40){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    if(s1.peek() == null){
                        res = "Ошибка в выражении";
                        break;
                    }
                    if(s1.peek() == '('){
                        s1.pop();
                    }
                    if(s1.peek() == 'M' || s1.peek() == 'N'){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                }
                if(c == 'A' || c == 'X' || c == 'O'){
                    while(s1.peek() != null && (s1.peek() == 'N' || s1.peek() == 'A' || s1.peek() == 'X' || s1.peek() == 'O')){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    s1.push(c);
                }
            }
            while(s1.peek() != null){
                if(s1.peek() == '('){
                    res = "Ошибка в выражении";
                    break;
                }
                str = str.concat(String.valueOf((char)(int)s1.pop()));
            }
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) > 47 && str.charAt(i) < 58){
                    s1.push((int)str.charAt(i));
                }
                if(str.charAt(i) == 'M'){
                    int b1, b2;
                    b1 = s1.pop();
                    b2 = s1.pop();
                    s1.push(Math.max(b1, b2));
                }
                if(str.charAt(i) == 'N'){
                    int b1, b2;
                    b1 = s1.pop();
                    b2 = s1.pop();
                    s1.push(Math.min(b1, b2));
                }
            }
            if(res.equals("")){
                res = String.valueOf((char)(int)s1.pop());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\n\nРезультат: ");
            System.out.print(res);
            writer.append(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    public static void equation3(String inputFile, String outputFile){
        System.out.println("\n\u001B[47m\u001B[30m11. Проверить выражение:\u001B[0m");
        String str = "";
        String res = "Выражение корректно";
        Stack s1 = new Stack(1000);
        int c;
        try {
            FileReader reader = new FileReader(inputFile);
            System.out.println("Выражение: ");
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
                if(c == 'x' || c == 'y' || c == 'z'){
                    str = str.concat(String.valueOf((char)c));
                }
                if(c == '('){
                    s1.push(c);
                }
                if(c == ')'){
                    while(s1.peek() != null && s1.peek() != 40){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    if(s1.peek() == null){
                        res = "Ошибка в выражении";
                        break;
                    }
                    if(s1.peek() == '('){
                        s1.pop();
                    }
                    if(s1.peek() == 'M' || s1.peek() == 'N'){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                }
                if(c == '+' || c == '-'){
                    while(s1.peek() != null && (s1.peek() == '+' || s1.peek() == '-')){
                        str = str.concat(String.valueOf((char)(int)s1.pop()));
                    }
                    s1.push(c);
                }
            }
            while(s1.peek() != null){
                if(s1.peek() == '('){
                    res = "Ошибка в выражении";
                    break;
                }
                str = str.concat(String.valueOf((char)(int)s1.pop()));
            }
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) > 47 && str.charAt(i) < 58){
                    s1.push((int)str.charAt(i));
                }
                if(str.charAt(i) == 'M'){
                    int b1, b2;
                    b1 = s1.pop();
                    b2 = s1.pop();
                    s1.push(Math.max(b1, b2));
                }
                if(str.charAt(i) == 'N'){
                    int b1, b2;
                    b1 = s1.pop();
                    b2 = s1.pop();
                    s1.push(Math.min(b1, b2));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile, false);
            System.out.println("\n\nРезультат: ");
            System.out.print(res);
            writer.append(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }
}

public class Fifteen {
    private int[] cells;
    private int[] solution = new int[0];

    public Fifteen(int[] cells){
        if(cells.length != 16){
            throw new IllegalArgumentException("Field should contain 16 elements");
        }
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                if(cells[i] == j){
                    break;
                }
                if(j == 15){
                    throw new IllegalArgumentException("Not enough numbers");
                }
            }
        }
        this.cells = cells;
    }

    public int[] solve(){
        if(!isSolvable()){
            return solution;
        }
        if(!checkRow(1)){
            placeAt(1, 1, 1);
            //System.out.println("1 PLACED");
            placeAt(2, 1, 2);
            //System.out.println("2 PLACED");
            placeAt(4, 1, 3);
            //System.out.println("4 PLACED");
            placeAt(3, 2, 3);
            if (getRow(4) == 1 && getCol(4) == 3 && getRow(3) == 2 && getCol(3) == 3) {
                if (getCol(0) != 4) {
                    while (getRow(0) < 3) {
                        moveZero(3);
                    }
                    while (getCol(0) < 4) {
                        moveZero(2);
                    }
                }
                while (getRow(0) > 1) {
                    moveZero(1);
                }
                moveZero(4);
                moveZero(3);
            }
            //System.out.println("3 PLACED");
        }

        if(!checkRow(2)) {
            placeAt(5, 2, 1);
            //System.out.println("5 PLACED");
            placeAt(6, 2, 2);
            //System.out.println("6 PLACED");
            placeAt(8, 2, 3);
            //System.out.println("8 PLACED");
            placeAt(7, 3, 3);
            if (getRow(8) == 2 && getCol(8) == 3 && getRow(7) == 3 && getCol(7) == 3) {
                if (getCol(0) != 4) {
                    while (getRow(0) < 4) {
                        moveZero(3);
                    }
                    while (getCol(0) < 4) {
                        moveZero(2);
                    }
                }
                while (getRow(0) > 2) {
                    moveZero(1);
                }
                moveZero(4);
                moveZero(3);
            }
            //System.out.println("7 PLACED");
        }
        if(!checkRow(3) && !checkRow(4)){
            placeAt(13, 3, 1);
            //System.out.println("13 PLACED");
            placeAt(9, 3, 2);
            if (getRow(13) == 4 && getCol(13) == 1 && getRow(9) == 3 && getCol(9) == 2) {
                border(9);
            } else {
                if (getRow(0) == 3) {
                    moveZero(3);
                }
                while (getCol(0) > 1) {
                    moveZero(4);
                }
                moveZero(1);
                moveZero(2);
            }
            //System.out.println("9 PLACED");
            placeAt(14, 3, 2);
            //System.out.println("14 PLACED");
            placeAt(10, 3, 3);
            if (getRow(14) == 4 && getCol(14) == 2 && getRow(10) == 3 && getCol(10) == 3) {
                border(10);
            } else {
                if (getRow(0) == 3) {
                    moveZero(3);
                }
                while (getCol(0) > 2) {
                    moveZero(4);
                }
                moveZero(1);
                moveZero(2);
            }
            //System.out.println("10 PLACED");
            while (!(getCol(11) == 3 && getRow(11) == 3 && getCol(12) == 4 && getRow(12) == 3)) {
                if (getRow(0) == 3) {
                    if (getCol(0) == 3) {
                        moveZero(2);
                    }
                    if (getCol(0) == 4) {
                        moveZero(3);
                    }
                }
                if (getRow(0) == 4) {
                    if (getCol(0) == 3) {
                        moveZero(1);
                    }
                    if (getCol(0) == 4) {
                        moveZero(4);
                    }
                }
            }
            if (getCol(15) == 4) {
                moveZero(2);
            }
        }
        //System.out.println("SOLVED");
        for(int i = 0; i < 15; i++){
            if(cells[i] != i + 1){
                System.out.println("NOT SOLVED");
                System.exit(-1);
            }
        }
        if(cells[15] != 0){
            System.out.println("NOT SOLVED");
            System.exit(-1);
        }
        return solution;
    }

    public void placeAt(int num, int row, int col){
        // выставляем в нужный столбец
        if(num == 3 && getRow(3) == 1){
            border(3);
            return;
        }
        if(num == 7 && getRow(7) == 2){
            border(7);
            return;
        }
        while(getCol(num) != col){
            if(getRow(num) < 4){
                while(getRow(num) + 1 != getRow(0)){
                    if(getRow(0) <= getRow(num)){
                        moveZero(3);
                    } else {
                        moveZero(1);
                    }
                }
                if(num == 3 && getRow(3) == 1){
                    border(3);
                    return;
                }
                if(num == 7 && getRow(7) == 2){
                    border(7);
                    return;
                }
                // теперь 0 находится в строке под элементом
                if(getCol(num) > col){ //если элемент правее чем нужно
                    while(getCol(0) + 1 != getCol(num)){
                        if(getCol(0) < getCol(num) - 1){
                            moveZero(2);
                        } else {
                            moveZero(4);
                        }
                    }
                    moveZero(1);
                    moveZero(2);
                }
                if(getCol(num) < col){ //если элемент левее чем нужно
                    while(getCol(0) - 1 != getCol(num)){
                        if(getCol(0) - 1 < getCol(num)){
                            moveZero(2);
                        } else {
                            moveZero(4);
                        }
                    }
                    moveZero(1);
                    moveZero(4);
                }
            } else {
                while(getRow(0) + 1 != getRow(num)){
                    if(getRow(0) + 1 < getRow(num)){
                        moveZero(3);
                    } else {
                        moveZero(1);
                    }
                }
                // теперь 0 находится в строке над элементом
                if(getCol(num) > col){ //если элемент правее чем нужно
                    while(getCol(0) + 1 != getCol(num)){
                        if(getCol(0) < getCol(num) - 1){
                            moveZero(2);
                        } else {
                            moveZero(4);
                        }
                    }
                    moveZero(3);
                    moveZero(2);
                }
                if(getCol(num) < col){ //если элемент левее чем нужно
                    while(getCol(0) - 1 != getCol(num)){
                        if(getCol(0) - 1 < getCol(num)){
                            moveZero(2);
                        } else {
                            moveZero(4);
                        }
                    }
                    moveZero(3);
                    moveZero(4);
                }
            }
        }
        // ставим в нужную строку
        while(getRow(num) != row){
            if(getRow(num) < 4){
                if (getCol(0) < getCol(num)) { // если 0 справа от элемента то обходим снизу
                    while (getRow(0) - 1 != getRow(num)) {
                        if(getRow(0) > getRow(num)){
                            moveZero(1);
                        } else {
                            moveZero(3);
                        }
                    }
                }
            } else {
                if (getCol(0) < getCol(num)) { // если 0 справа от элемента то обходим сверху
                    while (getRow(0) + 1 != getRow(num)) {
                        if(getRow(0) >= getRow(num)){
                            moveZero(1);
                        } else {
                            moveZero(3);
                        }
                    }
                }
            }
            while(getCol(0) - 1 != getCol(num)){
                if(getCol(0) > getCol(num)){
                    moveZero(4);
                } else {
                    moveZero(2);
                }
            }
            while(getRow(0) + 1 != getRow(num)){
                if(getRow(0) < getRow(num)){
                    moveZero(3);
                } else {
                    moveZero(1);
                }
            }
            moveZero(4);
            moveZero(3);
        }
    }

    public void border(int num){
        int[] moves = new int[]{1, 2, 3, 4, 3, 2, 1, 1, 4, 3, 2, 3, 4, 1, 1, 2, 3};
        int[] movesDown = new int[]{4, 1, 2, 2, 3, 4, 4, 1, 2, 3, 2, 1, 4, 4, 3, 2};
        //System.out.println("ОБРАБОТКА ГРАНИЧНОГО УСЛОВИЯ" + num);
        if(num == 3) {
            while (getRow(0) != 2) {
                if (getRow(0) > 2) {
                    moveZero(1);
                } else {
                    moveZero(3);
                }
            }
            while (getCol(0) != 3) {
                if (getCol(0) > 3) {
                    moveZero(4);
                } else {
                    moveZero(2);
                }
            }
            for(int i = 0; i < moves.length; i++){
                moveZero(moves[i]);
            }
        }

        if(num == 7) {
            while (getRow(0) != 3) {
                if (getRow(0) > 3) {
                    moveZero(1);
                } else {
                    moveZero(3);
                }
            }
            while (getCol(0) != 3) {
                if (getCol(0) > 3) {
                    moveZero(4);
                } else {
                    moveZero(2);
                }
            }
            for(int i = 0; i < moves.length; i++){
                moveZero(moves[i]);
            }
        }

        if(num == 9 || num == 10){
            while (getRow(0) != 4) {
                    moveZero(3);
            }
            while (getCol(0) != (num % 7)) {
                if (getCol(0) > (num % 7)) {
                    moveZero(4);
                } else {
                    moveZero(2);
                }
            }
            for(int i = 0; i < movesDown.length; i++){
                moveZero(movesDown[i]);
            }
        }
    }

    public void moveZero(int num){ // 1=вверх 2=вправо 3=вниз 4=влево
        int[] temp = new int[solution.length + 1];
        System.arraycopy(solution, 0, temp, 0, solution.length);
        solution = temp;
        int nullPos = getIndex(0);
        if(num == 1){
            cells[nullPos] = cells[nullPos - 4];
            cells[nullPos - 4] = 0;
        }
        if(num == 2){
            cells[nullPos] = cells[nullPos + 1];
            cells[nullPos + 1] = 0;
        }
        if(num == 3){
            cells[nullPos] = cells[nullPos + 4];
            cells[nullPos + 4] = 0;
        }
        if(num == 4){
            cells[nullPos] = cells[nullPos - 1];
            cells[nullPos - 1] = 0;
        }
        solution[solution.length - 1] = cells[nullPos];
        if(solution.length > 2 && solution[solution.length - 2] == cells[nullPos]){
            temp = new int[solution.length - 2];
            System.arraycopy(solution, 0, temp, 0, solution.length - 2);
            solution = temp;
        }
    }

    public int getIndex(int num){
        int pos = -1;
        for(int i = 0; i < 16; i++){
            if(cells[i] == num){
                pos = i;
            }
        }
        return pos;
    }

    public int getRow(int num){
        int pos = getIndex(num);
        return (pos - (pos % 4)) / 4 + 1;
    }

    public int getCol(int num){
        int pos = getIndex(num);
        return pos % 4 + 1;
    }

    public boolean checkRow(int num){
        for(int i = 1; i <= 4; i++){
            if(cells[(num - 1) * 4 + i] != (num - 1) * 4 + i + 1){
                return false;
            }
        }
        return true;
    }

    public void print(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(cells[i * 4 + j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean isSolvable(){
        int n = 0;
        for(int i = 0; i < 16; i++){
            for(int j = i + 1; j < 16; j++){
                if(cells[j] < cells[i] && cells[i] != 0 && cells[j] != 0){
                    n++;
                }
            }
        }
        return (n + getRow(0)) % 2 != 1;
    }
}

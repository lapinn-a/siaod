public class Stack {
    private Integer[] stack;
    private int pointerToEmpty = 0;

    public Stack(int size){
        stack = new Integer[size];
    }

    public boolean push(Integer value){
        if(pointerToEmpty < stack.length){
            stack[pointerToEmpty] = value;
            pointerToEmpty++;
            return true;
        }
        return false;
    }

    public Integer peek(){
        if(pointerToEmpty > 0){
            return stack[pointerToEmpty - 1];
        }
        return null;
    }

    public Integer pop(){
        if(pointerToEmpty > 0){
            pointerToEmpty--;
            return stack[pointerToEmpty];
        }
        return null;
    }
}

public class Deque {
    public Integer[] deque;
    private int size;
    private int rightPointerToEmpty = 0;
    private int leftPointerToEmpty = 0;

    public Deque(int size){
        this.size = size;
        deque = new Integer[size * 2];
        leftPointerToEmpty = size - 1;
        rightPointerToEmpty = size;
    }

    public boolean pushLeft(Integer value){
        if(leftPointerToEmpty >= 0){
            deque[leftPointerToEmpty] = value;
            leftPointerToEmpty--;
            return true;
        }
        return false;
    }

    public boolean pushRight(Integer value){
        if(rightPointerToEmpty < deque.length){
            deque[rightPointerToEmpty] = value;
            rightPointerToEmpty++;
            return true;
        }
        return false;
    }

    public Integer peekLeft(){
        if(leftPointerToEmpty + 1 != rightPointerToEmpty){
            return deque[leftPointerToEmpty + 1];
        }
        return null;
    }

    public Integer peekRight(){
        if(rightPointerToEmpty - 1 != leftPointerToEmpty){
            return deque[rightPointerToEmpty - 1];
        }
        return null;
    }

    public Integer popLeft(){
        if(leftPointerToEmpty + 1 != rightPointerToEmpty){
            leftPointerToEmpty++;
            Integer res = deque[leftPointerToEmpty];
            if(leftPointerToEmpty + 1 == rightPointerToEmpty){
                leftPointerToEmpty = size - 1;
                rightPointerToEmpty = size;
            }
            return res;
        }
        return null;
    }

    public Integer popRight(){
        if(rightPointerToEmpty - 1 != leftPointerToEmpty){
            rightPointerToEmpty--;
            Integer res = deque[rightPointerToEmpty];
            if(rightPointerToEmpty - 1 == leftPointerToEmpty){
                leftPointerToEmpty = size - 1;
                rightPointerToEmpty = size;
            }
            return res;
        }
        return null;
    }
}

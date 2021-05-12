public class BinaryTree {
    float root = -1;
    BinaryTree leftTree;
    BinaryTree rightTree;

    float searchElement(float element){
        if(root == -1){
            return -1;
        } else if(element == root){
            return root;
        } else if(element > root){
            if(rightTree == null){
                return -1;
            }
            return rightTree.searchElement(element);
        } else if(element < root){
            if(leftTree == null){
                return -1;
            }
            return leftTree.searchElement(element);
        }
        return -1;
    }

    String pathToElement(float element){
        String path = "";
        if(root == -1){
            return " Not found";
        } else if(element == root){
            return "";
        } else if(element > root){
            if(rightTree == null){
                return " Not found";
            }
            path += "R" + rightTree.pathToElement(element);
        } else if(element < root){
            if(leftTree == null){
                return " Not found";
            }
            path += "L" + leftTree.pathToElement(element);
        }
        return path;
    }

    void addElement(float element){
        if(root == -1){
            root = element;
        } else if(element == root){
            // Элемент уже есть
        } else if(element > root){
            if(rightTree == null){
                rightTree = new BinaryTree();
            }
            rightTree.addElement(element);
        } else if(element < root){
            if(leftTree == null){
                leftTree = new BinaryTree();
            }
            leftTree.addElement(element);
        }
    }
}

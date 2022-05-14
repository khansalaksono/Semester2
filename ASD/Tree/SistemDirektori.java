//https://www.hackerrank.com/contests/tree-asd-a/challenges/sistem-direktori/problem

import java.io.*;
import java.util.*;


public class SistemDirektori {
    //touch -> insert
    //find -> find and print the file name
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Tree direktori = new Tree();
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            String perintah1 = input.next();
            if (perintah1.equalsIgnoreCase("touch")) {
                direktori.insert(input.nextLine());
            } else if (perintah1.equalsIgnoreCase("find")) {
                String perintah2 = input.nextLine();
                String direk = perintah2.split(" ")[2];
                Node dir = direktori.pickDirektori(direk, direktori.root);
                if(dir==null){
                    System.out.println("TIDAK ADA");
                } else{
                    System.out.println(direktori.getPathIndex(dir));
                }
            }
        }
    }
}

class Node {
    Node parent;
    String data;
    ArrayList<Node> child; //ArrayList = re-sizable array

    Node(String data) {
        this.data = data;
        child = new ArrayList<Node>();
    }
}

class Tree {
    Node root;
    int height = 1;

    Tree() {
        Node treeRoot = new Node(" ");
        this.root = treeRoot;
    }

    public void insert (String data) {
        String[] rawInput = Arrays.copyOfRange(data.split("/"), 1, data.split("/").length);
        Node[] inputan = new Node[rawInput.length];

        int i = 0;
        while (i < rawInput.length) {
            inputan[i] = new Node(rawInput[i]);
            i++;
        }

        if ((height-1) < inputan.length) {
            height = inputan.length + 1;
        }

        Node temp = root;
        for (i = 0; i < rawInput.length; i++) {

            boolean search = false; //cek apakah direktori sudah ada
            for (Node child : temp.child) {
                if (child.data.equals(inputan[i].data)) {
                    search = true;
                }
            }
            if (!search) { //bila belum ada akan ditambahkan
                inputan[i].parent = temp;
                temp.child.add(inputan[i]);
            }

            int index = 0;
            for (int j = 0; j < temp.child.size(); j++) {
                if (temp.child.get(j).data.equals(inputan[i].data)) {
                    index = j;
                }
            }
            temp = temp.child.get(index);
        }
    }

    Node pickDirektori (String data, Node root) {
        Node temp = root;
        boolean search = false;

        if (temp.data.equals(data)) {
            return temp;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < temp.child.size(); j++) {
                Node pickedDir = pickDirektori(data, root.child.get(j));
                search = pickDirektori(data, root.child.get(j)) != null;
                if (search) {
                    return pickedDir;
                }
            }
        }
        return null;
    }

    int cekHeight (Node node) {
        Node temp = node;
        int cek = 0;
        while (temp.parent != null) {
            cek++;
            temp = temp.parent;
        }
        return cek;
    }

    String getPath (String path) {
        String[] tempIndexPath = path.split("/");
        int[] indexPath = new int[tempIndexPath.length-1];

        for (int i = 0; i < tempIndexPath.length - 1; i++) {
            indexPath[i] = Integer.parseInt(tempIndexPath[i + 1]);
        }

        Node temp = root;
        String paths = "";
        for (int i = 0; i < indexPath.length; i++) {
            paths += "/";
            paths += temp.child.get(indexPath[i]).data;
            temp = temp.child.get(indexPath[i]);
        }
        return paths;
    }

    String getPathIndex (Node data) {
        String path = "";
        Node temp = data;
        int tempHeight = cekHeight(data);

        for (int i = 0; i < tempHeight; i++) {
            for (int j = 0; j < temp.parent.child.size(); j++) {
                if (temp.parent.child.get(j).data.equals(temp.data)) {
                    path += "/";
                    path += j;
                    break;
                }
            }
            temp = temp.parent;
        }
        path = reversePath(path);
        String pathString = getPath(path);
        return pathString;
    }

    String reversePath(String path) {
        String truePath = "";
        String[] pathTemp = path.split("/");
        int i = (pathTemp.length - 1);
        while (i > 0) {
            truePath += "/";
            truePath += pathTemp[i];
            i--;
        }
        return truePath;
    }
}

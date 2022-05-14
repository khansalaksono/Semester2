//https://www.hackerrank.com/contests/tree-asd-a/challenges/bst-tree

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class BSTTree {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BST tree = new BST();
        String NM = input.nextLine();
        String[] masukan = NM.split(" ");
        int n = Integer.parseInt(masukan[0]); //banyak pasangan
        int m = Integer.parseInt(masukan[1]); //banyak perintah

        for(int i = 0; i < n; i++){ //add angka dan value
            String pasangan = input.nextLine();
            String[] pasanganArray = pasangan.split(" ");
            int key = Integer.parseInt(pasanganArray[0]);
            String value = pasanganArray[1];
            tree.insert(tree.root, key, value);
        }

        for(int i = 0; i < m; i++){ //perintah yang diminta
            int searchKey = input.nextInt();
            if(tree.root == null){ //pasangan tidak exist
                System.out.println("TIDAK ADA");
            } else{
                System.out.println(tree.value(tree.root, searchKey));
            }
        }
    }
}

class NodeBST {
    int key;
    String value;
    NodeBST left;
    NodeBST right;
    NodeBST parent;
    
    //konstruktor
    public NodeBST(){
        this.key = 0;
        this.value = null;
        this.right = null;
        this.left = null;
    }
    
    public NodeBST(int key, String value){
        this.key = key;
        this.value = value;
        this.right = null;
        this.left = null;
    }
}

class BST {
    NodeBST root;

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(NodeBST root, int key, String value) {
        NodeBST newNode = new NodeBST(key, value);

        if (isEmpty()) {
            root = newNode;
            this.root = root;
        }

        else {
            if (root.left == null || key < root.key) {
                root.left = newNode;
                root.left.parent = root;
            } else if (root.right == null || key > root.key) {
                root.right = newNode;
                root.right.parent = root;
            } else {
                if (key < root.key){
                    insert(root.left, key, value);
                }
                insert(root.right, key, value);
            }
        }
    }

    public NodeBST search(int key){
        NodeBST temp = root;
        while(temp.key != key){
            if(key < temp.key){
                temp = temp.left;
            } else{
                temp = temp.right;
            }
            if (temp == null){
                return null;
            }
        }
        return temp;
    }
    
    public String value(NodeBST root, int key){
        if(root == null){
            return "TIDAK ADA";
        }
        
        if(root.key == key) {
            return root.value;
        }
        
        if (root.key > key) {
            return value(root.left, key);
        }
        
        return value(root.right, key);
    }
}

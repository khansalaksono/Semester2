//https://www.hackerrank.com/contests/heap-n-avl-asd-a/challenges/sekretaris-sibuk

import java.io.*;
import java.util.*;

class Node {
    int prior;
    int queue;
    String data;
    Node left;
    Node right;

    Node(int prior, String data) {
        this.data = data;
        this.prior = prior;
    }

    Node(int prior, String data, int queue) {
        this.data = data;
        this.prior = prior;
        this.queue = queue;
    }
}

public class SekretarisSibuk {
    Node rootHeap;
    int count = 0;
    Node[] heap = new Node[50];

    Node insertTree(Node root, int prior, String data, int queue){
        Node newNode = new Node(prior, data, queue);
        if(root==null){
            root = newNode;
        }
        if(prior < root.prior){
            root.left = insertTree(root.left, prior, data, queue);
        }
        else if (prior > root.prior)
            root.right = insertTree(root.right, prior, data, queue);

        return insert(root);
    }

    Node insert(Node root) {
        heap[count] = root;
        count++;
        int indexHeap = count-1; //index mulai dari 0
        while (heap[indexHeap].prior < heap[parent(indexHeap)].prior) {
            swap(indexHeap, parent(indexHeap));
            indexHeap = parent(indexHeap);
        }
        return root;
    }

    int rightChild (int index){ //posisi right child
        return 2*index+1;
    }

    int leftChild (int index){ //posisi left child
        return 2*index+2;
    }

    int parent (int index){ //posisi parent
        return (index-1)/2;
    }

    void swap(int indexSebelum, int indexSesudah) {
        Node temp = heap[indexSebelum];
        heap[indexSebelum] = heap[indexSesudah];
        heap[indexSesudah] = temp;
    }

    void minHeap(int index) {
        if (!isLeaf(index)) {
            if (heap[leftChild(index)].prior < heap[index].prior || heap[rightChild(index)].prior < heap[index].prior) {
                if (heap[leftChild(index)].prior < heap[rightChild(index)].prior) {
                    swap(index, leftChild(index));
                    minHeap(leftChild(index));
                } else {
                    swap(index, rightChild(index));
                    minHeap(rightChild(index));
                }
            } else if (heap[leftChild(index)].prior == heap[index].prior || heap[rightChild(index)].prior == heap[index].prior) {
                if (heap[leftChild(index)].queue < heap[rightChild(index)].queue) {
                    swap(index, leftChild(index));
                    minHeap(leftChild(index));
                } else {
                    swap(index, rightChild(index));
                    minHeap(rightChild(index));
                }
            }
        }
    }

    boolean isLeaf(int index) {
        if (index >= (count / 2) && index <= count) {
            return true;
        }
        return false;
    }

    Node pop() {
        if(count==0) return null;
        Node root = heap[0];
        heap[0] = heap[count - 1];
        count--;
        minHeap(0);
        return root;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Solution a = new Solution();
        int queue = 0;
        while (input.hasNextLine()){
            String format = input.next(); //TERIMA atau BACA
            if(format.equalsIgnoreCase("TERIMA")){
                int prioritas = input.nextInt(); //ngescan prioritas
                String isiSuratRaw = input.nextLine(); //ngescan isi surat
                String[] isiSurat = isiSuratRaw.split(" ", 2);
                queue++;
                a.insertTree(a.rootHeap, prioritas, isiSurat[1], queue);
            }
            else if(format.equalsIgnoreCase("BACA")){
                Node print = a.pop();
                if(print==null) System.out.println("KOTAK MASUK KOSONG");
                else System.out.println(print.data);
            }
            if(format.equalsIgnoreCase("")){
                System.out.println("KOTAK MASUK KOSONG");
                break;
            }
        }
    }
}

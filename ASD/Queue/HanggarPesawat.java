//https://www.hackerrank.com/contests/praktikum-queue-asd-a/challenges/hanggar-pesawat/submissions/code/1342937119

import java.io.*;
import java.util.*;
import java.util.Scanner;

//hackerrank hanggar-pesawat
public class HanggarPesawat {
    //NAMA  : KHANSA SALSABILA SANGDIVA LAKSONO
    //NIM   : 215150201111068
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();
        while (input.hasNext()) {
            String inputKode = input.next();
            if (inputKode.equals("")){
                break;
            } else if(inputKode.equalsIgnoreCase("FIX")){
                if(queue.count == 0){ //bila misal perintah yang keluar pertama "FIX" bukan "INPUT"
                                      //atau bila isi queue kosong 
                    continue;
                }
                System.out.println(queue.dequeue());
                if(queue.count >= 2){
                    System.out.println(queue.dequeue());
                }
            } else{
                while(inputKode.equalsIgnoreCase("INPUT")){
                    String kodePesawat = input.nextLine();
                    if(!kodePesawat.equals(" ")){ //bila mengandung whitespace
                        kodePesawat = kodePesawat.substring(1);
                        queue.enqueue(kodePesawat);
                    }
                    break;
                }
            }
        }
    }
}

class Node {
    String data;
    Node next;
}

class Queue {
    Node head, tail;
    int count = 0;

    void enqueue(String data) {
        Node node = new Node();
        node.data = data;

        if(count == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        count++;
    }

    boolean isEmpty() {
        return (count == 0);
    }

    String dequeue() {
        if(!isEmpty()) {
            Node node = head;
            String value = node.data;

            head = head.next;

            count--;
            return value;
        } else {
            return null;
        }
    }
}

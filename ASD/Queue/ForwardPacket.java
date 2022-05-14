//https://www.hackerrank.com/contests/praktikum-queue-asd-a/challenges/forward-packet

import java.io.*;
import java.util.*;
import java.util.Scanner;

//hackerrank Forward Packet
public class ForwardPacket {
    //NAMA  : KHANSA SALSABILA SANGDIVA LAKSONO
    //NIM   : 215150201111068
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();
        while(input.hasNext()) {
            //CALL diprioritaskan daripada DATA
            //prior call = 0, data = 1
            String rawData = input.next();
            if (rawData.equals("")){
                break;
            } else if(rawData.equalsIgnoreCase("OUT")){
                if(queue.count == 0){ //bila misal perintah pertama yang keluar OUT bukan DATA ataupun CALL
                                      //atau bila isi queue kosong 
                    continue;
                }
                System.out.println(queue.dequeue());
            } else{
                while(rawData.equalsIgnoreCase("DATA")){
                    int setPrior = 1;
                    String realData = input.nextLine();
                        if(!realData.equals(" ")){ //bila mengandung whitespace
                            realData = realData.substring(1);
                            queue.enqueue(realData, setPrior);
                        }
                    break;
                } 
                
                while(rawData.equalsIgnoreCase("CALL")){
                    int setPrior = 0;
                    String realData = input.nextLine();
                        if(!realData.equals(" ")){ //bila mengandung whitespace
                            realData = realData.substring(1);
                            queue.enqueue(realData, setPrior);
                        }
                    break;
                }   
            }
        }
    }
}

class Node {
    String data;
    int prior; // 0 & 1
    Node next;
}

class Queue {
    Node head, tail;
    int count = 0;

    void enqueue(String data, int prior) {
        Node node = new Node();
        node.data = data;
        node.prior = prior;

        if (count == 0) {
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
        if (!isEmpty()) {
            Node awal = head;
            Node node = head;
            Node nodeNext = head.next;
            String value = "";
            count--;

            if (node.prior == 0) {
                value = node.data;
                head = head.next;
            } else {
                while (nodeNext != null) {
                    if (nodeNext.prior == 0) {
                        break;
                    }
                    node = node.next;
                    nodeNext = nodeNext.next;
                }

                if (nodeNext == null) {
                    value = awal.data;
                    head = head.next;
                } else {
                    value = nodeNext.data;
                    node.next = nodeNext.next;
                }
            }

            return value;
        }
        return null;
    }
}

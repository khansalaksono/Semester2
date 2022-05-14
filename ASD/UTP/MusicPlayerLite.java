//https://www.hackerrank.com/contests/utp-asd-tif-a-1648728115/challenges/music-player-lite/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
    Node prev;
    Node next;
    String artist;
    String judul;

    Node(String artist, String judul) {
        this.artist = artist;
        this.judul = judul;
    }
}

class DoubleLinkedListScratch {
    Node head;
    Node tail;
    Node state;

    int length = 0;
    
    boolean isEmpty(){
        return (length==0);
    }
    
    void addSong(Node newNode) {
        if (newNode == null) return;
        if(isEmpty()){
            head = tail = newNode;
            length++;
        } else{
            newNode.prev = tail; 
            tail.next = newNode;
            tail = newNode;
            length++;
        }
    }

    void printPlaylist() {
        Node temp = head;
        while (temp != null) {
            System.out.println("Artist: " + temp.artist + " | Judul: " + temp.judul);
            temp = temp.next;
        }
    }

    void playPlaylist() {
        if (this.state == null && length == 0) {
            System.out.println("No Playlist to Play!");
            return;
        } else if (this.state == null) {
            state = head;
            System.out.println("Now Playing:\nArtist: " + state.artist + " | Judul: " + state.judul);
        } else if (this.state != null) {
            System.out.println("Playlist has been played!");
        }
    }

    void printCurrentPlay() {
        if(this.state == null) return;
        else if (this.state == null) {
            return;
        } else System.out.println("Now Playing:\nArtist: " + state.artist + " | Judul: " + state.judul);
    }

    void toNextSong() {
        if (this.state.next == null) {
            System.out.println("Cannot go to next song, no song specified in playlist!");
        } //Lanjutin sendiri
        
        else{
            this.state = this.state.next;
            printCurrentPlay();
        }
    }

    void toPreviousSong() {
        if (this.state.prev == null) {
            System.out.println("Cannot go to preivous song, no song specified in playlist!");
        } //Lanjutin sendiri
        
        else{
            this.state = this.state.prev;
            printCurrentPlay();
        }
    }
}

class QueueScratch {
    Node head;
    Node tail;
    int length = 0;

    void addSong(Node newNode) {
        if(length == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        length++;
    }

    Node deQueue() {
        if (length == 0) {
            System.out.println("No song to pick from waiting list!");
            return null;
        }
        if (length == 1) {
            Node temp = head;
            head = tail = null;;
            length--;
            return temp;
        } else{
            Node temp = head;
            head.next = head;
            temp.next = null;
            head.prev = null;
            length--;
            return temp;
        }
    }

    void printWaitingList() {
        Node temp = head;
        System.out.println("Waiting List:");
        for (int i = 0; i < length; i++) {
            System.out.println("Artist: " + temp.artist + " | Judul: " + temp.judul);
            temp = temp.next;
        }
    }
}

public class MusicPlayerLite {
    public static void main(String args[] ) throws Exception {
        DoubleLinkedListScratch playlist = new DoubleLinkedListScratch();
        QueueScratch waitingList = new QueueScratch();
        Scanner in = new Scanner(System.in);
        boolean status = false;

        int totalActions = in.nextInt();
        in.nextLine();
        for (int action = 0; action < totalActions; action++) {
            String actionIndicator = in.nextLine();
            if (actionIndicator.equals("ADDSONG")) playlist.addSong(new Node(in.nextLine(), in.nextLine()));
            
            if (actionIndicator.equals("PLAYSONG")){
                playlist.playPlaylist();
                status = true;
            }
            
            if (actionIndicator.equals("NEXTSONG")){
                if(!status){
                    continue;
                }
                playlist.toNextSong();
            }
                
            if (actionIndicator.equals("PREVSONG")){
                if(!status){
                    continue;
                }
                playlist.toPreviousSong();
            }
            
            if (actionIndicator.equals("PRINTSONG")) playlist.printCurrentPlay();
            
            if (actionIndicator.equals("PRINTPLAYLIST")) playlist.printPlaylist();
            
            if (actionIndicator.equals("ADDWAIT")) waitingList.addSong(new Node(in.nextLine(), in.nextLine()));
            
            if (actionIndicator.equals("ADDSONGWAIT")) playlist.addSong(waitingList.deQueue());
            
            if (actionIndicator.equals("PRINTWAIT")) waitingList.printWaitingList();
        }
    }
}

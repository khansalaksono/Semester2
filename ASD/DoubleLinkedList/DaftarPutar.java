//https://www.hackerrank.com/contests/double-linked-list-a-asd-a/challenges/daftar-putar-playlist

import java.io.*;
import java.util.*;

public class DaftarPutar {
    //NAMA  : KHANSA SALSABILA SANGDIVA LAKSONO
    //NIM   : 215150201111068
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int banyakPerintah = input.nextInt();
        int sedangDiputar = 1;

        for(int i = 0; i <= banyakPerintah; i++){
            String perintah = input.nextLine();
            String[] arrayPerintah = perintah.split(" ");

            if(arrayPerintah[0].equalsIgnoreCase("INSERT")){
                playlist.insertLagu(arrayPerintah[1]); //["INSERT", "dataLagu"], yang diambil index ke-1 maka "dataLagu"
            }

            else if(arrayPerintah[0].equalsIgnoreCase("COUNT")){
                playlist.printBanyakLagu();
            }

            else if(arrayPerintah[0].equalsIgnoreCase("PLAY")){
                playlist.printSedangDiputar(sedangDiputar);
            }

            else if(arrayPerintah[0].equalsIgnoreCase("SWAP")){
                int urutanAwal = Integer.parseInt(arrayPerintah[1]); //parseInt digunakan untuk konversi String ke int
                int urutanTujuan = Integer.parseInt(arrayPerintah[2]);

                String laguAwal = playlist.getDataLagu(urutanAwal);
                String laguTujuan = playlist.getDataLagu(urutanTujuan);

                playlist.swapLagu(urutanAwal, laguTujuan); 
                playlist.swapLagu(urutanTujuan, laguAwal); 
                //tukaran judul lagu, misal urutan 1 yang sebelumnya "kekasih" maka ditukar jadi "buih"
                //urutan 3 yang sebelumnya "buih" maka jadi "kekasih"
            }

            else if(arrayPerintah[0].equalsIgnoreCase("NEXT")){
                sedangDiputar++; 
                //urutan yang sedangDiputar bermula dari urutan 1, jika NEXT maka urutan terkini bertambah 1
                playlist.printSedangDiputar(sedangDiputar);
            }

            else if(arrayPerintah[0].equalsIgnoreCase("PREV")){
                sedangDiputar--;
                //urutan yang sedangDiputar bermula dari urutan 1, jika PREV maka urutan terkini berkurang 1
                playlist.printSedangDiputar(sedangDiputar);
            }

            else if(arrayPerintah[0].equalsIgnoreCase("DELETE")){
                int urutanHapus = Integer.parseInt(arrayPerintah[1]);
                playlist.deleteLagu(urutanHapus);
            }
        }
    }
}

class Lagu {
    String dataLagu;
    Lagu next;
    Lagu prev;

    Lagu(){}

    Lagu(String dataLagu){
        this.dataLagu = dataLagu;
    }

    Lagu(String dataLagu, Lagu prev, Lagu next){
        this.dataLagu = dataLagu;
        this.prev = prev;
        this.next = next;
    }
}

class Playlist {
    Lagu laguAwal;
    Lagu laguAkhir;
    int banyakLagu;

    void inisialisasi(){
        laguAwal = null;
        laguAkhir = null;
    }

    boolean isEmpty(){
        return (banyakLagu==0);
    }

    int getBanyakLagu(){
        return banyakLagu;
    }

    public void insertLagu(String input){ //menambahkan lagu
        Lagu tmp = new Lagu(input);
        if(isEmpty()){
            laguAwal = laguAkhir = tmp;
            banyakLagu++;
        } else{
            tmp.prev = laguAkhir; 
            laguAkhir.next = tmp;
            laguAkhir = tmp;
            banyakLagu++;
        }
    }

    public void swapLagu(int urutanAwal, String inputUrutanTujuan){ //menukar posisi lagu
        Lagu tmp = laguAwal;
        for(int i = 0; i < (urutanAwal-1); i++){
            tmp = tmp.next;
        }
        tmp.dataLagu = inputUrutanTujuan;
    }

    public void deleteLagu(int urutanHapus){
        Lagu tmp = laguAwal;
        for(int i = 1; i < urutanHapus; i++){
            tmp = tmp.next;
        }
        if(tmp == laguAwal){ //yang dihapus laguAwal
            tmp.prev = null;
            laguAwal = tmp.next;
            banyakLagu--;
        } else if(tmp == laguAkhir) { //yang dihapus laguAkhir
            laguAkhir = tmp.prev;
            tmp.next = null;
            banyakLagu--;
        } else { //yang dihapus lagu di antara laguAwal dan laguAkhir
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
            tmp.prev = null;
            tmp.next = null;
            banyakLagu--;
        }
    }

    public String getDataLagu(int urutan){ //mencari dataLagu sesuai urutan
        Lagu tmp = laguAwal;
        for(int i = 1; i < urutan; i++){
            tmp = tmp.next;
        } return tmp.dataLagu;
    }

    public void printBanyakLagu(){
        System.out.println("Total lagu: " + getBanyakLagu());
    }

    public void printSedangDiputar(int urutan){
        System.out.println("Sedang diputar: " + getDataLagu(urutan));
    }
}

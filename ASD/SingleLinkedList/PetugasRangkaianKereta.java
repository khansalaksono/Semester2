//https://www.hackerrank.com/contests/single-linked-list-1646052034/challenges/petugas-rangkaian-kereta

import java.util.*;

public class PetugasRangkaianKereta {
    //NAMA  : KHANSA SALSABILA SANGDIVA LAKSONO
    //NIM   : 215150201111068
    public static void main(String args[] ) throws Exception{
        Scanner input = new Scanner(System.in);
        String namaKereta = input.nextLine();
        String gerbongInput = input.nextLine();

        Kereta nama = new Kereta(namaKereta);
        nama.printNama(); //print nama kereta

        Gerbong krl = new Gerbong();
        String[] gerbongPerKode = gerbongInput.split("-"); //untuk memisahkan char "-"
        for(String i : gerbongPerKode){
            krl.addGerbong(new Gerbong(i));
        }
        krl.printkrl();
    }
}

class Kereta{
    private String namaKereta;

    Kereta(String namaKereta){
        this.namaKereta = namaKereta;
    }

    public void printNama(){
        System.out.println(this.namaKereta);
    }
}

class Gerbong{
    private String kodeGerbong;

    Gerbong(String kodeGerbong) {
        this.kodeGerbong = kodeGerbong;
    }

    Gerbong first, p; 

    public Gerbong() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void addGerbong(Gerbong gerbong) { 
        Gerbong gerbongBaru;
        if (isEmpty()) {
            first = gerbong;
            first.p = null;
        } else {
            gerbongBaru = first;
            while (gerbongBaru.p != null) {
                gerbongBaru = gerbongBaru.p;
            }
            gerbongBaru.p = gerbong;
        }
    }

    public void printkrl() {
        Gerbong current = first;
        if (current == null) {
            System.out.println("Kereta Tidak Tersedia");
        } else {
            while (current != null) {
                System.out.print(current.kodeGerbong);
                if (current.p != null) {
                    System.out.print("-"); //di ekor llist tidak terprint "-"
                }
                current = current.p;
            }
            System.out.println();
        }
    }
}

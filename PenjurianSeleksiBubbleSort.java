import java.io.*;
import java.util.*;

public class PenjurianSeleksiBubbleSort {

    public static void main(String[] args) {
        //input data
        Scanner input = new Scanner(System.in);
        int banyakData = input.nextInt();
        int diambil = input.nextInt();

        String[] nama = new String[banyakData];
        double[] A = new double[banyakData];
        double[] B = new double[banyakData];
        double[] jumlah = new double[banyakData];

        for(int i = 0; i < banyakData; i++){
            nama[i] = input.next();
            A[i] = input.nextDouble();
            B[i] = input.nextDouble();
            jumlah[i] = A[i] + B[i];
        }

        String cekNama = input.next();

        //sorting dkk
        PenjurianSeleksiBubbleSort sorter = new PenjurianSeleksiBubbleSort();
        sorter.bubble_sort(B, A, nama, jumlah);

        int posisiPeserta = sorter.check_name(cekNama, nama);
        if(posisiPeserta > diambil){
            System.out.println("GAGAL");
        } else{
            System.out.println("LOLOS");
        }
    }

    void bubble_sort(double[] B, double[] A, String[] nama, double[] jumlah) {
        int size = jumlah.length;

        for (int k = 0; k < ( size - 1 ); k++) {
            for (int j = 0; j < size - k - 1; j++) {
                if (jumlah[j] < jumlah[j+1])
                {
                    double tempJumlah = jumlah[j];
                    double tempB = B[j];
                    double tempA = A[j];
                    String tempNama = nama[j];

                    jumlah[j] = jumlah[j+1];
                    B[j] = B[j+1];
                    A[j] = A[j+1];
                    nama[j] = nama[j+1];

                    jumlah[j+1] = tempJumlah;
                    B[j+1] = tempB;
                    A[j+1] = tempA;
                    nama[j+1] = tempNama;
                }
            }
        }
    }
    int check_name(String nama, String[] namaArray){
        int size = namaArray.length;
        for(int i = 0; i < size; i++){
            if(namaArray[i].equals(nama)){
                return i;
            }
        }
        return 0;
    }
}
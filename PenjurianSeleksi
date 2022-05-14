import java.io.*;
import java.util.*;

public class PenjurianSeleksi {

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
        Solution sorter = new Solution();
        sorter.insertion_sort(B, A, nama, jumlah);

        int posisiPeserta = sorter.check_name(cekNama, nama);
        if(posisiPeserta > diambil){
            System.out.println("GAGAL");
        } else{
            System.out.println("LOLOS");
        }
    }
    
    void insertion_sort(double[] B, double[] A, String[] nama, double[] jumlah) {
        int size = jumlah.length;

        for (int k = 1; k < size; k++) {
            double tempJumlah = jumlah[k];
            double tempB = B[k];
            double tempA = A[k];
            String tempNama = nama[k];
            int j = k - 1;

            while (j >= 0 && tempJumlah > jumlah[j]) {
                jumlah[j + 1] = jumlah[j];
                B[j + 1] = B[j];
                A[j + 1] = A[j];
                nama[j + 1] = nama[j];
                --j;
            }
            jumlah[j + 1] = tempJumlah;
            B[j + 1] = tempB;
            A[j + 1] = tempA;
            nama[j + 1] = tempNama;
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

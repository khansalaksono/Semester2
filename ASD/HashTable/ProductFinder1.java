//https://www.hackerrank.com/contests/searching-n-hashtable-asd-a/challenges/product-finder-1/problem

import java.io.*;
import java.util.*;

public class ProductFinder1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hashtable<Integer, String> barang = new Hashtable<>();
        int n = input.nextInt();

        for(int i = 0; i < n; i++){
            try{
                Integer id = input.nextInt();
                String b = input.next();
                barang.put(id, b);
            } catch (Exception e){
                continue;
            }
        }

        try{
            int cari = input.nextInt();
            if(barang.containsKey(cari)){
                for(Map.Entry<Integer, String> listBarang : barang.entrySet()){
                    if(listBarang.getKey() == cari){
                        System.out.println(listBarang.getValue());
                        break;
                    }
                }
            }

            else{
                System.out.println("TIDAK DITEMUKAN");
            }
        } catch (Exception e){
            System.out.println("TIDAK DITEMUKAN");
        }
    }
}

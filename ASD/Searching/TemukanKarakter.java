//https://www.hackerrank.com/contests/searching-n-hashtable-asd-a/challenges/temukan-karakter/problem

import java.util.*;

public class TemukanKarakter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String karakterRaw = input.nextLine();
        String[] karakter = karakterRaw.split(" ");
        String cari = input.next();

        boolean found = false;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (karakter[i].equals(cari)) {
                found = true;
                index = i + 1;
                break;
            }
        }

        if (found == true) {
            System.out.print(index);
        } else {
            System.out.print(0);
        }
    }
}

//https://www.hackerrank.com/contests/stack-a-asd-a/challenges/xml-markup/submissions

import java.util.Scanner;
import java.util.EmptyStackException;

class Node {
   String data;
   Node next;

   public Node(){}

   public Node(String data){
       this.data = data;
   }

   public Node(String data, Node pointer){
       this.data = data;
       this.next = pointer;
   }
}

class StackLinkedList {
   Node top, head, tail;

   void inisialisasi() {
       head = tail = top = null;
   }

   public boolean isEmpty() { //mengecek kondisi stack dalam keadaan kosong
       return top == null;
   }

   public void addLast(String data) {
       Node temp = new Node(data);
       if (isEmpty()) {
           head = tail = temp;
       } else {
           tail.next = temp;
           tail = temp;
       }
   }

   public void push(String data) { //penambahan elemen pada stack, top bergeser
       addLast(data);
       top = tail;
   }
    
    public String peek() throws EmptyStackException{ //ngakses elemen top (elemen yang terakhir kali ditambahkan
        if(!isEmpty()){
            return this.top.data;
        } else{
            throw new EmptyStackException();
        }
    }

   public void removeLast() {
       if (!isEmpty()) {
           if (head == tail) {
               head = tail = null;
           } else {
               Node temp = head;
               while (temp.next != tail) {
                   temp = temp.next;
               }
               temp.next = null;
               tail = temp;
           }
       }
   }

   public String pop() throws EmptyStackException { //pengambilan data pada stack
       if (isEmpty()) {
           throw new EmptyStackException(); //atau StackUnderflow
       }
       String tempData = top.data;
       removeLast();
       top = tail;
       return tempData;
   }
}

public class XMLMarkup {
   public static void main(String[] args) {
       if (cekTag()) { //cekTag()==true
           System.out.print("TRUE");
       } else {
           System.out.print("FALSE");
       }
   }

   static boolean cekTag() {
       Scanner input = new Scanner(System.in);
       StackLinkedList stack = new StackLinkedList();
       String tag = "";
// <html> </html> <body> </body> TRUE
// <php> <html> </html> FALSE
// <html> <body> </html> </body> FALSE
       while (input.hasNext()) {
          tag += input.next() + "\n";
          if(tag.equals("")) break;
       }

       String[] arrayTag = tag.split("\n"); //input berupa array
       // arrayTag[0] = <html>
       // arrayTag[1] = </html>
       // arrayTag[2] = <body>
       // arrayTag[3] = </body>

       for (int i = 0; i < arrayTag.length; i++) {
           if (arrayTag[i].equalsIgnoreCase("<html>") ||
               arrayTag[i].equalsIgnoreCase("<body>") ||
               arrayTag[i].equalsIgnoreCase("<php>")) {
                    stack.push(arrayTag[i]); //inputan yang ada di array masuk ke stack
           }
           
           else if (stack.isEmpty()){
                return false;
            }

           else{
               String x = stack.pop();
               if ((x.equalsIgnoreCase("<html>") && !arrayTag[i].equalsIgnoreCase("</html>")) ||
                   (x.equalsIgnoreCase("<body>") && !arrayTag[i].equalsIgnoreCase("</body>")) ||
                   ((x.equalsIgnoreCase("<php>") && !arrayTag[i].equalsIgnoreCase("</php>")))) {
                       return false;
               }
           }
       }
       return stack.isEmpty();
   }
}

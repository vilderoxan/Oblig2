package oblig2;

public class Main {
    public static void main(String[] args) {
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        DobbeltLenketListe<Integer> liste2 = new DobbeltLenketListe<>();


        for(int i = 0; i < 100000; i++){
            liste.leggInn(i);
        }
        long tid1 = System.currentTimeMillis();
        liste.nullstill();
        tid1 = System.currentTimeMillis() - tid1;
        System.out.println(tid1);



        for(int i = 0; i < 100000; i++){
            liste2.leggInn(i);
        }
        long tid2 = System.currentTimeMillis();
        liste2.nullstill2();
        tid2 = System.currentTimeMillis() - tid2;
        System.out.println(tid2);


















    }
}

package oblig2;

public class Main {
    public static void main(String[] args) {


        String[] navn = {"Lars"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        System.out.println(liste);

        liste.fjern("Lars");
        System.out.println(liste);

        liste.fjern("Per");
        System.out.println(liste);

        liste.fjern("Berit");
        System.out.println(liste);

// Utskrift:
// Lars Anders Bodil Kari Per Berit
// Lars Anders Bodil Kari Per Berit

        /*

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        DobbeltLenketListe<Integer> liste2 = new DobbeltLenketListe<>();


        for(int i = 0; i < 10; i++){
            liste.leggInn(i);
        }
        long tid1 = System.currentTimeMillis();
        liste.nullstill();
        tid1 = System.currentTimeMillis() - tid1;
        System.out.println(tid1);



        for(int i = 0; i < 10; i++){
            liste2.leggInn(i);
        }
        long tid2 = System.currentTimeMillis();
        liste2.nullstill2();
        tid2 = System.currentTimeMillis() - tid2;
        System.out.println(tid2);

         */


    }
}

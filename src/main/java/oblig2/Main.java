package oblig2;

public class Main {
    public static void main(String[] args) {


        Liste<Integer> liste = new DobbeltLenketListe<>();
        liste.leggInn(1);
        liste.leggInn(2);
        liste.leggInn(3);


        liste.leggInn(0, 10);
        System.out.println(liste);
        liste.leggInn(4, 10);
        System.out.println(liste);
        liste.leggInn(2, 10);
        System.out.println(liste);
    }
}

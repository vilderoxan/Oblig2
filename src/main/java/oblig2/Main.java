package oblig2;

public class Main {
    public static void main(String[] args) {


        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();
        liste.leggInn("En");
        liste.leggInn("To");
        liste.leggInn("Tre");
        liste.leggInn("En");


        liste.fjern("En");
        System.out.println(liste);
        System.out.println(liste.omvendtString());
    }
}

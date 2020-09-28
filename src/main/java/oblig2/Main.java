package oblig2;

public class Main {
    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();
        System. out .println(liste. antall () + " " + liste. tom ());
        // Utskrift: 0 true
        String[] s = { "Ole" , null , "Per" , "Kari ", null };
        Liste<String> liste2 = new DobbeltLenketListe<>(s);
        System.out.println(liste2.antall() + " " + liste2.tom ());
        System.out.println(liste2.toString());
// Utskrift: 3 false
    }
}

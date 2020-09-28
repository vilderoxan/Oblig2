package oblig2;

public class Main {
    public static void main(String[] args) {

        String[] s1 = {};
        String[] s2 = {"A"};
        String[] s3 = {null, "A", null, "B", null};

        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString() + " " + l2.toString() + " " + l3.toString()
                + l1.omvendtString() + " " + l2.omvendtString() + " " + l3.omvendtString());


        /*
        String[] s3 = {null, "A", null, "B", null};
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(s3);
        System.out.println(liste.toString() + "\n" + liste.omvendtString());

        /*
        for ( int i = 1; i <= 3; i++)
        {
            liste.leggInn(i);
        }
        System.out.println(liste.toString());
        System.out.println(liste.omvendtString());

         */
// Utskrift:
// [] []
// [1] [1]
// [1, 2] [2, 1]
// [1, 2, 3] [3, 2, 1]


    }
}

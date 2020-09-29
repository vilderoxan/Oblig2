package oblig2;

public class Main {
    public static void main(String[] args) {
        Character[] c = { 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' ,};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System. out .println(liste.subliste(3,8)); // [D, E, F, G, H]
        System. out .println(liste.subliste(5,5)); // []
        System. out .println(liste.subliste(8,liste.antall())); // [I, J]
        System.out.println(liste.subliste(0,11)); // skal kaste unntak


        //System.out.println(l3.hent(2));
       // System.out.println(l1.toString() + " " + l2.toString() + " " + l3.toString()
        //        + l1.omvendtString() + " " + l2.omvendtString() + " " + l3.omvendtString());



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

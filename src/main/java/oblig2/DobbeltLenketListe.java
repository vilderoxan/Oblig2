package oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import javax.swing.*;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        this.hode = this.hale = null;
        this.antall = 0;
    }

    public DobbeltLenketListe(T[] a) {
        this();  // alle variabelene er nullet

        Objects.requireNonNull(a, "Tabellen a er null");

        // Finner den første i a som ikke er null

        int i = 0; // plassen til første verdi som ikke er null

        for (; i < a.length && a[i] == null; i++) ;

        if (i < a.length) { // hvis alle har verdien null så vil i == a.length
            hode = new Node<>(a[i], null, null);// den første noden
            antall = 1;                                 // vi har minst en node

            Node<T> current = hode;

            for (i = i + 1; i < a.length; i++) {
                if (a[i] != null) {
                    Node<T> next = new Node<>(a[i], current, null);   // en ny node
                    current.neste = next;
                    current = next;
                    antall++;
                }
            }
            hale = current;
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        int antall = 0;

        if (tom() || fra == til) {
            return subliste;
        }

        Node<T> current = hode;

        for (int i = 0; i <= fra; i++) { //Legger til noden på plass fra
            if (i != fra) {
                current = current.neste;
            }
        }
        subliste.leggInn(current.verdi);

        current = current.neste;

        for (int i = fra + 1; i < til; i++) {
            subliste.leggInn(current.verdi);
            current = current.neste;
        }

        this.endringer = 0;

        return subliste;
    }

    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");

    }


    @Override
    public int antall() {
        return this.antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    //Hvorfor skal metoden alltid returnere true? Da kan den like så gjerne være void!

    @Override
    public boolean leggInn(T verdi) {// verdi legges bakerst
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if (antall == 0) hode = hale = new Node<>(verdi, null, null);  // tom liste
        else hale = hale.neste = new Node<>(verdi, hale, null);  // legges bakerst

        antall++;        // en mer i listen
        endringer++;      // en endring mer på listen
        return true;     // vellykket innlegging
        //Oppgave 2b ferdig
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);        // true: indeks = antall er lovlig

        Node<T> q = new Node<>(verdi);

        if (indeks == antall) {
            // ingen noder skal flyttes
            if (antall == 0) {
                // ingen noder fra før
                hode = q;
            } else {
                // putt inn bakerst
                q.forrige = hale;
                hale.neste = q;
            }
            hale = q;
        } else {
            // fletting / node må flyttes
            Node<T> r = finnNode(indeks);
            q.neste = r;

            if (r.forrige == null) {
                // flytt var hode - erstatt med ny
                hode = q;
            } else {
                // flytt var ikke hode
                r.forrige.neste = q;
                q.forrige = r.forrige;
            }
            r.forrige = q;
        }

        antall++;                            // listen har fått en ny verdi
        endringer++;                         // listen har en ny endring
    }


    private Node<T> finnNode(int indeks) {
        int midten = antall / 2;
        if (indeks < midten) {
            Node<T> p = hode;
            for (int i = 0; i < midten; i++) {
                if (i == indeks) {
                    return p;
                }
                p = p.neste;
            }
        } else {
            Node<T> p = hale;
            for (int i = antall - 1; i >= midten; i--) {
                if (i == indeks) {
                    return p;
                }
                p = p.forrige;
            }
        }
        return null;
    }


    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);  // false: indeks = antall er ulovlig
        Node<T> n = finnNode(indeks);
        if (n != null) {
            return n.verdi;
        }
        return null;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }

        Node<T> current = hode;

        for (int i = 0; i < antall; i++) {
            if (current.verdi.equals(verdi)) {
                return i;
            }
            current = current.neste;
        }
        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "null er ulovlig!");
        indeksKontroll(indeks, false);  // false: indeks = antall er ulovlig

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;
        p.verdi = nyverdi;

        endringer++;

        return gammelVerdi;
    }


    /*
I begge metodene må du passe på tilfellene 1) den første fjernes, 2) den siste fjernes og 3)
en verdi mellom to andre fjernes. Alle neste- og forrige-pekere må være korrekte etter
fjerningen. Variabelen antall skal også reduseres og variabelen endringer økes. Sjekk
også tilfellet at listen blir tom etter fjerningen, blir korrekt behandlet.
     */
    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {    // ingen nullverdier i listen
            return false;
        }

        Node<T> n = hode;
        Node<T> p;
        Node<T> q;

        while (n != null) {
            if (n.verdi.equals(verdi)) {
                break;
            }
            n = n.neste; //flytter n til n.neste
        }

        if (n == null) {   // fant ikke verdi
            return false;
        } else {
            if (n == hode) { // første verdi skal fjernes
                q = n.neste;
                hode = q;
                q.forrige = null;
                n.verdi = null;
                n.neste = null;
            } else if (n == hale) { //siste verdi skal fjernes
                p = n.forrige;
                hale = p;
                p.neste = null;
                n.verdi = null;
                n.forrige = null;
            } else { //mellom to verdier
                q = n.neste;
                p = n.forrige;

                p.neste = q;
                q.forrige = p;

                n.verdi = null;
                n.neste = null;
                n.forrige = null;
            }


            endringer++;                        // fjerning er en endring
            antall--;

            if (antall == 0) {
                hode = hale = null;
            }


            return true;
        }

    }


    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        if (antall == 0) {
            throw new IndexOutOfBoundsException("listen er tom");
        }

        Node<T> n = finnNode(indeks);


        if (indeks == 0) {
            hode = n.neste;
            n.neste.forrige = null;
        } else if (n.neste == null) {
            hale = n.forrige;
            n.forrige.neste = null;
        } else {
            Node<T> p = n.forrige;
            Node<T> r = n.neste;

            p.neste = r;
            r.forrige = p;
        }

        if (antall == 0) {
            hode = hale = null;
        }


        antall--;
        endringer++;


        return n.verdi;
    }

    @Override
    public void nullstill() {
        Node<T> p = hode;
        Node<T> q = null;


        while (p != null) {
            q = p.neste;
            p.neste = null;
            p.verdi = null;
            p.forrige = null;
            p = q;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
    }

    public void nullstill2() {
        for (int i = 0; i < antall; i++) {
            fjern(i);
        }
        hode = hale = null;
        antall = 0;
        endringer++;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom()) {
            Node<T> p = hode;
            s.append(p.verdi);

            p = p.neste;

            while (p != null) {  // tar med resten hvis det er noe mer
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }
        s.append(']');

        return s.toString();
    }


    public String omvendtString() {
        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom()) {
            Node<T> p = hale;
            s.append(p.verdi);

            p = p.forrige;

            while (p != null) {  // tar med resten hvis det er noe mer
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }

        }
        s.append(']');

        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        /*
Lag metoden T next() . Den skal først sjekke om iteratorendringer er lik endringer .
Hvis ikke, kastes en ConcurrentModificationException . Så en NoSuchElementException
hvis det ikke er flere igjen i listen (dvs. hvis hasNext () ikke er sann/true). Deretter
settes fjernOK til sann/true, verdien til denne returneres og denne flyttes til den neste node.
         */

        @Override
        public T next() {
            if (endringer != iteratorendringer)
                throw new ConcurrentModificationException("Listen er endret!");

            if (!hasNext()) throw new
                    NoSuchElementException("Tomt eller ingen verdier igjen!");

            fjernOK = true;            // nå kan remove() kalles
            
            Node<T> p = null;
            T denneVerdi = p.verdi;    // tar vare på verdien i p
            p = p.neste;               // flytter p til den neste noden

            return denneVerdi;         // returnerer verdien
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }


} // class DobbeltLenketListe




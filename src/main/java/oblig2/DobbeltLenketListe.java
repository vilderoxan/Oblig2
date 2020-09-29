package oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


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
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return this.antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
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


    /*
    Lag
    deretter metoden public T hent(int indeks) ved å bruke finnNode () . Pass på
    at indeks sjekkes. Bruk metoden indeksKontroll () som arves fra Liste (bruk false som
    parameter)
     */
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
        throw new UnsupportedOperationException();
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
     Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = verdi;
        return gammelVerdi;

    T gammelverdi = a[indeks];      // tar vare på den gamle verdien
    a[indeks] = verdi;              // oppdaterer
        return gammelverdi;             // returnerer den gamle verdien*/
    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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




package oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import java.util.Iterator;
import java.util.Objects;

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

        endringer = 0;


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

    //Hvorfor skal metoden leggInn alltid returnere true?
    // Da kan den like så gjerne være void!?

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


/*
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);        // true: indeks = antall er lovlig
        Node<T> q = new Node<>(verdi);

        if (antall == 0) {
            hode = hale = q;
        } else {
            Node<T> n = finnNode(indeks); // n skal flyttes en til høyre

            if (n.forrige == null) { //Noden skal settes inn først
                q.neste = n;
                q.forrige = null;
                n.forrige = q;
                hode = q;
            } else if (n.neste == null) {
                n.neste = q;
                q.neste = null;
                q.forrige = n;
                hale = q;
            } else {
                Node<T> p = n.forrige;
                p.neste = q;
                q.neste = n;
                n.forrige = q;
                q.forrige = p;
            }
        }
        antall++;                            // listen har fått en ny verdi
        endringer++;
    }

 */


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


    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {    // ingen nullverdier i listen
            return false;
        }

        Node<T> n = hode;
        while (n != null) {
            if (n.verdi.equals(verdi)) {
                break;
            }
            n = n.neste; //flytter n til n.neste
        }

        if (n == null) {   // fant ikke verdi
            return false;
        }

        if (antall == 1) {
            hode = hale = null;
        } else {
            if (n.forrige == null) {
                // fant i første node
                hode = n.neste;
                hode.forrige = null;
            } else if (n.neste == null) {
                // fant i siste node
                hale = n.forrige;
                hale.neste = null;
            } else {
                // fant midt i
                Node<T> p = n.forrige;
                Node<T> q = n.neste;
                p.neste = q;
                q.forrige = p;
            }
        }

        n.neste = null;
        n.forrige = null;
        n.verdi = null;

        endringer++;
        antall--;
        return true;
    }


    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        if (antall == 0) {
            throw new IndexOutOfBoundsException("listen er tom");
        }

        Node<T> n = finnNode(indeks);

        if (antall == 1) {
            hode = hale = null;
        } else {
            if (n.forrige == null) {
                //noden er den første
                hode = n.neste;
                hode.forrige = null;
            } else if (n.neste == null) {
                hale = n.forrige;
                hale.neste = null;
            } else {
                Node<T> p = n.forrige;
                Node<T> q = n.neste;
                p.neste = q;
                q.forrige = p;
            }
        }

        antall--;
        endringer++;

        return n.verdi;
    }

    @Override
    public void nullstill() {
        Node<T> p = hode;

        while (p != null) {
            Node<T> q = p.neste;
            p.neste = null;
            p.verdi = null;
            p.forrige = null;
            p = q;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
    }

    // Andre versjon an nullstill-metoden. Denne er mindre effektiv enn den ovenfor
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

        if (antall > 0) {
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
        return new DobbeltLenketListeIterator();
    }


    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
            denne = finnNode(indeks);

            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }


        @Override
        public T next() {
            if (endringer != iteratorendringer)
                throw new ConcurrentModificationException("Listen er endret!");

            if (!hasNext()) throw new
                    NoSuchElementException("Tomt eller ingen verdier igjen!");

            fjernOK = true;            // nå kan remove() kalles

            T denneVerdi = denne.verdi;    // tar vare på verdien
            denne = denne.neste;               // flytter denne til den neste noden

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




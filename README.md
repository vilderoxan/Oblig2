# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Vilde Roksasand Fauchald, S346419, s346419@oslomet.no
* Chonlawit With-Pettersen, S311596, s311596@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt. 
Vi har ...? commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Vi har jobbet veldig tett sammen
* Selv om vi noen ganger har jobbet hver for oss så har vi samarbeidet om hver metode

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

Oppgavene 1-8 består den utleverte testen. Vi har ikke implementert oppgave 9 og 10

# Oppgave 1: Løste ved å implementere..

*int antall():

Siden variabelen antall alltid skal oppdateres ved hver node som legges inn eller fjernes
implementerte vi metoden slik at den returnerer denne variabelen. 


*boolean tom():

Variabelen antall vil være oppdatert. Dersom antall er 0 vil tabellen være tom.
Derfor implementerte vi metoden til å returnere om antall er null eller ikke.

*public DobbeltLenketListe(T[] a):

Her tenkte vi at vi kan løpe gjennom arrayet som sendes inn med en for-løkke
og legger til første node som ikke er null. Dersom alle verdiene er null vil ikke noen noder legges inn. 
Dermed løper vi gjennom resten av arrayet og legger til Node som ikke er null.
Pekere oppdateres etter hver Node som legges inn.
Til slutt sier vi at hale peker på siste Node. 

# Oppgave 2a:

*String toString():

Her valgte vi å bruke en Stringbuilder for å komponere output.
Dermed løper vi igjennom Nodene med en While-løkke og beveger oss mot høyre ved neste pekere. 
While løkke valgte vi fordi man kan gjøre det så lenge Node ikke er null. Siste node vil pekke på null og dermed vil løkken stoppe.

*omvendtString ():

Her gjorde vi det samme som i toString-metoden, men startet fra halen og endre nestepeker
til forrigepeker.

# Oppgave 3a:

*finnNode(int indeks):

Her fant vi først midten og løp igjennom en av "sidene" basert på indeks > eller < enn midten.
Hvis indeks er større enn midten løper vi fra hale og mot midten. Hvis indeks er mindre enn midten løper vi fra hode til midten
Vi valgte en for løkke for å itterere gjennom listen.

*T hent(int indeks):

Vi vlagte å kalle på finnNode(indeks) for å finne noden på gitte indeks og returnerer verdien til Noden hvis vi finner den.
Hvis ikke returnerer den null.

*T oppdater(int indeks, T nyVerdi):

Vi finner Noden ved hjelp av finnNode(int indeks)
Dermed mellomlagrer vi verdien til en variabel (gammelVerdi) og setter ny verdi til Noden vi har funnet.
Metoden returnerer gammelVerdi.


# Oppgave 3b:

*subliste(int fra, int til):

Sjekker alle feilsitasjoner og om listen er tom eller om intervallet er tom. Da returnerer vi et en tom liste.

Vi finner noden på den første plassen vi er interessert i ved hjelp av en for-løkke. Legger til noden på indeks "fra"
Dermed løper vi igjennom intervallet "fra + 1" til den siste indeksen og legger til alle verdiene.

Siden vi kaller på metoden leggInn(verdi) vil antall oppdateres via denne metoden.

Vi setter subliste.endringer = 0 fordi det er en ny liste uten endringer. 


# Oppgave 4:

*int indeksTil(T verdi):

Først returnerer sjekker vi om verdien er null. I såfall returnerer vi -1.

Hvis ikke null, løper vi fra hode til antall med en for-løkke og sjekker om verdien til noden er lik verdien som sendes inn og returnerer indeks.
Om verdien ikke finnes returnerer vi også -1.

*boolean inneholder(T verdi):

Her sjekker vi om indeksTil(T verdi) returner -1. I såfall returnerer vi false.
Hvis indeksTil(T verdi) returnerer indeks så returnerer metoden true fordi listen inneholder verdien.


# Oppgave 5:

*void leggInn(int indeks, T verdi):

NB! Sjekk om min eller Mikael sin versjon skal stå på lab!!

# Oppgave 6:

*T fjern(int indeks)
Antall kan ikke være større enn indeks. Kaster exception.
Sjekker om listen er tom. I såfall kaster vi exception (ingenting kan fjernes).

Dermed kaller på metoden finnNode(indeks) og sjekker om noden ligger først, bakerst eller mellom
to noder. Vi sjekker også om det kun er en node. Da skal hode være lik hale = null.

*boolean fjern(T verdi)

Returnerer false hvis verdi == null.

Bruker dermed while-løkke for å løpe igjennom listen så lenge noden ikke er null. 
Breaker ut dersom vi finner verdi. Da har vi funnet noden.

Dermed sjekker vi om noden ligger først, sist eller mellom to noder og oppdaterer pekere deretter og nuller ut verdien til noden som skal fjernes


 
# Oppgave 7:

*void nullstill():

Vi har kodet begge alternativene. Metoden som er kodet direkte uten å kalle på metoden fjern
bruker gjennomsnittlig halvparten så lang tid når vi kjører testen. Vi prøvde å teste dette i main med en metode som måler tid
og da er det også metoden som bruker fjern() som bruker lengst tid. 

2000ms vs. 3ms

Metodekall er mer inneffektivt enn direkte kode.

Vi testet det slik i main:

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

# Oppgave 8:

*T next()
Først tar vi av oss feilsituasjoner: endringer != itteratorendringer samt dersom listen ikke har flere elementer igjen.
Dersom dette ikke skjer så blir fjernOK = true og vi returnerer verdien til denne for å så flytte denne til neste node.

*private DobbeltLenketListeIterator(int indeks)
Her valgte vi å kalle på metoden finnNode(indeks) for å sette noden til varibelen "denne".

*Iterator<T> iterator(int indeks)
Sjekker at indeksen er lovlig vha metoden indekskontroll. Hvis lovlig returnerer metoden en instans av DobbeltlenketlisteItterator med indeksen som argument

# Warnings:

Vi har 9 warnings. Noen av de sier at metodene i DobbeltlenketListe bør være private.
Resten sier at noen varibaler kan gi nullpointerexception. Siden alle testene består gjør
jeg ingenting med dette. Dette er avklart med Andre på lab. 

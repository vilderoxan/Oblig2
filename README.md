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
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: Løste ved å implementere..

#int antall():

Siden variabelen antall alltid skal oppdateres ved hver node som legges inn eller fjernes
implementerte vi metoden slik at den returnerer denne variabelen. 


#boolean tom():

Variabelen antall vil være oppdatert. Dersom antall er 0 vil tabellen være tom.
Derfor implementerte vi metoden til å returnere om antall er null eller ikke.

#public DobbeltLenketListe(T[] a):

Her tenkte vi at vi kan løpe gjennom arrayet som sendes inn med en for-løkke
og legger til første node som ikke er null. Dersom alle verdiene er null vil ikke noen noder legges inn. 
Dermed løper vi gjennom resten av arrayet og legger til Node som ikke er null.
Pekere oppdateres etter hver Node som legges inn.
Til slutt sier vi at hale peker på siste Node. 

* Oppgave 2a:

#String toString():

Her valgte vi å bruke en Stringbuilder for å komponere output.
Dermed løper vi igjennom Nodene med en While-løkke og beveger oss mot høyre ved neste pekere. 
While løkke valgte vi fordi man kan gjøre det så lenge Node ikke er null. Siste node vil pekke på null og dermed vil løkken stoppe.

#omvendtString ():

Her gjorde vi det samme som i toString-metoden, men startet fra halen og endre nestepeker
til forrigepeker.






package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.util.EinUndAusgabe;


public class NutzerEingabe {
    private EinUndAusgabe io;

    public NutzerEingabe(EinUndAusgabe io){
        this.io = io;
    }
    public int anzahlZeilenDesSpielfelds(){
        int breite;
        do {

            System.out.print("Bitte Breite des Feldes eingeben\n(5-100)Breite:");
            breite = io.leseInteger();
        }while (breite > 100 || breite < 5);
       return breite;
    }
    public int wahrscheinlichkeitDerBesiedlung(){
        int wahrscheinlichkeit;
        do {

            System.out.print("Wahscheinlickeit einer Besiedlung \n(0-100):");
            wahrscheinlichkeit = io.leseInteger();
        }while (wahrscheinlichkeit < 0 || wahrscheinlichkeit > 100);
        return wahrscheinlichkeit;
    }
    public int anzahlDerSimulationsschritte(){
        int simulationsSchritte;
        do {

            System.out.print("Anzahl durchzufuehrender Simulationsschritte \n(Maximal 5 | Abbruch bei negativer Zahl=:");
            simulationsSchritte = io.leseInteger();
        }while (simulationsSchritte > 5);
        return simulationsSchritte;
    }
}

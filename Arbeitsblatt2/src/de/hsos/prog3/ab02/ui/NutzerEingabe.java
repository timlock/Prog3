package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.util.EinUndAusgabe;

import java.util.Scanner;

public class NutzerEingabe {
    private EinUndAusgabe io;

    public NutzerEingabe(EinUndAusgabe io){
        this.io = io;
    }
    public int anzahlZeilenDesSpielfelds(){
        Scanner eingabe = new Scanner(System.in);
        int breite;
        do {

            System.out.print("Bitte Breite des Feldes eingeben\nBreite:");
            breite = eingabe.nextInt();
        }while (breite < 12);
       return breite;
    }
    public int wahrscheinlichkeitDerBesiedlung(){
        Scanner eingabe = new Scanner(System.in);
        int wahrscheinlichkeit;
        do {

            System.out.print("Wahscheinlickeit einer Besiedlung \n(0-100):");
            wahrscheinlichkeit = eingabe.nextInt();
        }while (wahrscheinlichkeit <= 0 || wahrscheinlichkeit >= 100);
        return wahrscheinlichkeit;
    }
    public int anzahlDerSimulationsschritte(){
        Scanner eingabe = new Scanner(System.in);
        int simulationsSchritte;
        do {

            System.out.print("Anzahl durchzufuehrender Simulationsschritte \n(Abbruch bei negativer Zahl:");
            simulationsSchritte = eingabe.nextInt();
        }while (simulationsSchritte > 10);
        return simulationsSchritte;
    }
}

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

            System.out.print("Bitte Wahrscheinlichkeitswert zwischen 1 und 100 eingeben\nWahrscheinlichkeit:");
            wahrscheinlichkeit = eingabe.nextInt();
        }while (wahrscheinlichkeit < 0 || wahrscheinlichkeit > 100);
        return wahrscheinlichkeit;
    }
    public int anzahlDerSimulationsschritte(){
        Scanner eingabe = new Scanner(System.in);
        int simulationsSchritte;
        do {

            System.out.print("Bitte Anzahl der Simulationsschritte eingeben (zwischen 1 und 100)\nSimulationsschritte:");
            simulationsSchritte = eingabe.nextInt();
        }while (simulationsSchritte < 0 || simulationsSchritte > 100);
        return simulationsSchritte;
    }
}

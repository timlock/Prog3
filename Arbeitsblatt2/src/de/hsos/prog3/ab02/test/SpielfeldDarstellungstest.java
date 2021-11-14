package de.hsos.prog3.ab02.test;

import de.hsos.prog3.ab02.ui.SpielfeldDarstellung;
import de.hsos.prog3.ab02.util.Interaktionsbrett;

import java.util.Scanner;

public class SpielfeldDarstellungstest {
    public static void main(String[] args) {
        Interaktionsbrett ib = new Interaktionsbrett();
        SpielfeldDarstellung spielfeldDarstellung = new SpielfeldDarstellung(ib);
        boolean[][] spielfeld = {{true,false,true},{false,true, false}, {true,false,true}};
        spielfeldDarstellung.spielfeldDarstellen(spielfeld);
        Scanner eingabe = new Scanner(System.in);
        System.out.println("Abwischen");
        if(eingabe.nextBoolean())
            spielfeldDarstellung.abwischen();
    }
}

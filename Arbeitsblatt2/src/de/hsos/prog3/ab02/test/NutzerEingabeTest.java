package de.hsos.prog3.ab02.test;

import de.hsos.prog3.ab02.ui.NutzerEingabe;
import de.hsos.prog3.ab02.util.EinUndAusgabe;

public class NutzerEingabeTest {
    public static void main(String[] args) {
        EinUndAusgabe einUndAusgabe = new EinUndAusgabe();
        NutzerEingabe nutzerEingabe = new NutzerEingabe(einUndAusgabe);
        System.out.println(nutzerEingabe.anzahlZeilenDesSpielfelds());
        System.out.println(nutzerEingabe.wahrscheinlichkeitDerBesiedlung());
        System.out.println(nutzerEingabe.anzahlDerSimulationsschritte());

    }
}

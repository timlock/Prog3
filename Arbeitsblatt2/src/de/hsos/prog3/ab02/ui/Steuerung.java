package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.logik.BeiAenderung;
import de.hsos.prog3.ab02.logik.Simulation;
import de.hsos.prog3.ab02.logik.Simulator;
import de.hsos.prog3.ab02.util.EinUndAusgabe;
import de.hsos.prog3.ab02.util.Interaktionsbrett;

public class Steuerung implements BeiAenderung {
    NutzerEingabe nutzerEingabe;
    Simulation simulator;
    SpielfeldDarstellung spielFeldDarstellung;


    public void startDesSpiels(){
        initialisierung();
        int anzahlZellen = nutzerEingabe.anzahlZeilenDesSpielfelds();
        int wahrscheinlichkeit = nutzerEingabe.wahrscheinlichkeitDerBesiedlung();
        simulator.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);
        int simulationsschritte = nutzerEingabe.anzahlDerSimulationsschritte();
        if(simulationsschritte < 0) return;
        simulator.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);
        simulator.berechneFolgeGeneration(simulationsschritte);

    }
    public void initialisierung(){
        Interaktionsbrett interaktionsbrett = new Interaktionsbrett();
        spielFeldDarstellung = new SpielfeldDarstellung(interaktionsbrett);
        EinUndAusgabe einUndAusgabe = new EinUndAusgabe();
        nutzerEingabe = new NutzerEingabe(einUndAusgabe);
        simulator = new Simulator();
        simulator.anmeldenFuerAktualisierungBeiAenderung(this);
        simulator.anmeldenFuerAktualisierungBeiAenderung(this);
    }

    @Override
    public void aktualisiere(boolean[][] neu) {
        spielFeldDarstellung.spielfeldDarstellen(neu);
    }
}

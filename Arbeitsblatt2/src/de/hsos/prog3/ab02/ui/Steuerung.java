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

    Steuerung(){

    }

    public void startDesSpiels(){
        initialisierung();
        int anzahlZellen = nutzerEingabe.anzahlZeilenDesSpielfelds();
        int wahrscheinlichkeit= nutzerEingabe.wahrscheinlichkeitDerBesiedlung();
        simulator.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);
        int simulationsschritte = nutzerEingabe.anzahlDerSimulationsschritte();
        if(simulationsschritte < 1) return;
        simulator.berechneFolgeGeneration(simulationsschritte);

    }
    public void initialisierung(){
        Interaktionsbrett interaktionsbrett = new Interaktionsbrett();
        SpielfeldDarstellung spielFeldDarstellung = new SpielfeldDarstellung(interaktionsbrett);
        EinUndAusgabe einUndAusgabe = new EinUndAusgabe();
        NutzerEingabe nutzerEingabe = new NutzerEingabe(einUndAusgabe);
        simulator.anmeldenFuerAktualisierungBeiAenderung(this);
    }

    @Override
    public void aktualisiere(boolean[][] neu) {
        spielFeldDarstellung.spielfeldDarstellen(neu);
    }
}

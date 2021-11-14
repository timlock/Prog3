package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.logik.BeiAenderung;
import de.hsos.prog3.ab02.logik.Simulation;
import de.hsos.prog3.ab02.logik.Simulator;
import de.hsos.prog3.ab02.util.EinUndAusgabe;
import de.hsos.prog3.ab02.util.Interaktionsbrett;

public class Steuerung implements BeiAenderung {
    private NutzerEingabe nutzerEingabe;
    private Simulation simulator;
    private SpielfeldDarstellung spielFeldDarstellung;


    public void startDesSpiels() throws InterruptedException {
        int anzahlZellen = nutzerEingabe.anzahlZeilenDesSpielfelds();
        int wahrscheinlichkeit = nutzerEingabe.wahrscheinlichkeitDerBesiedlung();
        simulator.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);
        int simulationsschritte = nutzerEingabe.anzahlDerSimulationsschritte();
        do {
            simulator.berechneFolgeGeneration(simulationsschritte);
            simulationsschritte = nutzerEingabe.anzahlDerSimulationsschritte();
        }while (simulationsschritte > 0);
    }
    public void initialisierung(){
        Interaktionsbrett interaktionsbrett = new Interaktionsbrett();
        spielFeldDarstellung = new SpielfeldDarstellung(interaktionsbrett);
        EinUndAusgabe einUndAusgabe = new EinUndAusgabe();
        nutzerEingabe = new NutzerEingabe(einUndAusgabe);
        simulator = new Simulator();
        simulator.anmeldenFuerAktualisierungBeiAenderung(this);
    }

    public Simulation getSimulator() {
        return simulator;
    }

    public void setSimulator(Simulation simulator) {
        this.simulator = simulator;
    }

    @Override
    public void aktualisiere(boolean[][] neu) {
        spielFeldDarstellung.abwischen();
        spielFeldDarstellung.spielfeldDarstellen(neu);
    }
}

package de.hsos.prog3.ab02.test;

import de.hsos.prog3.ab02.logik.Simulation;
import de.hsos.prog3.ab02.logik.Simulator;
import de.hsos.prog3.ab02.ui.Steuerung;

public class SteuerungSimulationTest {
    public static void main(String[] args) throws InterruptedException {
        Steuerung steuerung = new Steuerung();
        steuerung.initialisierung();
        Simulation simulator = new Simulator();
        simulator.anmeldenFuerAktualisierungBeiAenderung(steuerung);
        simulator.berechneAnfangsGeneration(30,20);
        simulator.berechneFolgeGeneration(10);

    }
}

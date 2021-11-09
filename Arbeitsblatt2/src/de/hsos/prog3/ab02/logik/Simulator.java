package de.hsos.prog3.ab02.logik;

public class Simulator implements Simulation {
    private boolean[][] spielfeld;
    private int anzahlFelder;
    public BeiAenderung beiAenderung;

    public Simulator(int size){
        spielfeld = new boolean[size][size];
        anzahlFelder = size*size;
    }
    @Override
    public void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung) {

    }

    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) {

    }

    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {

    }
}

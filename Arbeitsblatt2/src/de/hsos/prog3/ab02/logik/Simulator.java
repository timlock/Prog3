package de.hsos.prog3.ab02.logik;

import java.util.Random;

public class Simulator implements Simulation {
    private boolean[][] spielfeld;
    private boolean[][] spielfeldNeu;
    private int anzahlZeilenn;
    public BeiAenderung beiAenderung;

    public Simulator(int size){
        spielfeld = new boolean[size][size];
        anzahlZeilenn = size*size;
    }
    @Override
    public void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung) {
        spielfeld = new boolean[anzahlDerZellen][anzahlDerZellen];
        Random random = new Random();
        for (int y = 0; y < anzahlDerZellen; y++) {
            for (int x = 0; x < anzahlDerZellen; x++) {
                if(random.nextInt(100) <= wahrscheinlichkeitDerBesiedlung) {
                    spielfeld[y][x] = true;
                }else {
                    spielfeld[y][x] = false;
                }
            }
        }
        beiAenderung.aktualisiere(spielfeld);

    }
    /*
       1. jede bewohnte Zelle mit genau zwei oder genau drei bewohnten Nachbarn bleibt bewohnt, sonst wird sie unbewohnt
       2. jede unbewohnte Zelle mit genau drei bewohnten Nachbarn wird bewohnt, bleibt sonst unbewohnt
       3. jede Zelle hat damit minimal drei (in der Ecke) und maximal acht Nachbarn (in der Mitte)

     */
    public int nachbarn(int y, int x){
        int anzahl = 0;
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++) {
                if((y - 1 + i) < anzahlZeilenn || )
                if(spielfeld[y - 1 +i][x - 1 + j])
            }
        }
        return anzahl;
    }

    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) {
        spielfeldNeu = new boolean[anzahlZeilenn][anzahlZeilenn];
        for (int i = 0; i < berechnungsschritte; i++) {
            for (int y = 0; y < anzahlZeilenn; y++) {
                for (int x = 0; x < anzahlZeilenn; x++) {


                }
            }
        }
        spielfeld = spielfeldNeu;
        beiAenderung.aktualisiere(spielfeld);

    }

    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {
        this.beiAenderung = beiAenderung;
    }
}

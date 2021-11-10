package de.hsos.prog3.ab02.logik;

import java.util.Random;

public class Simulator implements Simulation {
    private boolean[][] spielfeld;
    private boolean[][] spielfeldNeu;
    private int anzahlZeilen;
    public BeiAenderung beiAenderung;

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
    Zählt die Anzahl aller lebenden Nachbarzellen
     */

    public int nachbarn(int y, int x){
        int anzahl = 0;
        int nachbarY;
        int nachbarX;
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++) {
                nachbarY = y - 1 + i;
                nachbarX = x - 1 + j;
                if( nachbarY > 0 && nachbarY < anzahlZeilen && nachbarX > 0 && nachbarX < anzahlZeilen) {
                    if(spielfeld[nachbarY][nachbarX]) anzahl++;
                }
            }
        }
        if(spielfeld[y][x]) anzahl--;
        return anzahl;
    }

    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) {
        spielfeldNeu = new boolean[anzahlZeilen][anzahlZeilen];
        int anzahlNachbar;
        for (int i = 0; i < berechnungsschritte; i++) {
            for (int y = 0; y < anzahlZeilen; y++) {
                for (int x = 0; x < anzahlZeilen; x++) {
                    anzahlNachbar = nachbarn(y, x);
                    if(anzahlNachbar == 2 || anzahlNachbar == 3){
//                        1. jede bewohnte Zelle mit genau zwei oder genau drei bewohnten Nachbarn bleibt bewohnt, sonst wird sie unbewohnt
                        if(spielfeld[y][x]) spielfeldNeu[y][x] = true;
//                        2. jede unbewohnte Zelle mit genau drei bewohnten Nachbarn wird bewohnt, bleibt sonst unbewohnt
                        if(!spielfeld[y][x] && anzahlNachbar == 3)
                            spielfeldNeu[y][x] = true;
                    } else spielfeldNeu[y][x] = false;
                }
            }
            spielfeld = spielfeldNeu;
            beiAenderung.aktualisiere(spielfeld);
        }
    }

    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {
        this.beiAenderung = beiAenderung;
    }
}

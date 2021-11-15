package de.hsos.prog3.ab02.logik;
import java.util.Random;

public class Simulator implements Simulation {
    private boolean[][] spielfeld;
    private boolean[][] spielfeldNeu;
    private int anzahlDerZellen;
    private BeiAenderung beiAenderung;


    @Override
    public void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung) {
        spielfeld = new boolean[anzahlDerZellen][anzahlDerZellen];
        this.anzahlDerZellen = anzahlDerZellen;
        Random random = new Random();
        for (int x = 0; x < anzahlDerZellen; x++) {
            for (int y = 0; y < anzahlDerZellen; y++) {
                if(random.nextInt(100) <= wahrscheinlichkeitDerBesiedlung) {
                    spielfeld[x][y] = true;
                }else {
                    spielfeld[x][y] = false;
                }
            }
        }
        beiAenderung.aktualisiere(spielfeld);

    }
    /*
    Zählt die Anzahl aller lebenden Nachbarzellen
     */

    public int nachbarn(int x, int y){
        int anzahl = 0;
        int nachbarY;
        int nachbarX;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                nachbarX = x - 1 + i;
                nachbarY = y - 1 + j;
                if(nachbarY >= 0 && nachbarY < anzahlDerZellen && nachbarX >= 0 && nachbarX < anzahlDerZellen) {
                    if(spielfeld[nachbarX][nachbarY]) anzahl++;
                }
            }
        }
        if(spielfeld[x][y]) anzahl--;
        return anzahl;
    }

    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) throws InterruptedException {
        spielfeldNeu = new boolean[anzahlDerZellen][anzahlDerZellen];
        boolean wiederholung = false;
        int anzahlNachbar;
        for (int i = 0; i < berechnungsschritte; i++) {
            Thread.sleep(200);
            for (int x = 0; x < anzahlDerZellen; x++) {
                for (int y = 0; y < anzahlDerZellen; y++) {
                    anzahlNachbar = nachbarn(x, y);
                    if (spielfeld[x][y] && (anzahlNachbar < 2 || anzahlNachbar > 3)) {
                        spielfeldNeu[x][y] = false;
                    } else if (!spielfeld[x][y] && anzahlNachbar == 3) {
                        spielfeldNeu[x][y] = true;
                    } else if (spielfeld[x][y] && (anzahlNachbar == 2 || anzahlNachbar == 3)) {
                        spielfeldNeu[x][y] = true;
                    }
                    /*spielfeldNeu[x][y] = false;
                    if(spielfeld[x][y] && (anzahlNachbar < 2 || anzahlNachbar > 3)) spielfeldNeu[x][y] = false;
                    else if(anzahlNachbar == 2 || anzahlNachbar == 3){
//                        1. jede bewohnte Zelle mit genau zwei oder genau drei bewohnten Nachbarn bleibt bewohnt, sonst wird sie unbewohnt
                        if(spielfeld[x][y]) spielfeldNeu[x][y] = true;
//                        2. jede unbewohnte Zelle mit genau drei bewohnten Nachbarn wird bewohnt, bleibt sonst unbewohnt
                        else if(!spielfeld[x][y] && anzahlNachbar == 3) spielfeldNeu[x][y] = true;
                    }*/
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

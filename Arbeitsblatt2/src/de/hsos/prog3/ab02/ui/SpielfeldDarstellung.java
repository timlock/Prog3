package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.util.Interaktionsbrett;

public class SpielfeldDarstellung {
    private Interaktionsbrett ib;
    SpielfeldDarstellung(Interaktionsbrett ib){
        this.ib = ib;
    }

    public void spielfeldDarstellen(boolean[][] spielfeld){
        int laengeDarstellung = 100;
        int margin = 10;
        int quadratBreite = laengeDarstellung / spielfeld.length;
        Quadrat quadrat;
        for (int x = 0; x < spielfeld.length; x++) {
            for (int y = 0; y < spielfeld[x].length; y++) {
                quadrat = new Quadrat(margin + x * quadratBreite, margin + y * quadratBreite, quadratBreite);
                quadrat.darstellenRahmen(ib);
                quadrat.darstellenFuellung(ib);
            }

        }

    }
    public void abwischen(){
        ib.abwischen();
    }
}

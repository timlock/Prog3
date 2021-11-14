package de.hsos.prog3.ab02.test;

import de.hsos.prog3.ab02.ui.Quadrat;
import de.hsos.prog3.ab02.util.Interaktionsbrett;

public class QuadratTest {
    public static void main(String[] args) {
        Interaktionsbrett ib = new Interaktionsbrett();
        Quadrat quadrat;
        int margin = 30;
        int quadratBreite = 30;

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                quadrat = new Quadrat(margin + x * quadratBreite, margin + y * quadratBreite, quadratBreite);
                quadrat.darstellenRahmen(ib);
                if((y+1) % 2 == 0) quadrat.darstellenFuellung(ib);
            }

        }
    }
}

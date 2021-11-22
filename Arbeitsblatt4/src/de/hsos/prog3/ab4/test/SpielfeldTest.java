package de.hsos.prog3.ab4.test;
import de.hsos.prog3.ab4.Spielfeld;
import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class SpielfeldTest {
    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld();
        Interaktionsbrett ib = new Interaktionsbrett();
        spielfeld.darstellen(ib);

    }
}

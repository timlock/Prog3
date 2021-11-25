package de.hsos.prog3.ab4.test;

import de.hsos.prog3.ab4.Quadrat;
import de.hsos.prog3.ab4.Spieler;
import de.hsos.prog3.ab4.Spielfeld;
import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class SpielerTest {
    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld();
        Interaktionsbrett ib = new Interaktionsbrett();
        Spieler spielerLinks = new Spieler(spielfeld,spielfeld.getSpielflaeche().links() + spielfeld.getMargin() , spielfeld.getSpielflaeche().mitteInY());
        Spieler spielerRechts = new Spieler(spielfeld, spielfeld.getSpielflaeche().rechts() - (spielfeld.getMargin() + spielerLinks.getSchlaeger().breite()), spielfeld.getSpielflaeche().mitteInY());
        Quadrat spiellaeche = spielfeld.getSpielflaeche();
        ib.neuesRechteck(spiellaeche.links(),spiellaeche.oben(),spiellaeche.breite(),spiellaeche.hoehe());
        ib.neueLinie(spiellaeche.mitteInX(),spiellaeche.oben(),spiellaeche.mitteInX(), spiellaeche.unten());
        zeichneSpieler(ib,spielerLinks);
        zeichneSpieler(ib,spielerRechts);

    }
    public static void zeichneSpieler(Interaktionsbrett ib ,Spieler spieler){
        ib.neuesRechteck(spieler.getSchlaeger().links(),spieler.getSchlaeger().oben(),spieler.getSchlaeger().breite(),spieler.getSchlaeger().hoehe());
    }
}

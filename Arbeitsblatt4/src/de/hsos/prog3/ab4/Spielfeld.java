package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class Spielfeld {
    private Quadrat spiellaeche;
    private Interaktionsbrett ib;
    private int margin = 20;
    private int breite = 100;
    public Spielfeld(){
        spiellaeche = new Quadrat(margin ,margin ,margin + 700,margin + 800);

    }
    public void darstellen(Interaktionsbrett ib){
        this.ib = ib;
        ib.neuesRechteck(spiellaeche.links(),spiellaeche.oben(),spiellaeche.breite(),spiellaeche.hoehe());
        ib.neueLinie(spiellaeche.mitteInX(),spiellaeche.oben(),spiellaeche.mitteInX(), spiellaeche.unten());

    }

}

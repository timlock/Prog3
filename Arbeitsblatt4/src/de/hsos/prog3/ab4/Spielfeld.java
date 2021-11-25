package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class Spielfeld {
    private Quadrat spielflaeche;
    private Interaktionsbrett ib;
    private int margin = 20;
    private int breite = 100;
    public Spielfeld(){
        spielflaeche = new Quadrat(margin ,margin ,500 ,850);

    }
    public void darstellen(Interaktionsbrett ib){
        this.ib = ib;
        ib.neuesRechteck(spielflaeche.links(), spielflaeche.oben(), spielflaeche.breite(), spielflaeche.hoehe());
        ib.neueLinie(spielflaeche.mitteInX(), spielflaeche.oben(), spielflaeche.mitteInX(), spielflaeche.unten());


    }

    public int getMargin() {
        return margin;
    }

    public Quadrat getSpielflaeche() {
        return spielflaeche;
    }
}

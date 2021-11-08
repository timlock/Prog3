package de.hsos.prog3.ab02.ui;

import de.hsos.prog3.ab02.util.EinUndAusgabe;

public class NutzerEingabe {
    private EinUndAusgabe io;

    public NutzerEingabe(EinUndAusgabe io){
        this.io = io;
    }
    public int anzahlZeilenDesSpielfelds(){

        return 1;
    }
    public int wahrscheinlichkeitDerBesiedlung(){

        return 1;
    }
    public int anzahlDerSimulationsschritte(){

        return 1;
    }
}

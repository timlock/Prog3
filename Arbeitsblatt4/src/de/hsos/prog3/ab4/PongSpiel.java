package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class PongSpiel {
    private Spieler spielerLinks;
    private Spieler spielerRechts;
    private Spielfeld spielfeld;
    private Interaktionsbrett ib;
    private String zustand;
    private String eingaeLinks;
    private String eingaeRechts;
    private final int FPMS = 17;

    public PongSpiel(){
        ib = new Interaktionsbrett();
        ib.willTasteninfo(this);
        startAufstellung();
    }
    private void startAufstellung(){
        spielfeld = new Spielfeld();
        Quadrat quadratFeld = spielfeld.getSpiellaeche();
        spielerLinks = new Spieler(spielfeld, quadratFeld.links() + spielfeld.getMargin() , quadratFeld.mitteInY());
        spielerRechts = new Spieler(spielfeld, quadratFeld.rechts() - (spielfeld.getMargin() + spielerLinks.getSchlaeger().breite()), quadratFeld.mitteInY());
    }

    public void spielen() throws InterruptedException {
        long start;
        long differenz;
        while (true){
            start = System.currentTimeMillis();
            ib.abwischen();
            resetEingabe();
            System.out.println(eingaeLinks);
            spielfeld.darstellen(ib);
            zeichneSpieler(spielerLinks);
            zeichneSpieler(spielerRechts);
            differenz = System.currentTimeMillis() - start;
            if(differenz < FPMS)Thread.sleep(FPMS-differenz);
        }
    }

    public void zeichneSpieler(Spieler spieler){
        ib.neuesRechteck(spieler.getSchlaeger().links(),spieler.getSchlaeger().oben(),spieler.getSchlaeger().breite(),spieler.getSchlaeger().hoehe());
    }
    private void resetEingabe(){
        eingaeLinks = "";
        eingaeRechts = "";
    }


    public void tasteGedrueckt(String s) throws InterruptedException {
        System.out.println(s);
        if(s.equals("a")) spielerLinks.aufwaerts();
        if(s.equals("y")) spielerLinks.abwaerts();
        if(s.equals("Oben")) spielerRechts.abwaerts();
        if(s.equals("Unten")) spielerRechts.abwaerts();
       if(s.equals("s"))spielen();
        if(s.equals("e")) System.exit(0);
    }

    public void erhoehePunkte(){

    }

    public void setzePunkteZurueck(){

    }
}

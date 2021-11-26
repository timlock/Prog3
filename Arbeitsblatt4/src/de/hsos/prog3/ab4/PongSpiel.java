package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class PongSpiel {
    private Spieler spielerLinks;
    private Spieler spielerRechts;
    private Spielfeld spielfeld;
    private Ball ball;
    private Interaktionsbrett ib;
    private KollisionsDetektor detektor;
    private String punkte = "0  :  0";
    private boolean zustand = false;
    private final int FPMS = 17;

    public PongSpiel(){
        ib = new Interaktionsbrett();
        ib.willTasteninfo(this);
        startAufstellung();
    }
    private void startAufstellung(){
        spielfeld = new Spielfeld();
        Quadrat quadratFeld = spielfeld.getSpielflaeche();
        spielerLinks = new Spieler(spielfeld, quadratFeld.links() + spielfeld.getMargin() , quadratFeld.mitteInY());
        spielerRechts = new Spieler(spielfeld, quadratFeld.rechts() - (spielfeld.getMargin() + spielerLinks.getSchlaeger().breite()), quadratFeld.mitteInY());
        ball = new Ball(quadratFeld.links() + quadratFeld.mitteInX()*2 /3, 400 , quadratFeld.breite() /100, -4,-1,spielfeld.getSpielflaeche().breite());
        detektor = new KollisionsDetektor(spielfeld,spielerLinks, spielerRechts);
    }

    public void spielen() throws InterruptedException {
        long start;
        long differenz = 17;
       // while(!zustand){}
        while (true){
            start = System.currentTimeMillis();
            ib.abwischen();
            spielfeld.darstellen(ib);
            ib.neuerText(spielfeld.getSpielflaeche().mitteInX() - 14, 10, punkte);
            zeichneSpieler(spielerLinks);
            zeichneSpieler(spielerRechts);
            ball.darstellen(ib);
            detektor.checkBeruehrungBallSpielfeldGrenzen(ball);
            detektor.checkBeruehrungBallMitSchlaeger(ball);
            switch (detektor.checkAusserhalbDesSpielfeldes(ball)){
                case DRAUSSEN_LINKS: {
                    erhoehePunkte(false);
                    ball.aufschlag(true);
                }
                break;
                case DRAUSSEN_RECHTS: {
                    erhoehePunkte(true);
                    ball.aufschlag(false);
                }
                break;
            }
            ball.bewegen((int)differenz / FPMS);
            differenz = (System.currentTimeMillis() - start);
            if(differenz < FPMS)Thread.sleep(FPMS-differenz);
        }
    }

    public void zeichneSpieler(Spieler spieler){
        ib.neuesRechteck(spieler.getSchlaeger().links(),spieler.getSchlaeger().oben(),spieler.getSchlaeger().breite(),spieler.getSchlaeger().hoehe());
    }


    public void tasteGedrueckt(String s) throws InterruptedException {
       // System.out.println(s);
        if(s.equals("a")) spielerLinks.aufwaerts();
        if(s.equals("y")) spielerLinks.abwaerts();
        if(s.equals("Oben")) spielerRechts.aufwaerts();
        if(s.equals("Unten")) spielerRechts.abwaerts();
       if(s.equals("s")) zustand = true;
        if(s.equals("e")) System.exit(0);
    }

    public void erhoehePunkte(boolean links){
        if(links) {
            spielerLinks.setPunkte(spielerLinks.getPunkte() + 1);
            StringBuilder builder = new StringBuilder(punkte);
            builder.setCharAt(0,spielerLinks.getPunkte());
            punkte = builder.toString();
        }
        else{
            spielerRechts.setPunkte(spielerLinks.getPunkte()+1);
            StringBuilder builder = new StringBuilder(punkte);
            builder.setCharAt(punkte.length()-1,(char)spielerRechts.getPunkte());
            punkte = builder.toString();
        }
    }

    public void setzePunkteZurueck(){
        spielerLinks.setPunkte(0);
        spielerRechts.setPunkte(0);
        punkte = "0  :  0";
    }
}

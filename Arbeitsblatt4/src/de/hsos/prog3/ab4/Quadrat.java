package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class Quadrat {
    private int x;
    private int y;
    private int hoehe;
    private int breite;

    public Quadrat(int x, int y, int hoehe, int breite) {
        this.x = x;
        this.y = y;
        this.hoehe = hoehe;
        this.breite = breite;


    }

    public void darstellenRahmen(Interaktionsbrett ib) {
        ib.neuesRechteck(x, y, breite, hoehe);
    }

    public void darstellenFuellung(Interaktionsbrett ib) {
//        for (int i = 0; i < seitenlaenge; i++) {
//            ib.neueLinie(x+i,y,x+i,y+seitenlaenge);
//            ib.neueLinie(x,y+i,x+seitenlaenge,y+i);
//        }
        ib.neueLinie(x, y, x + breite, y + hoehe);
        ib.neueLinie(x, y + hoehe, x + breite, y);
    }

    public int oben() {
        return y;
    }

    public int unten() {
        return y + hoehe;
    }

    public int links() {
        return x;
    }
    public int rechts() {
        return x+ breite;
    }
    public int breite() {
        return breite;
    }
    public int hoehe(){
        return hoehe;
    }
    public int mitteInY(){
        return (y + hoehe) /2;
    }
    public int mitteInX(){
        return (x + breite) /2;
    }
    public void verschiebe(int dx, int dy){
        x += dx;
        y += dy;
    }
    public void verschiebeNach(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean ueberschneidet(Quadrat o){
        if (this.oben() < o.unten()
                || this.unten() > o.oben()) {
            return false;
        }
        if (this.rechts() < o.links()
                || this.links() > o.rechts()) {
            return false;
        }
        return true;
    }

}

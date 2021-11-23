package de.hsos.prog3.ab4;

public class Spieler {
    private Spielfeld spielfeld;
    private Quadrat schlaeger;
    private int punkte;


    public Spieler(Spielfeld spielfeld, int x, int y){
        this.spielfeld = spielfeld;
        schlaeger = new Quadrat(x,y,spielfeld.getSpiellaeche().hoehe() / 10, spielfeld.getSpiellaeche().breite()/100);

    }

    public Quadrat getSchlaeger() {
        return schlaeger;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public void aufwaerts() {
        if(schlaeger.oben() + 5 >= spielfeld.getSpiellaeche().oben()) return;
        schlaeger.verschiebe(0,2);
    }
    public void abwaerts(){
       if(schlaeger.unten() + 5 >= spielfeld.getSpiellaeche().unten()) return;
        schlaeger.verschiebe(0,-2);
    }

}

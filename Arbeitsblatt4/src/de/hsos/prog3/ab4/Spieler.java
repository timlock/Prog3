package de.hsos.prog3.ab4;

public class Spieler {
    private Spielfeld spielfeld;
    private Quadrat schlaeger;
    private int geschwindigkeit;
    private int punkte;


    public Spieler(Spielfeld spielfeld, int x, int y){
        this.spielfeld = spielfeld;
        schlaeger = new Quadrat(x,y,spielfeld.getSpielflaeche().hoehe() / 10, spielfeld.getSpielflaeche().breite()/100);
        geschwindigkeit = schlaeger.hoehe()/2;
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
        if(schlaeger.oben() - 5 <= spielfeld.getSpielflaeche().oben()) return;
        schlaeger.verschiebe(0,-geschwindigkeit);
    }
    public void abwaerts(){
       if(schlaeger.unten() + 5 >= spielfeld.getSpielflaeche().unten()) return;
        schlaeger.verschiebe(0,geschwindigkeit);
    }

}

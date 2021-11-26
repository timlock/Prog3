package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class Ball {
    private Quadrat form;
    private int hMitte;
    private int vorSchlaeger;
    private int feldBreite;
    private int bewegungInXProFrame;
    private int bewegungInYProFrame;

    public Ball(int x, int y,int breite, int bewegungInXProFrame, int bewegungInYProFrame, int feldBreite){
        form = new Quadrat(x,y,breite,breite);
        hMitte = y;
        vorSchlaeger = x;
        this.feldBreite =feldBreite;
        this.bewegungInXProFrame = bewegungInXProFrame;
        this.bewegungInYProFrame = bewegungInYProFrame;
    }

    public Quadrat getForm() {
        return form;
    }

    public void bewegen(int anzahlFrames){
        anzahlFrames = anzahlFrames == 0 ? 1:anzahlFrames;
        for (int i = 1; i <= anzahlFrames; i++) {
            form.setX(form.getX()+bewegungInXProFrame);
            form.setY(form.getY()+bewegungInYProFrame);

        }

    }

    public void umkehrenDerBewegungInX(){
        bewegungInXProFrame *= -1;

    }

    public int getBewegungInXProFrame() {
        return bewegungInXProFrame;
    }

    public int getBewegungInYProFrame() {
        return bewegungInYProFrame;
    }

    public void umkehrenDerBewegungInY(){
        bewegungInYProFrame *= -1;
    }
    public void darstellen(Interaktionsbrett ib){
        ib.neuesRechteck(form.links(),form.oben(), form.breite(), form.hoehe());

    }
    public void aufschlag(boolean links){
        if(links) form.setX(vorSchlaeger);
        else form.setX(feldBreite - vorSchlaeger);
        form.setY(hMitte);

    }
}

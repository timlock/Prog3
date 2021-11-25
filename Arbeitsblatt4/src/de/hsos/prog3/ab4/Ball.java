package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.pong.util.Interaktionsbrett;

public class Ball {
    private Quadrat form;
    private int bewegungInXProFrame;
    private int bewegungInYProFrame;

    public Ball(int x, int y,int breite, int bewegungInXProFrame, int bewegungInYProFrame){
        form = new Quadrat(x,y,breite,breite);
        this.bewegungInXProFrame = bewegungInXProFrame;
        this.bewegungInYProFrame = bewegungInYProFrame;
    }

    public void bewegen(int anzahlFrames){
        anzahlFrames = anzahlFrames == 0 ? 1:anzahlFrames;
        for (int i = 1; i <= anzahlFrames; i++) {
            form.setX(form.getX()+bewegungInXProFrame);
            form.setY(form.getY()+bewegungInXProFrame);

        }

    }

    public void umkehrenDerBewegungInX(){
        bewegungInXProFrame += -1;

    }
    public void umkehrenDerBewegungInY(){
        bewegungInYProFrame += -1;
    }
    public void darstellen(Interaktionsbrett ib){
        ib.neuesRechteck(form.links(),form.oben(), form.breite(), form.hoehe());

    }
}

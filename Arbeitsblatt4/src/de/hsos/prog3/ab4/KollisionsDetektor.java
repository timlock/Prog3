package de.hsos.prog3.ab4;

import de.hsos.prog3.ab4.Ball;
import de.hsos.prog3.ab4.BallPosition;
import de.hsos.prog3.ab4.Spieler;
import de.hsos.prog3.ab4.Spielfeld;

public class KollisionsDetektor {
    private Spielfeld spielfeld;
    private Spieler spielerLinks;
    private Spieler spielerRechts;

    public KollisionsDetektor(Spielfeld spielfeld, Spieler spielerLinks, Spieler spielerRechts) {
        this.spielfeld = spielfeld;
        this.spielerLinks = spielerLinks;
        this.spielerRechts = spielerRechts;
    }

    public void checkBeruehrungBallSpielfeldGrenzen(Ball ball) {
        if (ball.getForm().oben() - 5 <= spielfeld.getSpielflaeche().oben()
                || ball.getForm().unten() + 5 >= spielfeld.getSpielflaeche().unten())
            ball.umkehrenDerBewegungInY();
    }

    public void checkBeruehrungBallMitSchlaeger(Ball ball) {
        if (ball.getForm().ueberschneidet(spielerLinks.getSchlaeger())
                || ball.getForm().ueberschneidet(spielerRechts.getSchlaeger())) {
            double random = Math.random();
            if (random > 0.5)
                ball.umkehrenDerBewegungInX();
            else {
                ball.umkehrenDerBewegungInY();
                ball.umkehrenDerBewegungInX();
            }
        }
    }

    public BallPosition checkAusserhalbDesSpielfeldes(Ball ball) {
        if (ball.getForm().links() <= spielfeld.getSpielflaeche().links())
            return BallPosition.DRAUSSEN_LINKS;
        if (ball.getForm().rechts() >= spielfeld.getSpielflaeche().rechts())
            return BallPosition.DRAUSSEN_RECHTS;
        return BallPosition.DRINNEN;
    }
}

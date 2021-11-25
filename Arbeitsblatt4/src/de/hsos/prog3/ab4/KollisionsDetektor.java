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
        ball.umkehrenDerBewegungInY();
    }

    public void checkBeruehrungBallMitSchlaeger(Ball ball) {
        ball.umkehrenDerBewegungInY();

        ball.umkehrenDerBewegungInY();
        ball.umkehrenDerBewegungInX();
    }

    public BallPosition checkAusserhalbDesSpielfeldes(Ball ball) {
        return BallPosition.DRINNEN;
    }
}

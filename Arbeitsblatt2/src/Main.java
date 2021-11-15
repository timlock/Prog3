import de.hsos.prog3.ab02.ui.Steuerung;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Steuerung steuerung = new Steuerung();
        steuerung.initialisierung();
        steuerung.startDesSpiels();
    }
}

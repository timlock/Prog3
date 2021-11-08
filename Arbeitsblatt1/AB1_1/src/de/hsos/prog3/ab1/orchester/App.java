package de.hsos.prog3.ab1.orchester;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

import de.hsos.prog3.ab1.audio.StdAudioPlayer;
import de.hsos.prog3.audio.SimpleAudioPlayer;
import de.hsos.prog3.ab1.audio.adapter.SimpleAudioPlayerAdapter;


public class App {

    public static void main(String[] args) throws IOException {
        aufgabe1_1();
        aufgabe1_2();
        aufgabe1_3();
        aufgabe1_4();
    }


    public static void aufgabe1_1(){
        HashSet set = new HashSet();
        for (int i = 0; i < 4; i++) {
            Scanner eingabe = new Scanner(System.in);
            String vorname = eingabe.nextLine();
            String nachname = eingabe.nextLine();
            Nachbar nachbar = new Nachbar(vorname,nachname);
            set.add(nachbar);
        }
        Iterator<Nachbar> hashIterator = set.iterator();
        while (hashIterator.hasNext()){
            System.out.println("Hallo " + hashIterator.next());
        }
    }
    public static void aufgabe1_2()throws IOException {
        URL url = App.class.getResource("/Baritone.wav");
        SimpleAudioPlayer player = new SimpleAudioPlayer(url);
        player.setDebug(false);
        player.verboseLogging(true);
        player.play(0);

    }
    public static void aufgabe1_3()throws IOException {
        URL url = App.class.getResource("/Drum.wav");
        StdAudioPlayer player = new SimpleAudioPlayerAdapter();
        player.einmaligAbspielen(url);

    }
    public static void aufgabe1_4() throws IOException {
        String audioDatei = "/All_Together.wav";
        Orchester orchester = new Orchester("HSOS TITTY TWISTER ORCHESTRA", audioDatei);

        DirigentIn karajan = new DirigentIn("Karajan");
        orchester.addDirigentIn(karajan);

        MusikerIn barritone = new MusikerIn("Dirk Die Lunge Mueller", Instrument.BARITONE);
        MusikerIn akkordion = new MusikerIn("Akki Taste", Instrument.AKKORDION);
        MusikerIn drum = new MusikerIn("Das Biest", Instrument.SCHLAGZEUG);
        orchester.addMusikerIn(barritone);
        orchester.addMusikerIn(akkordion);
        orchester.addMusikerIn(drum);

        orchester.proben();
        orchester.auftreten();
        orchester.spielen();

    }

}

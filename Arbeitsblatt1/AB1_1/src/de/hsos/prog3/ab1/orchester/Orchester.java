package de.hsos.prog3.ab1.orchester;

import de.hsos.prog3.ab1.audio.audio.StdAudioPlayer;
import de.hsos.prog3.ab1.audio.audio.adapter.SimpleAudioPlayerAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public class Orchester {
    private String bezeichnung;
    private String audioDateiKonzert;

    private DirigentIn dirigentIn;
    private  HashSet<MusikerIn> musikerInnen;
    private Verhalten verhalten;

    public Orchester(String bezeichnung, String audioDateiKonzert){
        this.bezeichnung = bezeichnung;
        this.audioDateiKonzert = audioDateiKonzert;
        musikerInnen = new HashSet<MusikerIn>();
    }
    public void addDirigentIn(DirigentIn dirigentIn) {
        this.dirigentIn = dirigentIn;
    }
    public void addMusikerIn(MusikerIn musikerIn){
        musikerInnen.add(musikerIn);
    }
    public HashSet<MusikerIn> getMusikerinnen() {
        return musikerInnen;
    }
    public void proben() throws IOException {
        verhalten = new Probe();
        verhalten.spielen(this);
    }
    public void auftreten() {
        verhalten = new Konzert();
        verhalten.spielen(this);

    }
    public void spielen() {
        verhalten.spielen(this);
    }
    public URL getAudioDateiKonzert() throws MalformedURLException {
        return App.class.getResource(audioDateiKonzert);
    }

    private class Probe implements Verhalten{

        @Override
        public void spielen(Orchester orchester) {
            HashSet musikerInnen = orchester.getMusikerinnen();
            Iterator<MusikerIn> hashIterator = musikerInnen.iterator();
            StdAudioPlayer player= new SimpleAudioPlayerAdapter();
            try {
                while (hashIterator.hasNext()) {
                    String audioDatei = hashIterator.next().getInstrument().getAudio();
                    URL adresse = App.class.getResource(audioDatei);
                    player.einmaligAbspielen(adresse);
                }
            }catch (IOException e){
                System.out.println("Probe wird abgegbrochen");
            }
        }

    }
    private class Konzert implements Verhalten{
        @Override
        public void spielen(Orchester orchester) {
            try {
                URL konzertAudioDatei = orchester.getAudioDateiKonzert();
                SimpleAudioPlayerAdapter playerAdapter = new SimpleAudioPlayerAdapter();
                playerAdapter.einmaligAbspielen(konzertAudioDatei);

            }catch (IOException e){
                System.out.println("Auftritt wird abgebrochen");
            }
        }
    }

}

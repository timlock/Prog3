package de.hsos.prog3.ab1.audio.audio.adapter;

import de.hsos.prog3.ab1.audio.audio.StdAudioPlayer;
import de.hsos.prog3.audio.SimpleAudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class SimpleAudioPlayerAdapter implements StdAudioPlayer {
    SimpleAudioPlayer player;

    public SimpleAudioPlayerAdapter() { }
    @Override
    public void einmaligAbspielen(URL url) throws IOException {
        wiederholtAbspielen(url,0);
    }

    @Override
    public void wiederholtAbspielen(URL url, int wiederholungen) throws IOException {
        player = new SimpleAudioPlayer(url);
        System.out.println("Ton aus? J/N");
        Scanner eingabe = new Scanner(System.in);
        if(eingabe.nextLine().equals("j"))
            tonAus();
        else
            tonAn();
        player.play(wiederholungen);

    }

    @Override
    public void tonAus() {
        player.setDebug(true);
        player.verboseLogging(true);
    }

    @Override
    public void tonAn() {
        player.setDebug(false);
        player.verboseLogging(true);
    }
}

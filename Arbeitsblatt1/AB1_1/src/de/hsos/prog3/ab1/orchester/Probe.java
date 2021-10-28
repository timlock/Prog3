package de.hsos.prog3.ab1.orchester;

import de.hsos.prog3.ab1.audio.audio.StdAudioPlayer;
import de.hsos.prog3.ab1.audio.audio.adapter.SimpleAudioPlayerAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

//public class Probe implements Verhalten{
//
//    @Override
//    public void spielen(Orchester orchester) {
//        HashSet musikerInnen = orchester.getMusikerinnen();
//        Iterator<MusikerIn> hashIterator = musikerInnen.iterator();
//        StdAudioPlayer player= new SimpleAudioPlayerAdapter();
//
//        try {
//            while (hashIterator.hasNext()) {
//                String audioDatei = hashIterator.next().getInstrument().getAudio();
//                URL adresse = App.class.getResource(audioDatei);
//                player.einmaligAbspielen(adresse);
//            }
//        }catch (IOException e){
//            System.out.println("Probe wird abgegbrochen");
//        }
//    }
//
//}

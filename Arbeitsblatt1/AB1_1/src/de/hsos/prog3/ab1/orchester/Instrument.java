package de.hsos.prog3.ab1.orchester;

public enum Instrument {
    BARITONE("/Baritone.wav"),AKKORDION("/Accordion.wav"),SCHLAGZEUG("/Drum.wav");

    private String audiodatei;

    Instrument(String audiodatei){
        this.audiodatei = audiodatei;
    }
    public String getAudio(){
        return audiodatei;
    }
}

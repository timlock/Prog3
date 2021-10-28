package de.hsos.prog3.ab1.orchester;

public class MusikerIn extends Mitglied{
    private Instrument instrument;
    MusikerIn(String name, Instrument instrument ){
        super(name);
        this.instrument = instrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}

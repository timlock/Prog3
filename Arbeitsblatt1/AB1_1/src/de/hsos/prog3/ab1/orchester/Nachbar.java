package de.hsos.prog3.ab1.orchester;

public class Nachbar {
    String vorname;
    String nachname;

    public Nachbar(String vorname,String nachname){
        this.vorname = vorname;
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return  vorname + " " + nachname;
    }
}

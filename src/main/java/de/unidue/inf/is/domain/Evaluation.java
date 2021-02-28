package de.unidue.inf.is.domain;

public class Evaluation {
    private int bNummer;
    private int aID;
    private int note;
    private String kommentar;

    public Evaluation(int bNummer, int aID, int note, String kommentar) {
        this.bNummer = bNummer;
        this.aID = aID;
        this.note = note;
        this.kommentar = kommentar;
    }

    public int getbNummer() {
        return bNummer;
    }

    public void setbNummer(int bNummer) {
        this.bNummer = bNummer;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}

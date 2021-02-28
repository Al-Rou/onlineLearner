package de.unidue.inf.is.domain;

public class DeliveryToShow {
    private int aID;
    private String abgabeText;
    private int kID;
    private int aNummer;

    public int getaNummer() {
        return aNummer;
    }

    public void setaNummer(int aNummer) {
        this.aNummer = aNummer;
    }

    public DeliveryToShow(int aID, String abgabeText, int kID, int aNummer) {
        this.aID = aID;
        this.abgabeText = abgabeText;
        this.kID = kID;
        this.aNummer = aNummer;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public String getAbgabeText() {
        return abgabeText;
    }

    public void setAbgabeText(String abgabeText) {
        this.abgabeText = abgabeText;
    }
}


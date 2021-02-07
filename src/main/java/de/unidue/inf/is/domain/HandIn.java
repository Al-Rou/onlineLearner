package de.unidue.inf.is.domain;

public class HandIn {
    private int bNummer;
    private String abgabeText;
    private String aName;
    private int kID;

    public HandIn(int bNummer, String abgabeText, String aName, int kID) {
        this.bNummer = bNummer;
        this.abgabeText = abgabeText;
        this.aName = aName;
        this.kID = kID;
    }

    public int getbNummer() {
        return bNummer;
    }

    public void setbNummer(int bNummer) {
        this.bNummer = bNummer;
    }

    public String getAbgabeText() {
        return abgabeText;
    }

    public void setAbgabeText(String aID) {
        this.abgabeText = aID;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aNummer) {
        this.aName = aNummer;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }
}

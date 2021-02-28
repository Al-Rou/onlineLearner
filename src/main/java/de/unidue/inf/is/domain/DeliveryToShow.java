package de.unidue.inf.is.domain;

public class DeliveryToShow {
    private int aID;
    private String abgabeText;
    private int kID;

    public DeliveryToShow(int aID, String abgabeText, int kID) {
        this.aID = aID;
        this.abgabeText = abgabeText;
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


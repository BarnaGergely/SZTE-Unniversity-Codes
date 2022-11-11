package Egyseg;

import Egyseg.Kepesseg.Kepesseg;

public class Egyseg {
    // TODO String frakcio;

    String nev;
    int ar;
    int sebzesMin;
    int sebzesMax;
    int eletero;
    int sebesseg;
    int kezdemenyezes;
    Kepesseg kepesseg;
    int mennyiseg;

    public Egyseg(String nev, int ar, int sebzesMin, int sebzesMax, int eletero, int sebesseg, int kezdemenyezes, Kepesseg kepesseg) {
        this.nev = nev;
        this.ar = ar;
        this.sebzesMin = sebzesMin;
        this.sebzesMax = sebzesMax;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.kepesseg = kepesseg;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public int getSebzesMin() {
        return sebzesMin;
    }

    public void setSebzesMin(int sebzesMin) {
        this.sebzesMin = sebzesMin;
    }

    public int getSebzesMax() {
        return sebzesMax;
    }

    public void setSebzesMax(int sebzesMax) {
        this.sebzesMax = sebzesMax;
    }

    public int getEletero() {
        return eletero;
    }

    public void setEletero(int eletero) {
        this.eletero = eletero;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public void setKezdemenyezes(int kezdemenyezes) {
        this.kezdemenyezes = kezdemenyezes;
    }

    public Kepesseg getKepesseg() {
        return kepesseg;
    }

    public String getKepessegNev(){
        return kepesseg.getName();
    }

    public void setKepesseg(Kepesseg kepesseg) {
        this.kepesseg = kepesseg;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public void setSebesseg(int sebesseg) {
        this.sebesseg = sebesseg;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
}

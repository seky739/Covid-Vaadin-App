package cz.seky.backend.objects;

public class Infected {

    String datum;
    int pocetDen;
    int pocetCelkem;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getPocetDen() {
        return pocetDen;
    }

    public void setPocetDen(int pocetDen) {
        this.pocetDen = pocetDen;
    }

    public int getPocetCelkem() {
        return pocetCelkem;
    }

    public void setPocetCelkem(int pocetCelkem) {
        this.pocetCelkem = pocetCelkem;
    }

    @Override
    public String toString() {
        return "Infected{" +
                "datum='" + datum + '\'' +
                ", pocetDen=" + pocetDen +
                ", pocetCelkem=" + pocetCelkem +
                '}';
    }
}

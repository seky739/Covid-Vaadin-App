package cz.seky.backend.objects;

import com.google.gson.annotations.SerializedName;

public class Tested {

    String datum;
    @SerializedName("testy-den")
    int testDay;
    @SerializedName("testy-celkem")
    int testFull;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getTestDay() {
        return testDay;
    }

    public void setTestDay(int testDay) {
        this.testDay = testDay;
    }

    public int getTestFull() {
        return testFull;
    }

    public void setTestFull(int testFull) {
        this.testFull = testFull;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + datum + '\'' +
                ", testDay='" + testDay + '\'' +
                ", testFull='" + testFull + '\'' +
                '}';
    }
}

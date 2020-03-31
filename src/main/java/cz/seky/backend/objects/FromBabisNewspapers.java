package cz.seky.backend.objects;
/**
 *   "fromBabisNewspapers": {
 *  *     "totalInfected": 2716,
 *  *     "totalDeaths": 13,
 *  *     "totalCured": 11,
 *  *     "totalTested": 40700
 *  *     */
public class FromBabisNewspapers {

    String totalInfected,totalDeaths,totalCured,totalTested;

    public String getTotalInfected() {
        return totalInfected;
    }

    public void setTotalInfected(String totalInfected) {
        this.totalInfected = totalInfected;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(String totalCured) {
        this.totalCured = totalCured;
    }

    public String getTotalTested() {
        return totalTested;
    }

    public void setTotalTested(String totalTested) {
        this.totalTested = totalTested;
    }
}

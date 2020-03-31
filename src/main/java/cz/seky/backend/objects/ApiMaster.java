package cz.seky.backend.objects;



/**
 *  "totalTested": 48811,
 *   "infected": 3002,
 *   "recovered": 25,
 *   "deceased": 24,
 *   "totalPositiveTests": [
 *     {
 *
 *     numberOfTestedGraph": [
 *     {
 *       "value": 20,
 *       "date": "2020-01-27T00:00:00.000Z"
 *     },
 *
 *    "infectedByRegion": [
 *     {
 *       "name": "Hlavní město Praha",
 *       "value": 816
 *     },
 *
 *     "infectedDaily": [
 *      {
 *       "value": 0,
 *       "date": "2020-01-27T00:00:00.000Z"
 *     },
 *
 *     "infectedByAgeSex": [
 *     {
 *       "sex": "muž",
 *       "infectedByAge": [
 *         {
 *           "age": "0–14",
 *           "value": 81
 *         },
 *
 *
 *     "lastUpdatedAtSource": "2020-03-31T07:30:00.000Z",
 *   "lastUpdatedAtApify": "2020-03-31T10:15:00.000Z",
 *   "readMe": "https://apify.com/petrpatek/covid-cz",
 *   "fromBabisNewspapers": {
 *     "totalInfected": 2716,
 *     "totalDeaths": 13,
 *     "totalCured": 11,
 *     "totalTested": 40700
 *
 *     */


public class ApiMaster {
    String totalTested,infected,recovered,deceased,lastUpdatedAtSource,lastUpdatedAtApify;
    ApiValue[] totalPositiveTests;
    ApiValue[] numberOfTestedGraph;
    ApiRegion[] infectedByRegion;
    ApiValue[] infectedDaily;
    ApiSex[] infectedByAgeSex;
    FromBabisNewspapers fromBabisNewspapers;

    public FromBabisNewspapers getFromBabisNewspapers() {
        return fromBabisNewspapers;
    }

    public void setFromBabisNewspapers(FromBabisNewspapers fromBabisNewspapers) {
        this.fromBabisNewspapers = fromBabisNewspapers;
    }

    public String getTotalTested() {
        return totalTested;
    }

    public void setTotalTested(String totalTested) {
        this.totalTested = totalTested;
    }

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getLastUpdatedAtSource() {
        return lastUpdatedAtSource;
    }

    public void setLastUpdatedAtSource(String lastUpdatedAtSource) {
        this.lastUpdatedAtSource = lastUpdatedAtSource;
    }

    public String getLastUpdatedAtApify() {
        return lastUpdatedAtApify;
    }

    public void setLastUpdatedAtApify(String lastUpdatedAtApify) {
        this.lastUpdatedAtApify = lastUpdatedAtApify;
    }

    public ApiValue[] getTotalPositiveTests() {
        return totalPositiveTests;
    }

    public void setTotalPositiveTests(ApiValue[] totalPositiveTests) {
        this.totalPositiveTests = totalPositiveTests;
    }

    public ApiValue[] getNumberOfTestedGraph() {
        return numberOfTestedGraph;
    }

    public void setNumberOfTestedGraph(ApiValue[] numberOfTestedGraph) {
        this.numberOfTestedGraph = numberOfTestedGraph;
    }

    public ApiRegion[] getInfectedByRegion() {
        return infectedByRegion;
    }

    public void setInfectedByRegion(ApiRegion[] infectedByRegion) {
        this.infectedByRegion = infectedByRegion;
    }

    public ApiValue[] getInfectedDaily() {
        return infectedDaily;
    }

    public void setInfectedDaily(ApiValue[] infectedDaily) {
        this.infectedDaily = infectedDaily;
    }

    public ApiSex[] getInfectedByAgeSex() {
        return infectedByAgeSex;
    }

    public void setInfectedByAgeSex(ApiSex[] infectedByAgeSex) {
        this.infectedByAgeSex = infectedByAgeSex;
    }
}

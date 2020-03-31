package cz.seky.backend.objects;

/**
 * "infectedByAgeSex": [
 * {
 * "sex": "muž",
 * "infectedByAge": [
 * {
 * "age": "0–14",
 * "value": 81
 * },
 */
public class ApiSex {
    String sex;
    ApiAge[] infectedByAge;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ApiAge[] getInfectedByAge() {
        return infectedByAge;
    }

    public void setInfectedByAge(ApiAge[] infectedByAge) {
        this.infectedByAge = infectedByAge;
    }
}

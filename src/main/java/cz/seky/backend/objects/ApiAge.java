package cz.seky.backend.objects;
/**
 *
 * "infectedByAgeSex": [
 *     {
 *       "sex": "muž",
 *       "infectedByAge": [
 *         {
 *           "age": "0–14",
 *           "value": 81
 *         },
 * */
public class ApiAge {
    String age,value;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

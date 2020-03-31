package cz.seky.backend.objects;
/**
 * "infectedByRegion": [
 *     {
 *       "name": "Hlavní město Praha",
 *       "value": 816
 *     },
 * */
public class ApiRegion {
    String name,value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

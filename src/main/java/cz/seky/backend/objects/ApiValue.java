package cz.seky.backend.objects;


/**
 * "value": 0,
 *       "date": "2020-01-27T00:00:00.000Z"*/
public class ApiValue {
    String value,date;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

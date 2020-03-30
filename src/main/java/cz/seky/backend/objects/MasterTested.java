package cz.seky.backend.objects;

import java.util.Arrays;

public class MasterTested {

    String modified,source;
    Tested[] data;

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Tested[] getData() {
        return data;
    }

    public void setData(Tested[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HeadRecord{" +
                "modified='" + modified + '\'' +
                ", source='" + source + '\'' +
                ", recordList=" + Arrays.toString(data) +
                '}';
    }
}

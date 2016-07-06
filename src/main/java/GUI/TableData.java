package GUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Shonen on 05.07.2016.
 */
public class TableData {
    private final SimpleStringProperty name;
    private final SimpleStringProperty desc;

    public TableData(String name, String desc) {
        this.name = new SimpleStringProperty(name);
        this.desc = new SimpleStringProperty(desc);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public String getDesc() {
        return desc.get();
    }
    public void setDesc(String desc) {
        this.desc.set(desc);
    }


}

package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Functions {
    SimpleIntegerProperty id;
    SimpleStringProperty function_name;

    public Functions(SimpleIntegerProperty id, SimpleStringProperty function_name) {
        this.id = id;
        this.function_name = function_name;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFunction_name() {
        return function_name.get();
    }

    public SimpleStringProperty function_nameProperty() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name.set(function_name);
    }
}

package ba.unsa.etf.rpr.tutorijal08;

import javafx.beans.property.SimpleStringProperty;

public class Controller {
    private SimpleStringProperty trazenaRijec;

    public Controller() {
        trazenaRijec = new SimpleStringProperty("");
    }

    public SimpleStringProperty trazenaRijecProperty() {
        return trazenaRijec;
    }

    public String getTrazenaRijec() {
        return trazenaRijec.get();
    }
}

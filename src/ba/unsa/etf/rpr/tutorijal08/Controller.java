package ba.unsa.etf.rpr.tutorijal08;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


public class Controller {
    public TextField trazenaRijec;

    public void trazi(ActionEvent actionEvent) {
        System.out.println(trazenaRijec.getText());
    }

}

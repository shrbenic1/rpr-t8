package ba.unsa.etf.rpr.tutorijal08;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;

public class Controller {
    public TextField trazenaRijec;
    public ListView<String> lista;
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    public void walk (String pocetak, String rijec){
        File root = new File(pocetak);
        File[] list = root.listFiles();
        if (list == null) {
            return;
        }
        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath(), rijec);
            } else {
                if(f.getAbsoluteFile().toString().contains(rijec)) {
                    observableList.add(f.getAbsoluteFile().toString());
                    lista.setItems(observableList);
                    System.out.println("File:" + f.getAbsoluteFile());
                }
            }
        }
    }

    public void trazi(ActionEvent actionEvent) {
        walk("C:\\Users", trazenaRijec.getText());
    }
}

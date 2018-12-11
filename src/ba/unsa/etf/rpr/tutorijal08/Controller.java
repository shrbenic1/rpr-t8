package ba.unsa.etf.rpr.tutorijal08;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.io.File;

public class Controller {
    public TextField trazenaRijec;
    public ListView<String> lista;
    private ObservableList<String> observableList = FXCollections.synchronizedObservableList(FXCollections.observableArrayList());
    public Button trazi;
    public Button prekini;
    private boolean prekinuto = false;
    @FXML
    private ProgressIndicator progres = new ProgressIndicator(0);


    @FXML
    void initialize() {
        prekini.setDisable(true);
        progres.setVisible(false);
    }

    public void walk(String pocetak, String rijec) {
        if(prekinuto) {
            return;
        }
        File root = new File(pocetak);
        File[] list = root.listFiles();
        if (list == null) {
            return;
        }
        for (File f : list) {
            if(prekinuto) return;
            else if (f.isDirectory()) {
                walk(f.getAbsolutePath(), rijec);
            } else {
                if (f.getAbsoluteFile().toString().contains(rijec)) {
                    Platform.runLater( () -> observableList.add(f.getAbsoluteFile().toString()));
                    System.out.println("File:" + f.getAbsoluteFile());
                }
            }
        }
    }

    public void trazi(ActionEvent actionEvent) {
        new Thread(() -> {
            trazi.setDisable(true);
            prekini.setDisable(false);
            if(!prekinuto) {
                progres.setVisible(true);
                if(!lista.getItems().isEmpty()) {
                    Platform.runLater( () -> lista.getItems().clear());
                }
                walk(System.getProperty("user.home"), trazenaRijec.getText());
                Platform.runLater( () -> lista.setItems(observableList));
            }
            progres.setVisible(false);
            prekinuto = false;
            trazi.setDisable(false);
            prekini.setDisable(true);
        }).start();
    }

    public void prekini(ActionEvent actionEvent) {
        prekinuto = true;
    }
}

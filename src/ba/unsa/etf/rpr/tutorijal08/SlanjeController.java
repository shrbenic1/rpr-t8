package ba.unsa.etf.rpr.tutorijal08;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SlanjeController {
    public TextField postanskiBroj;

    private boolean validanPostanskiBroj(String n) throws Exception {
        URL url = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + n);
        BufferedReader ulaz = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String json = "";
        String line = null;
        while ((line = ulaz.readLine()) != null) {
            json = json + line;
        }
        ulaz.close();
        if (json.equals("OK")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void initialize() {
        postanskiBroj.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                if (!n) {
                    new Thread(() -> {
                        try {
                            if (validanPostanskiBroj(postanskiBroj.getCharacters().toString())) {
                                System.out.println("OK");
                                postanskiBroj.getStyleClass().removeAll("poljeNijeIspravno");
                                postanskiBroj.getStyleClass().add("poljeIspravno");
                            } else {
                                System.out.println("NOT OK");
                                postanskiBroj.getStyleClass().removeAll("poljeIspravno");
                                postanskiBroj.getStyleClass().add("poljeNijeIspravno");
                            }
                        } catch (Exception e) {
                            //
                        }
                    }).start();
                }
            }
        });
    }
}

package ba.unsa.etf.rpr.tutorijal08;

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

    private boolean validanPostanskiBroj(String n) {
        try {
            URL url = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + n);
            BufferedReader ulaz = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "";
            String line = null;
            while((line = ulaz.readLine()) != null) {
                json = json + line;
            }
            ulaz.close();
            if(json.equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //
        }
        return false;
    }

    @FXML
    public void initialize() {
        postanskiBroj.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean o, Boolean n) {
                GraphicValidationDecoration graphicValidationDecoration = new GraphicValidationDecoration();
                if (!n && !validanPostanskiBroj(postanskiBroj.getCharacters().toString())) {
                    graphicValidationDecoration.applyValidationDecoration(new ValidationMessage() {
                        @Override
                        public String getText() {
                            return "Neispravan poštanski broj!";
                        }
                        @Override
                        public Severity getSeverity() {
                        return Severity.ERROR;
                    }
                        @Override
                        public Control getTarget() {
                        return postanskiBroj;
                    }
                });
            } else {
                graphicValidationDecoration.removeDecorations(postanskiBroj);
            }
        }
    });
    }
}

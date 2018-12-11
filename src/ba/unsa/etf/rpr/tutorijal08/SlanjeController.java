package ba.unsa.etf.rpr.tutorijal08;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;

public class SlanjeController {
    public TextField postanskiBroj;

    private boolean validanPostanskiBroj(String n) {
        return true;
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

package util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

public class FXUtility {

    public static Alert alert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        // Eliminamos la línea alert.show() para que no se muestre automáticamente
        return alert;
    }

    public static TextInputDialog dialog(String title, String header){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        return dialog;
    }
}
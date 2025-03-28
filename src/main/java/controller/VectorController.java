package controller;

import domain.Vector;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import util.FXUtility;

import java.util.Optional;

public class VectorController {

    private Alert alert;
    private Vector vector;

    @javafx.fxml.FXML
    private TextField nTextField;

    @javafx.fxml.FXML
    private TextArea textArea;

    @javafx.fxml.FXML
    private Pane buttonPane;

    @javafx.fxml.FXML
    public void initialize() {
        // Crear la alerta sin mostrarla automáticamente
        this.alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Algoritmo Vector");
    }

    @javafx.fxml.FXML
    public void createOnAction(ActionEvent actionEvent) {
        try {
            int n = Integer.parseInt(this.nTextField.getText());
            if (isValid(n)) {
                this.vector = new Vector(n);
                this.buttonPane.setDisable(false);
                this.textArea.setText("El vector ha sido creado para " + n + " elementos");
            } else {
                alert.setContentText("Error. Por favor, ingrese un número positivo.");
                alert.show();
            }
        } catch (Exception ex) {
            alert.setContentText("Error. Por favor, ingrese un número válido");
            alert.show();
        }
    }

    private boolean isValid(int n) {
        return n > 0;
    }

    @javafx.fxml.FXML
    public void fill(ActionEvent actionEvent) {
        if (vector != null) {
            vector.fill();
            textArea.setText("Contenido del vector: " + vector.toString());
        } else {
            alert.setContentText("Error. Vector aún no creado.");
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void addByValue(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = FXUtility.dialog("Vector - Agregar por valor", "Ingrese el valor a agregar");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent() && !result.get().isEmpty()) {
                int value = Integer.parseInt(result.get());
                vector.add(value);
                textArea.setText("Valor " + value + " agregado al vector.\n\nContenido del vector: " + vector.toString());
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Error. Por favor, ingrese un entero válido.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void addByIndex(ActionEvent actionEvent) {
        try {
            TextInputDialog indexDialog = FXUtility.dialog("Vector - Agregar por índice", "Ingrese el índice");
            Optional<String> indexResult = indexDialog.showAndWait();

            if (indexResult.isPresent() && !indexResult.get().isEmpty()) {
                int index = Integer.parseInt(indexResult.get());

                TextInputDialog valueDialog = FXUtility.dialog("Vector - Agregar por índice", "Ingrese el valor");
                Optional<String> valueResult = valueDialog.showAndWait();

                if (valueResult.isPresent() && !valueResult.get().isEmpty()) {
                    int value = Integer.parseInt(valueResult.get());

                    vector.add(index, value);
                    textArea.setText("Valor " + value + " agregado en el índice " + index + ".\n\nContenido del vector: " + vector.toString());
                }
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Error. Por favor, ingrese valores enteros válidos.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void size(ActionEvent actionEvent) {
        textArea.setText("Tamaño del vector: " + vector.size() + "\n\nContenido del vector: " + vector.toString());
    }

    @javafx.fxml.FXML
    public void removeByValue(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = FXUtility.dialog("Vector - Eliminar por valor", "Ingrese el valor a eliminar");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent() && !result.get().isEmpty()) {
                int value = Integer.parseInt(result.get());
                boolean removed = vector.remove(Integer.valueOf(value));

                if (removed) {
                    textArea.setText("Valor " + value + " eliminado del vector.\n\nContenido del vector: " + vector.toString());
                } else {
                    textArea.setText("Valor " + value + " no encontrado en el vector.\n\nContenido del vector: " + vector.toString());
                }
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Error. Por favor, ingrese un entero válido.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void removeByIndex(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = FXUtility.dialog("Vector - Eliminar por índice", "Ingrese el índice a eliminar");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent() && !result.get().isEmpty()) {
                int index = Integer.parseInt(result.get());
                Object removed = vector.remove(index);

                if (removed != null) {
                    textArea.setText("Valor " + removed + " eliminado del índice " + index + ".\n\nContenido del vector: " + vector.toString());
                } else {
                    textArea.setText("Índice " + index + " inválido.\n\nContenido del vector: " + vector.toString());
                }
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Error. Por favor, ingrese un entero válido.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void contains(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = FXUtility.dialog("Vector - Contiene", "Ingrese el elemento a buscar");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent() && !result.get().isEmpty()) {
                int value = Integer.parseInt(result.get());
                boolean contains = vector.contains(Integer.valueOf(value));

                textArea.setText("El vector " + (contains ? "contiene" : "no contiene") + " el valor " + value + ".\n\nContenido del vector: " + vector.toString());
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Error. Por favor, ingrese un entero válido.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        vector.clear();
        textArea.setText("Vector vaciado.");
    }
}
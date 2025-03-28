package controller;

import domain.Probabilistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.Utility;

public class ProbabilisticController {

    private Probabilistic probabilistic;

    @FXML
    private Text txtMessage;

    @FXML
    private ComboBox<String> cbSelectAlgorithm;

    @FXML
    private Pane buttonPane;

    @FXML
    private TextField nTextField;

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() {
        probabilistic = new Probabilistic();

        // Configurar el ComboBox con opciones de algoritmos
        ObservableList<String> algorithms = FXCollections.observableArrayList(
                "Rabin-Miller", "Paradoja del Cumpleaños", "Búsqueda Aleatoria"
        );
        cbSelectAlgorithm.setItems(algorithms);
        cbSelectAlgorithm.getSelectionModel().selectFirst();
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        nTextField.clear();
        textArea.clear();
    }

    @FXML
    public void calculate(ActionEvent actionEvent) {
        try {
            String selectedAlgorithm = cbSelectAlgorithm.getValue();
            String input = nTextField.getText().trim();

            if (input.isEmpty()) {
                showAlert("Error", "Por favor, ingrese un valor.");
                return;
            }

            switch (selectedAlgorithm) {
                case "Rabin-Miller":
                    int number = Integer.parseInt(input);
                    if (number <= 0) {
                        showAlert("Error", "Por favor, ingrese un entero positivo.");
                        return;
                    }

                    boolean isPrime = probabilistic.isProbablePrime(number, 10);
                    textArea.setText(number + " es " + (isPrime ? "probablemente primo." : "compuesto."));
                    break;

                case "Paradoja del Cumpleaños":
                    int people = Integer.parseInt(input);
                    if (people <= 0) {
                        showAlert("Error", "Por favor, ingrese un número positivo de personas.");
                        return;
                    }

                    double probability = probabilistic.birthdayParadox(people);
                    textArea.setText("Con " + people + " personas, la probabilidad de que al menos 2 compartan cumpleaños es: "
                            + Utility.format(probability * 100) + "%");
                    break;

                case "Búsqueda Aleatoria":
                    int size = 100; // Crear un arreglo de tamaño 100
                    int[] array = new int[size];
                    Utility.fill(array);

                    int valueToFind = Integer.parseInt(input);
                    int maxAttempts = 50;

                    textArea.setText("Contenido del arreglo:\n" + Utility.show(array) + "\n\n");

                    int[] result = probabilistic.randomSearch(array, valueToFind, maxAttempts);

                    if (result[0] != -1) {
                        textArea.appendText("Elemento [" + valueToFind + "] encontrado en el índice: " + result[0]
                                + ". Intentos: " + result[1]);
                    } else {
                        textArea.appendText("Elemento [" + valueToFind + "] no encontrado. Máximo de intentos: " + result[1]);
                    }
                    break;
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, ingrese valores enteros válidos.");
        } catch (Exception e) {
            showAlert("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
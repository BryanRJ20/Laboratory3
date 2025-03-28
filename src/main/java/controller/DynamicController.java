package controller;

import domain.Dynamic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DynamicController {

    private Dynamic dynamic;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField tfValue;

    @FXML
    private ComboBox<String> cbSelectAlgorithm;

    @FXML
    private TextField tfResult;

    @FXML
    public void initialize() {
        dynamic = new Dynamic();

        // Configurar el ComboBox con opciones de algoritmos
        ObservableList<String> algorithms = FXCollections.observableArrayList(
                "Factorial", "Fibonacci", "Cambio de Moneda"
        );
        cbSelectAlgorithm.setItems(algorithms);
        cbSelectAlgorithm.getSelectionModel().selectFirst();
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        tfValue.clear();
        tfResult.clear();
    }

    @FXML
    public void calculate(ActionEvent actionEvent) {
        try {
            String selectedAlgorithm = cbSelectAlgorithm.getValue();
            String input = tfValue.getText().trim();

            if (input.isEmpty()) {
                showAlert("Error", "Por favor, ingrese un valor.");
                return;
            }

            switch (selectedAlgorithm) {
                case "Factorial":
                    int n = Integer.parseInt(input);
                    if (n < 0) {
                        showAlert("Error", "El factorial requiere un entero no negativo.");
                        return;
                    }
                    tfResult.setText(String.valueOf(dynamic.factorial(n)));
                    break;

                case "Fibonacci":
                    n = Integer.parseInt(input);
                    if (n < 0) {
                        showAlert("Error", "Fibonacci requiere un entero no negativo.");
                        return;
                    }
                    tfResult.setText(String.valueOf(dynamic.fibonacci(n)));
                    break;

                case "Cambio de Moneda":
                    // Para cambio de moneda, necesitamos analizar múltiples valores
                    String[] values = input.split(",");
                    if (values.length < 2) {
                        showAlert("Error", "Para Cambio de Moneda, ingrese valores separados por comas: moneda1,moneda2,...,cantidad");
                        return;
                    }

                    int amount = Integer.parseInt(values[values.length - 1]);
                    int[] coins = new int[values.length - 1];

                    for (int i = 0; i < values.length - 1; i++) {
                        coins[i] = Integer.parseInt(values[i].trim());
                    }

                    int result = dynamic.coinChange(coins, amount);
                    if (result == -1) {
                        tfResult.setText("No hay solución");
                    } else {
                        tfResult.setText(String.valueOf(result));
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
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CalBinariosController {

    @FXML
    private Button BUTTON_SUMA;

    @FXML
    private Button BUTTON_MULTIPLICAR;

    @FXML
    private Button BUTTON_TO_BINARY;

    @FXML
    private Button BUTTON_TO_DECIMAL;

    @FXML
    private Button BUTTON_DIVIDIR;

    @FXML
    private TextField FIELD_RESULTADO;

    @FXML
    private TextField INPUT_A;

    @FXML
    private TextField INPUT_B;

    @FXML
    private TextField INPUT_BINARY;

    @FXML
    private TextField INPUT_DECIMAL;

    @FXML
    private TextArea txtHistorial;

    private final List<String> historial = new ArrayList<>();

    @FXML
    void BUTTON_SUMA(ActionEvent event) {
        try {
            String binarioA = INPUT_A.getText();
            String binarioB = INPUT_B.getText();
            int decimalA = Integer.parseInt(binarioA, 2);
            int decimalB = Integer.parseInt(binarioB, 2);

            int resultadoDecimal = decimalA + decimalB;
            String resultadoBinario = Integer.toBinaryString(resultadoDecimal);
            FIELD_RESULTADO.setText(resultadoBinario);

            historial.add(binarioA + " + " + binarioB + " = " + resultadoBinario);
            actualizarHistorial();

        } catch (NumberFormatException e) {
            FIELD_RESULTADO.setText("Entrada no válida");
        }
    }

    @FXML
    void BUTTON_MULTIPLICAR(ActionEvent event) {
        try {
            String binarioA = INPUT_A.getText();
            String binarioB = INPUT_B.getText();
            int decimalA = Integer.parseInt(binarioA, 2);
            int decimalB = Integer.parseInt(binarioB, 2);

            int resultadoDecimal = decimalA * decimalB;
            String resultadoBinario = Integer.toBinaryString(resultadoDecimal);
            FIELD_RESULTADO.setText(resultadoBinario);

            historial.add(binarioA + " * " + binarioB + " = " + resultadoBinario);
            actualizarHistorial();

        } catch (NumberFormatException e) {
            FIELD_RESULTADO.setText("Entrada no válida");
        }
    }

    @FXML
    void BUTTON_TO_BINARY(ActionEvent event) {
        try {
            int decimal = Integer.parseInt(INPUT_DECIMAL.getText());
            String binario = Integer.toBinaryString(decimal);
            FIELD_RESULTADO.setText(binario);

            historial.add(decimal + " -> binario = " + binario);
            actualizarHistorial();
        } catch (NumberFormatException e) {
            FIELD_RESULTADO.setText("Entrada no válida");
        }
    }

    @FXML
    void BUTTON_TO_DECIMAL(ActionEvent event) {
        try {
            String binario = INPUT_BINARY.getText();
            int decimal = Integer.parseInt(binario, 2);
            FIELD_RESULTADO.setText(String.valueOf(decimal));

            historial.add(binario + " -> decimal = " + decimal);
            actualizarHistorial();
        } catch (NumberFormatException e) {
            FIELD_RESULTADO.setText("Entrada no válida");
        }
    }

    @FXML
    void BUTTON_DIVIDIR(ActionEvent event) {
        try {
            String binarioA = INPUT_A.getText();
            String binarioB = INPUT_B.getText();
            int decimalA = Integer.parseInt(binarioA, 2);
            int decimalB = Integer.parseInt(binarioB, 2);

            if (decimalB == 0) {
                FIELD_RESULTADO.setText("Error: No se puede dividir entre cero");
                return;
            }

            int resultadoDecimal = decimalA / decimalB;
            String resultadoBinario = Integer.toBinaryString(resultadoDecimal);
            FIELD_RESULTADO.setText(resultadoBinario);

            historial.add(binarioA + " / " + binarioB + " = " + resultadoBinario);
            actualizarHistorial();

        } catch (NumberFormatException e) {
            FIELD_RESULTADO.setText("Entrada no válida");
        }
    }

    private void actualizarHistorial() {
        if (txtHistorial == null) return;

        if (historial.isEmpty()) {
            txtHistorial.setText("No hay operaciones guardadas.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String item : historial) {
                sb.append(item).append("\n");
            }
            txtHistorial.setText(sb.toString());
        }
    }
}
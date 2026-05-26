package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalEcuacionesController implements Initializable {

    @FXML
    private ComboBox<String> COMBOTIPO;
    @FXML
    private Label LBLFORMULA;
    @FXML
    private Label LBLC;
    @FXML
    private Label LBLD;
    @FXML
    private TextField FIELDA;
    @FXML
    private TextField FIELDB;
    @FXML
    private TextField FIELDC;
    @FXML
    private TextField FIELDD;
    @FXML
    private TextField FIELDRESULTADO;
    @FXML
    private TextArea txtHistorial;

    @FXML
    private HBox HBOXD;

    private final List<String> historial = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        COMBOTIPO.setItems(FXCollections.observableArrayList("Lineal", "Cuadrática"));
        COMBOTIPO.getSelectionModel().selectFirst();
        actualizarVista("Lineal");
        actualizarHistorial();
    }

    @FXML
    void COMBOTIPO(ActionEvent event) {
        actualizarVista(COMBOTIPO.getValue());
        limpiarCampos();
    }

    private void actualizarVista(String tipo) {
        boolean esCuadratica = tipo != null && tipo.equals("Cuadrática");

        if (esCuadratica) {
            LBLFORMULA.setText("ax² + bx + c = d");
            LBLC.setText("c =");
            FIELDC.setPromptText("ej: 7");
        } else {
            LBLFORMULA.setText("ax + b = c");
            LBLC.setText("c =");
            FIELDC.setPromptText("ej: 7");
        }

        if (HBOXD != null) {
            HBOXD.setVisible(esCuadratica);
            HBOXD.setManaged(esCuadratica);
        } else {
            LBLD.setVisible(esCuadratica);
            FIELDD.setVisible(esCuadratica);
        }

        LBLC.setVisible(true);
        FIELDC.setVisible(true);
    }

    @FXML
    void BUTTONCALCULAR(ActionEvent event) {
        String tipo = COMBOTIPO.getValue();
        if (tipo == null) return;

        try {
            if (tipo.equals("Lineal")) {
                calcularLineal();
            } else {
                calcularCuadratica();
            }
        } catch (NumberFormatException e) {
            FIELDRESULTADO.setText("Error: Ingresa solo números válidos");
        }
    }

    @FXML
    void BUTTONLIMPIAR(ActionEvent event) {
        limpiarCampos();
    }

    private void calcularLineal() {
        double a = Double.parseDouble(FIELDA.getText());
        double b = Double.parseDouble(FIELDB.getText());
        double c = Double.parseDouble(FIELDC.getText());

        if (a == 0) {
            FIELDRESULTADO.setText("Error: a no puede ser 0");
            return;
        }

        double resultado = (c - b) / a;
        String texto = "x = " + resultado;
        FIELDRESULTADO.setText(texto);

        historial.add("Lineal: " + a + "x + " + b + " = " + c + " -> " + texto);
        actualizarHistorial();
    }

    private void calcularCuadratica() {
        double a = Double.parseDouble(FIELDA.getText());
        double b = Double.parseDouble(FIELDB.getText());
        double c = Double.parseDouble(FIELDC.getText());
        double d = Double.parseDouble(FIELDD.getText());

        if (a == 0) {
            FIELDRESULTADO.setText("Error: a no puede ser 0");
            return;
        }

        double cAjustado = c - d;
        double discriminante = b * b - 4 * a * cAjustado;

        if (discriminante < 0) {
            FIELDRESULTADO.setText("No hay soluciones reales");
            historial.add("Cuadrática: sin soluciones reales");
        } else {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            String texto = "x1 = " + x1 + ", x2 = " + x2;
            FIELDRESULTADO.setText(texto);
            historial.add("Cuadrática: " + a + "x² + " + b + "x + " + c + " = " + d + " -> " + texto);
        }

        actualizarHistorial();
    }

    private void limpiarCampos() {
        FIELDA.clear();
        FIELDB.clear();
        FIELDC.clear();
        FIELDD.clear();
        FIELDRESULTADO.clear();
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
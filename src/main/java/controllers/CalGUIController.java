package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalGUIController {
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";
    private String numActual = "";
    //------------------------------------------------------------------------------------------------
    @FXML
    private Button BUTTONCLEAR1;
    @FXML
    private Button BUTTONCLEARALL;
    @FXML
    private Button BUTTON0;
    @FXML
    private Button BUTTON1;
    @FXML
    private Button BUTTON2;
    @FXML
    private Button BUTTON3;
    @FXML
    private Button BUTTON4;
    @FXML
    private Button BUTTON5;
    @FXML
    private Button BUTTON6;
    @FXML
    private Button BUTTON7;
    @FXML
    private Button BUTTON8;
    @FXML
    private Button BUTTON9;
    @FXML
    private Button BUTTONPUNTO;
    @FXML
    private Button BUTTONSIGNO;
    @FXML
    private Button BUTTONIGUAL;
    @FXML
    private Button BUTTONMENOS;
    @FXML
    private Button BUTTONSUMAR;
    @FXML
    private Button BUTTONMULTIPLICAR;
    @FXML
    private Button BUTTONDIVIDIR;
    @FXML
    private TextField lblMensaje2;
    //------------------------------------------------------------------------------------------------
    private void digit(String d) { numActual += d; lblMensaje2.setText(numActual); }

    @FXML
    void BUTTON0(ActionEvent event) {
        digit("0"); }
    @FXML
    void BUTTON1(ActionEvent event) {
        digit("1"); }
    @FXML
    void BUTTON2(ActionEvent event) {
        digit("2"); }
    @FXML
    void BUTTON3(ActionEvent event) {
        digit("3"); }
    @FXML
    void BUTTON4(ActionEvent event) {
        digit("4"); }
    @FXML
    void BUTTON5(ActionEvent event) {
        digit("5"); }
    @FXML
    void BUTTON6(ActionEvent event) {
        digit("6"); }
    @FXML
    void BUTTON7(ActionEvent event) {
        digit("7"); }
    @FXML
    void BUTTON8(ActionEvent event) {
        digit("8"); }
    @FXML
    void BUTTON9(ActionEvent event) {
        digit("9"); }

    @FXML
    void BUTTONPUNTO(ActionEvent event) {
        if (!numActual.contains(".")) {
            numActual += numActual.isEmpty() ? "0." : ".";
            lblMensaje2.setText(numActual);
        }
    }

    @FXML
    void BUTTONSIGNO(ActionEvent event) {
        if (!numActual.isEmpty() && !numActual.equals("0")) {
            numActual = numActual.startsWith("-") ? numActual.substring(1) : "-" + numActual;
            lblMensaje2.setText(numActual);
        }
    }
    //------------------------------------------------------------------------------------------------
    @FXML
    void BUTTONIGUAL(ActionEvent event) {
        try {
            if (numActual.isEmpty() || operator.isEmpty()) {
                return;
            }
            num2 = Double.parseDouble(numActual);
            double resultado = 0;
            switch (operator) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else{
                        lblMensaje2.setText("Error: División por cero");
                        numActual = ""; operator = ""; num1 = 0; num2 = 0;
                        return;
                    }
                    break;
                default: return;
            }
            String resultadoTexto;
            if (resultado % 1 == 0) {
                resultadoTexto = String.valueOf((long) resultado);
            } else {
                resultadoTexto = String.valueOf(resultado);
            }
            lblMensaje2.setText(resultadoTexto);
            numActual = String.valueOf(resultado);
            operator = "";
        } catch (NumberFormatException e) {
            lblMensaje2.setText("Error");
            numActual = ""; operator = ""; num1 = 0; num2 = 0;
        }
    }

    //------------------------------------------------------------------------------------------------

    private void operar(String op) {
        if (numActual.isEmpty()) return;
        num1 = Double.parseDouble(numActual);
        operator = op;
        numActual = "";
        lblMensaje2.setText("");
    }

    @FXML
    void BUTTONSUMAR(ActionEvent event)       {
        operar("+"); }
    @FXML
    void BUTTONMENOS(ActionEvent event)       {
        operar("-"); }
    @FXML
    void BUTTONMULTIPLICAR(ActionEvent event) {
        operar("*"); }
    @FXML
    void BUTTONDIVIDIR(ActionEvent event)     {
        operar("/"); }

    //------------------------------------------------------------------------------------------------

    @FXML
    void BUTTONCLEARALL(ActionEvent event) {
        num1 = 0; num2 = 0; operator = ""; numActual = "";
        lblMensaje2.setText("");
    }

    @FXML
    void BUTTONCLEAR1(ActionEvent event) {
        if (numActual.isEmpty()) return;
        numActual = numActual.substring(0, numActual.length() - 1);
        lblMensaje2.setText(numActual.isEmpty() ? "0" : numActual);
    }
}
    //------------------------------------------------------------------------------------------------

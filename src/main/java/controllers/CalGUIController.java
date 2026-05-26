package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class CalGUIController {
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";
    private String numActual = "";

    private final List<String> historial = new ArrayList<>();

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
    private Button BUTTONELEVAR;
    @FXML
    private Button BUTTONRAIZ;
    @FXML
    private Button BUTTONLOG;
    @FXML
    private Button BUTTONPI;
    @FXML
    private Button BUTTONSENO;
    @FXML
    private Button BUTTONCOSENO;
    @FXML
    private Button BUTTONTANGENTE;
    @FXML
    private Button BUTTONHISTORIAL;
    @FXML
    private TextField lblMensaje2;
    //------------------------------------------------------------------------------------------------

    @FXML
    public void initialize() {
        BUTTONELEVAR.setText("x\u02E3");
        BUTTONRAIZ.setText("\u221A");
        BUTTONLOG.setText("log\u2081\u2080");
        BUTTONPI.setText("\u03C0");
    }

    private void digit(String d) {
        numActual += d; lblMensaje2.setText(numActual);
    }

    @FXML
    void BUTTON0(ActionEvent event) {
        digit("0");
    }
    @FXML
    void BUTTON1(ActionEvent event) {
        digit("1");
    }
    @FXML
    void BUTTON2(ActionEvent event) {
        digit("2");
    }
    @FXML
    void BUTTON3(ActionEvent event) {
        digit("3");
    }
    @FXML
    void BUTTON4(ActionEvent event) {
        digit("4");
    }
    @FXML
    void BUTTON5(ActionEvent event) {
        digit("5");
    }
    @FXML
    void BUTTON6(ActionEvent event) {
        digit("6");
    }
    @FXML
    void BUTTON7(ActionEvent event) {
        digit("7");
    }
    @FXML
    void BUTTON8(ActionEvent event) {
        digit("8");
    }
    @FXML
    void BUTTON9(ActionEvent event) {
        digit("9");
    }

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
                    } else {
                        lblMensaje2.setText("Error: División por cero");
                        numActual = ""; operator = ""; num1 = 0; num2 = 0;
                        return;
                    }
                    break;
                case "^":
                    resultado = Math.pow(num1, num2);
                    break;
                default: return;
            }

            historial.add(num1 + " " + operator + " " + num2 + " = " + resultado);
            mostrarResultadoFormateado(resultado);
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
        operar("+");
    }
    @FXML
    void BUTTONMENOS(ActionEvent event)       {
        operar("-");
    }
    @FXML
    void BUTTONMULTIPLICAR(ActionEvent event) {
        operar("*");
    }
    @FXML
    void BUTTONDIVIDIR(ActionEvent event)     {
        operar("/");
    }

    //------------------------------------------------------------------------------------------------

    @FXML
    void BUTTONELEVAR(ActionEvent event) {
        operar("^");
    }
    @FXML
    void BUTTONRAIZ(ActionEvent event) {
        if (numActual.isEmpty()) return;
        double x = Double.parseDouble(numActual);
        if (x >= 0) {
            double resultado = Math.sqrt(x);
            historial.add("\u221A(" + x + ") = " + resultado);
            mostrarResultadoFormateado(resultado);
        } else {
            lblMensaje2.setText("Error: Raíz Negativa");
            numActual = "";
        }
    }

    @FXML
    void BUTTONLOG(ActionEvent event) {
        if (numActual.isEmpty()) return;
        double x = Double.parseDouble(numActual);
        if (x > 0) {
            double resultado = Math.log10(x);
            historial.add("log10(" + x + ") = " + resultado);
            mostrarResultadoFormateado(resultado);
        } else {
            lblMensaje2.setText("Error: Log no válido");
            numActual = "";
        }
    }

    @FXML
    void BUTTONPI(ActionEvent event) {
        numActual = String.valueOf(Math.PI);
        lblMensaje2.setText(numActual);
    }

    private void mostrarResultadoFormateado(double resultado) {
        String resultadoTexto;
        if (resultado % 1 == 0) {
            resultadoTexto = String.valueOf((long) resultado);
        } else {
            resultadoTexto = String.valueOf(resultado);
        }
        lblMensaje2.setText(resultadoTexto);
        numActual = resultadoTexto;
        operator = "";
    }

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

    //------------------------------------------------------------------------------------------------

    @FXML
    void BUTTONSENO(ActionEvent event) {
        if (numActual.isEmpty()) return;
        try {
            double x = Double.parseDouble(numActual);
            double resultado = Math.sin(Math.toRadians(x));
            historial.add("sin(" + x + "°) = " + resultado);
            mostrarResultadoFormateado(resultado);
        } catch (NumberFormatException e) {
            lblMensaje2.setText("Error");
            numActual = "";
        }
    }

    @FXML
    void BUTTONCOSENO(ActionEvent event) {
        if (numActual.isEmpty()) return;
        try {
            double x = Double.parseDouble(numActual);
            double resultado = Math.cos(Math.toRadians(x));
            historial.add("cos(" + x + "°) = " + resultado);
            mostrarResultadoFormateado(resultado);
        } catch (NumberFormatException e) {
            lblMensaje2.setText("Error");
            numActual = "";
        }
    }

    @FXML
    void BUTTONTANGENTE(ActionEvent event) {
        if (numActual.isEmpty()) return;
        try {
            double x = Double.parseDouble(numActual);
            if (Math.abs(x % 180) == 90) {
                lblMensaje2.setText("Error: Indefinido");
                numActual = "";
                return;
            }
            double resultado = Math.tan(Math.toRadians(x));
            historial.add("tan(" + x + "°) = " + resultado);
            mostrarResultadoFormateado(resultado);
        } catch (NumberFormatException e) {
            lblMensaje2.setText("Error");
            numActual = "";
        }
    }

    //-----------------------------------------------------------------------------------------------------------------

    @FXML
    void BUTTONHISTORIAL(ActionEvent event) {
        System.out.println("\n--- HISTORIAL DE LA CALCULADORA ---");
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones guardadas.");
        } else {
            for (String operacion : historial) {
                System.out.println(operacion);
            }
        }
        System.out.println("------------------------------------");
    }
}

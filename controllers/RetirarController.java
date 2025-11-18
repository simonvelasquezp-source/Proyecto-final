package controllers;

import app.model.Monedero;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RetirarController {

    @FXML private TextField txtMonto;
    @FXML private Button btnRetirar;
    @FXML private Label lblMensaje;

    private Monedero monederoActual;

    public void setDatos(Monedero actual) {
        this.monederoActual = actual;
    }

    @FXML
    private void initialize() {
        btnRetirar.setOnAction(e -> retirar());
    }

    private void retirar() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            monederoActual.retirar(monto, LocalDate.now());
            lblMensaje.setText("Retiro exitoso");
        } catch (Exception e) {
            lblMensaje.setText("Monto inválido o saldo insuficiente");
        }
    }
}



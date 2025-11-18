package controllers;

import app.model.Monedero;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DepositarController {

    @FXML private TextField txtMonto;
    @FXML private Button btnDepositar;
    @FXML private Label lblMensaje;

    private Monedero monederoActual;

    public void setDatos(Monedero actual) {
        this.monederoActual = actual;
    }

    @FXML
    private void initialize() {
        btnDepositar.setOnAction(e -> depositar());
    }

    private void depositar() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            monederoActual.depositar(monto, LocalDate.now());
            lblMensaje.setText("Depósito exitoso");
        } catch (Exception e) {
            lblMensaje.setText("Monto inválido");
        }
    }
}


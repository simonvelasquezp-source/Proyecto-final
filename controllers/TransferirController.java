package controllers;

import app.model.Monedero;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TransferirController {

    @FXML private TextField txtMonto;
    @FXML private ComboBox<Monedero> comboDestino;
    @FXML private Button btnTransferir;
    @FXML private Label lblMensaje;

    private Monedero monederoActual;
    private ArrayList<Monedero> todos;

    public void setDatos(Monedero actual, ArrayList<Monedero> todos) {
        this.monederoActual = actual;
        this.todos = todos;

        comboDestino.getItems().addAll(todos);
        comboDestino.getItems().remove(monederoActual);

        comboDestino.setConverter(new javafx.util.StringConverter<Monedero>() {
            @Override
            public String toString(Monedero m) {
                return (m == null) ? "" : (m.getId() + " - " + m.getNombre());
            }

            @Override
            public Monedero fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    private void initialize() {
        btnTransferir.setOnAction(e -> transferir());
    }

    private void transferir() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            Monedero destino = comboDestino.getValue();

            if (destino == null) {
                lblMensaje.setText("Selecciona un monedero destino");
                return;
            }

            monederoActual.transferir(monto, destino, LocalDate.now());
            lblMensaje.setText("Transferencia exitosa");

        } catch (Exception e) {
            lblMensaje.setText("Monto inválido");
        }
    }
}



package controllers;

import app.model.Cliente;
import app.model.Monedero;
import app.model.Reporte;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReporteController {

    @FXML
    private TextArea txtReporte;

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        mostrarReporte();
    }

    private void mostrarReporte() {
        if (cliente == null) return;

        StringBuilder sb = new StringBuilder();

        sb.append("=== REPORTE FINANCIERO ===\n\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("Cédula: ").append(cliente.getCedula()).append("\n");
        sb.append("Correo: ").append(cliente.getCorreo()).append("\n");
        sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n");
        sb.append("Puntos actuales: ").append(cliente.getPuntos()).append("\n");
        sb.append("Rango: ").append(cliente.getRangoActual()).append("\n\n");

        sb.append("=== MONEDEROS ===\n");
        for (Monedero m : cliente.getMonederos()) {
            sb.append("Monedero: ").append(m.getNombre())
                    .append(" | Saldo: ").append(m.getSaldo()).append("\n");
        }

        sb.append("\n=== HISTORIAL POR MONEDERO ===\n");
        for (Monedero m : cliente.getMonederos()) {
            sb.append("\n> ").append(m.getNombre()).append(" <\n");
            for (var t : m.getHistorial()) {
                sb.append(t.toString()).append("\n");
            }
        }

        txtReporte.setText(sb.toString());
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtReporte.getScene().getWindow();
        stage.close();
    }
}



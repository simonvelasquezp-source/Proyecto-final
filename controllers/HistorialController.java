package controllers;

import app.model.Monedero;
import app.model.Transaccion;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class HistorialController {

    @FXML
    private ListView<Transaccion> listaHistorial;

    private Monedero monedero;

    public void setMonedero(Monedero m) {
        this.monedero = m;
        cargarHistorial();
    }

    private void cargarHistorial() {
        if (monedero != null) {
            listaHistorial.getItems().setAll(monedero.getHistorial());
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) listaHistorial.getScene().getWindow();
        stage.close();
    }
}




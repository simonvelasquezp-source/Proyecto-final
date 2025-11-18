package controllers;

import app.model.Cliente;
import app.model.Monedero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class DashboardController {

    @FXML
    private Label lblBienvenida;

    @FXML
    private ListView<Monedero> listaMonederos;

    @FXML
    private Button btnOperaciones;

    @FXML
    private Button btnHistorial;

    private Cliente usuarioActual;

    public void setUsuarioActual(Cliente usuario) {
        this.usuarioActual = usuario;
        lblBienvenida.setText("Bienvenido, " + usuario.getNombre());
        if (usuario instanceof Cliente c) {
            listaMonederos.getItems().addAll(usuario.getMonederos());
        }
    }
        @FXML
        private void initialize () {
            btnOperaciones.setOnAction(e -> abrirOperaciones());
            btnHistorial.setOnAction(e -> abrirHistorial());
        }

        private void abrirOperaciones () {
            Monedero monederoSeleccionado = listaMonederos.getSelectionModel().getSelectedItem();
            if (monederoSeleccionado == null) return;

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operaciones.fxml"));
                Parent root = loader.load();

                OperacionesController opController = loader.getController();
                opController.setDatos(monederoSeleccionado, usuarioActual.getMonederos());

                Stage stage = new Stage();
                stage.setTitle("Operaciones");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void abrirHistorial () {
            Monedero monederoSeleccionado = listaMonederos.getSelectionModel().getSelectedItem();
            if (monederoSeleccionado == null) return;

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historial.fxml"));
                Parent root = loader.load();

                HistorialController histController = loader.getController();
                histController.setMonedero(monederoSeleccionado);

                Stage stage = new Stage();
                stage.setTitle("Historial");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    @FXML
    private void abrirReporte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Reporte.fxml"));
            Parent root = loader.load();
            ReporteController reporteController = loader.getController();
            reporteController.setCliente(usuarioActual);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reporte de Movimientos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
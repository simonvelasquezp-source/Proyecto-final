package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import app.model.Monedero;

import java.io.IOException;
import java.util.ArrayList;

public class OperacionesController {

    @FXML private Label lblTitulo;
    @FXML private Button btnAbrirDepositar;
    @FXML private Button btnAbrirRetirar;
    @FXML private Button btnAbrirTransferir;

    private Monedero monederoActual;
    private ArrayList<Monedero> todos;

    public void setDatos(Monedero actual, ArrayList<Monedero> todos) {
        this.monederoActual = actual;
        this.todos = todos;

        lblTitulo.setText("Monedero: " + actual.getId() + " — " + actual.getNombre());
    }

    @FXML
    private void initialize() {
        btnAbrirDepositar.setOnAction(e -> abrirDepositar());
        btnAbrirRetirar.setOnAction(e -> abrirRetirar());
        btnAbrirTransferir.setOnAction(e -> abrirTransferir());
    }

    private void abrirDepositar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Depositar.fxml"));
            Parent root = loader.load();

            DepositarController controller = loader.getController();
            controller.setDatos(monederoActual);

            Stage stage = new Stage();
            stage.setTitle("Depositar");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirRetirar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Retirar.fxml"));
            Parent root = loader.load();

            RetirarController controller = loader.getController();
            controller.setDatos(monederoActual);

            Stage stage = new Stage();
            stage.setTitle("Retirar");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTransferir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Transferir.fxml"));
            Parent root = loader.load();

            TransferirController controller = loader.getController();
            controller.setDatos(monederoActual, todos);

            Stage stage = new Stage();
            stage.setTitle("Transferir");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



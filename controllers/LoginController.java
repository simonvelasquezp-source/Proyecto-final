package controllers;

import app.model.Monedero;
import app.model.Usuario;
import app.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContraseña;
    @FXML private Button btnIniciarSesion;
    @FXML private Label lblMensaje;

    private ArrayList<Monedero> todosLosMonederos;

    public void setListaMonederos(ArrayList<Monedero> listaMonederos) {
        this.todosLosMonederos = listaMonederos;
    }

    @FXML
    private void iniciarSesionAction() {
        String nombreIngresado = txtUsuario.getText().trim();
        String contrasenaIngresada = txtContraseña.getText().trim();

        if (nombreIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
            lblMensaje.setText("Por favor completa todos los campos");
            return;
        }

        for (Monedero m : todosLosMonederos) {
            Usuario u = m.getPropietario();
            if (u.getNombre().equals(nombreIngresado)) {
                if (u.getContrasena().equals(contrasenaIngresada)) {
                    lblMensaje.setText("¡Bienvenido " + u.getNombre() + "!");
                    abrirDashboard((Cliente) u); // Pasamos como Cliente
                    return;
                } else {
                    lblMensaje.setText("Contraseña incorrecta");
                    return;
                }
            }
        }
        lblMensaje.setText("Usuario no encontrado");
    }

    private void abrirDashboard(Cliente clienteActivo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setUsuarioActual(clienteActivo);

            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

            btnIniciarSesion.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package app.model;

public class Cliente extends Usuario{
    public Cliente(String nombre, String cedula, String correo, String contraseña, String telefono, String tipoUsuario){
        super(nombre, cedula, correo, contraseña, telefono, tipoUsuario);
    }
    
}

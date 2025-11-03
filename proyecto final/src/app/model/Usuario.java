package app.model;

public abstract class Usuario {
    protected String nombre;
    protected String cedula;
    protected String correo;
    protected String contraseña;
    protected String telefono;
    protected String tipoUsuario;
    protected ArrayList <Transaccion> transacciones;
    public Usuario(String nombre, String cedula, String correo, String contraseña, String telefono, String tipoUsuario, Transaccion transacciones){
        this.nombre=nombre;
        this.cedula=cedula;
        this.correo=correo;
        this.contraseña=contraseña;
        this.telefono=telefono;
        this.tipoUsuario=tipoUsuario;
        this.transacciones= new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        this.cedula=cedula;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getContraseña(){
        return contraseña;
    }
    public void setContraseña(String contraseña){
        this.contraseña=contraseña;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
    public String getTipoUsuario(){
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario){
        this.tipoUsuario=tipoUsuario;
    }
    
}


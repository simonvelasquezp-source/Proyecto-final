package app.model;

public class Puntos {
    private Usuario propietario;
    private int puntos;
    public Puntos( Usuario propietario){
        this.propietario=propietario;
        this.puntos=0;
    }
    public Usuario getPropietario(){
        return propietario;
    }
    public void setPropietario(Usuario propietario){
        this.propietario=propietario;
    }
    public int getPuntos(){
        return puntos;
    }
    public void setPuntos(int puntos){
        this.puntos=puntos;
    }
    public void agregarPuntos(int cantidad){
        this.puntos+= cantidad;
    }
}


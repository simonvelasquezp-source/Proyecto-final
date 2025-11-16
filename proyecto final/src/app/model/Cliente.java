package app.model;
import java.util.ArrayList;
public class Cliente extends Usuario{
    public Cliente(String nombre, String cedula, String correo, String contraseña, String telefono, String tipoUsuario, Transaccion transacciones, Monedero monederos,  int puntos, String rangoActual){
        super(nombre, cedula, correo, contraseña, telefono, tipoUsuario);
        this.monederos= new ArrayList<>();
        this.puntos=puntos;
        this.rangoActual=rangoActual;
    }
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public String getRangoActual(){
        return rangoActual;
    }

    public void setRangoActual(String rangoActual) {
        this.rangoActual = rangoActual;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getCedula() {
        return super.getCedula();
    }
    public Monedero getMonedero(String nombreMonedero) {
        for (Monedero m : monederos) {
            if (m.getNombre().equalsIgnoreCase(nombreMonedero)) {
                return m;
            }
        }
        return null;
    }


    public ArrayList<Monedero> getMonederos() {
        return monederos;
    }

    public void setMonederos(ArrayList<Monedero> monederos) {
        this.monederos = monederos;
    }
    public void agregarPuntos(int puntosGanados) {
        this.puntos += puntosGanados;
    }
    public void agregarMonedero(Monedero m) {
        this.monederos.add(m);
    }
}



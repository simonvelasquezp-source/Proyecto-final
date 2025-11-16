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

    public static void asignarPuntos(Cliente cliente, Transaccion transaccion) {
        double monto = transaccion.getMonto();
        int puntosGanados = 0;

        if (transaccion instanceof Deposito) {
            puntosGanados = (int) ((monto / 1000) * 10);
        } else if (transaccion instanceof Retiro) {
            puntosGanados = (int) ((monto / 100) * 2);
        } else if (transaccion instanceof Transferencia) {
            puntosGanados = (int) ((monto / 100) * 3);
        }

        cliente.agregarPuntos(puntosGanados);

        System.out.println( puntosGanados + " puntos asignados al cliente " + cliente.getNombre());
    }
}

    


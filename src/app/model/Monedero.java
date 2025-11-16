package app.model;
import java.util.ArrayList;
import java.time.*;
import app.util.Notificador;
public class Monedero extends CuentaBase{
    private Puntos puntos;
    private ArrayList<Transaccion> historial;
    public Monedero(String numeroCuenta, Usuario propietario, String id, double saldo, String nombre){
        super(numeroCuenta, propietario, id, nombre);
        this.puntos=new Puntos(propietario);
        this.saldo=saldo;
        this.historial= new ArrayList<>();
    }
    public Puntos getPuntos(){
        return puntos;
    }
    public void setPuntos(Puntos puntos ){
        this.puntos=puntos;
    }
    public ArrayList <Transaccion> getHistorial(){
        return historial;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setHistorial(ArrayList<Transaccion> historial) {
        this.historial = historial;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Monedero de " + propietario.getNombre() + 
                           " | Saldo: " + saldo + " | Puntos: " + puntos);
    }
    public void depositar(double monto) {
        super.depositar(monto);
        int puntosGanados = (int)(monto / 1000 * 10);
        puntos.agregarPuntos(puntosGanados);
        agregarTransaccion(new Deposito(monto, LocalDate.now(), propietario.getNombre(), "Depósito exitoso"));
        if (propietario instanceof Cliente c) {
            Notificador.enviarCorreo(c, "Depósito realizado",
                    "Has depositado $" + monto + " en tu monedero '" + getNombre() + "'. Nuevo saldo: $" + saldo);
        }
    }
    public void retirar(double monto) {
        if (monto <= 0) return;
        if (saldo >= monto) {
            saldo -= monto;
            Retiro ret = new Retiro(monto, LocalDate.now(), propietario.getNombre(), "Retiro realizado");
            historial.add(ret);
            System.out.println("Retiro de $" + monto + " realizado correctamente.");
            int puntosGanados = (int)(monto / 1000 * 2);
            puntos.agregarPuntos(puntosGanados);
            System.out.println("Has ganado " + puntosGanados + " puntos por tu retiro.");

            if (propietario instanceof Cliente c) {
                Notificador.enviarWhatsApp(c, "Has retirado $" + monto + " de tu monedero '" + getNombre() + "'.");
                Notificador.alertaSaldoBajo(c, saldo);
            }
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void transferir(double monto, Monedero destino) {
        if (monto <= 0) return;
        if (saldo >= monto) {
            saldo -= monto;
            destino.saldo += monto;
            Transferencia trans = new Transferencia(monto, LocalDate.now(), propietario.getNombre(), "Transferencia a " + destino.getPropietario().getNombre(), destino);
            historial.add(trans);
            destino.getHistorial().add(trans);

            System.out.println("Transferencia de $" + monto + " a " + destino.getPropietario().getNombre() + " realizada con éxito.");
            int puntosGanados = (int)(monto / 1000 * 3);
            puntos.agregarPuntos(puntosGanados);
            System.out.println("Has ganado " + puntosGanados + " puntos por transferir dinero.");

            if (propietario instanceof Cliente cOrigen) {
                Notificador.enviarCorreo(cOrigen, "Transferencia realizada",
                        "Has transferido $" + monto + " a " + destino.getPropietario().getNombre() + ".");
                Notificador.alertaSaldoBajo(cOrigen, saldo);
            }
            if (destino.getPropietario() instanceof Cliente cDestino) {
                Notificador.enviarWhatsApp(cDestino, "Has recibido $" + monto + " de " + propietario.getNombre() + ".");
            }
        } else {
            System.out.println("Fondos insuficientes para transferir.");
        }
    }

    public String obtenerRangoCliente() {
        int totalPuntos = puntos.getPuntos();
        return RangoCliente.determinarRango(totalPuntos);
    }
    public void agregarTransaccion(Transaccion transaccion) {

        historial.add(transaccion);
    }

}

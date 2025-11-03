package app.model;
import java.time.*;
public class Retiro extends Transaccion {
    public Retiro(double monto, LocalDate fecha, String cliente, String descripcion){
        super(monto, fecha, cliente, descripcion);
    }
     @Override
    public void aplicarTransaccion(CuentaBase cuenta){
        if (cuenta.getSaldo() >= getMonto()) {
            cuenta.setSaldo(cuenta.getSaldo() - getMonto());
            System.out.println("Retiro de $" + getMonto() + " realizado. Nuevo saldo: $" + cuenta.getSaldo());
        } else {
            System.out.println("Fondos insuficientes para realizar el retiro.");
        }
    }
}


package app.model;
import java.time.*;
public  class Deposito extends Transaccion {
    public Deposito(double monto, LocalDate fecha, String cliente, String descripcion, CuentaBase cuentaDestino){
        super(monto, fecha, cliente, descripcion, cuentaDestino);
    }

    @Override
    public void aplicarTransaccion(CuentaBase cuenta){
        cuenta.setSaldo(cuenta.getSaldo()+getMonto());
        System.out.println("Desposito de:"+ getMonto()+ "realizado. Nuevo saldo de:"+ cuenta.getSaldo());
    }
    
}

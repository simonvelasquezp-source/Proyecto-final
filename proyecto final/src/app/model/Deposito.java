package app.model;
import java.time.*;
public  class Deposito extends Transaccion {
    public Deposito(double monto, LocalDate fecha, String cliente, String descripcion){
        super(monto, fecha, cliente, descripcion);
    }
 @Override
    public void aplicarTransaccion(CuentaBase cuenta){
        cuenta.setSaldo(cuenta.getSaldo()+getMonto());
        System.out.println("Desposito de:"+ getMonto()+ "realizado. Nuevo saldo de:"+ cuenta.getSaldo());
    }
    
    
}


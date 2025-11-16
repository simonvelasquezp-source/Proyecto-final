package app.model;
import java.time.*;
public class Transferencia extends Transaccion {
    private CuentaBase cuentaDestino;
    public Transferencia(double monto, LocalDate fecha, String cliente, String descripcion, CuentaBase cuentaDestino){
        super(monto, fecha, cliente, descripcion);
        this.cuentaDestino=cuentaDestino;
    }
    public CuentaBase getCuentaDestino(){
        return cuentaDestino;
    }
    public void setCuentaDestino(CuentaBase cuentaDestino){
        this.cuentaDestino=cuentaDestino;
    }

    @Override
    public void aplicarTransaccion(CuentaBase cuentaOrigen){
        if (cuentaOrigen.getSaldo() >= getMonto()) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - getMonto());
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + getMonto());
            System.out.println("Transferencia de $" + getMonto() + " realizada de " 
                                + cuentaOrigen.getId() + " a " + cuentaDestino.getId());
        } else {
            System.out.println("Fondos insuficientes para realizar la transferencia.");
        }
    }

    }


package app.model;
import java.time.*;
public  class Deposito extends Transaccion {
    public Deposito(double monto, LocalDate fecha, String cliente, String descripcion){
        super(monto, fecha, cliente, descripcion);
    }

    
    
}

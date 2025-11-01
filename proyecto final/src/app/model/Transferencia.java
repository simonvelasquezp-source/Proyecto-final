package app.model;
import java.time.*;
public class Transferencia extends Transaccion {
    public Transferencia(double monto, LocalDate fecha, String cliente, String descripcion){
        super(monto, fecha, cliente, descripcion);
    }
    
}

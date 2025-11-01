package app.model;
import java.time.*;
public class Retiro extends Transaccion {
    public Retiro(double monto, LocalDate fecha, String cliente, String descripcion){
        super(monto, fecha, cliente, descripcion);
    }
    
}

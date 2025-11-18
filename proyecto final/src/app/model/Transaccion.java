package app.model;
import java.time.*;
public abstract class Transaccion {

    private double monto;
    private LocalDate fecha;
    private String cliente;
    private String descripcion;

    public Transaccion(double monto, LocalDate fecha, String cliente, String descripcion){
        this.monto=monto;
        this.fecha=LocalDate.now();
        this.cliente=cliente;
        this.descripcion=descripcion;
    }

    public double getMonto(){
        return monto;
    }
    public void setMonto(double monto){
        this.monto=monto;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha=fecha;
    }
    public String getCliente(){
        return cliente;
    }
    public void setCliente(String cliente){
        this.cliente=cliente;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public abstract void aplicarTransaccion(CuentaBase cuenta);
    
     @Override
    public String toString(){
        return "=== Transaccion === "+ "Monto:"+ monto+
                                    "Fecha:"+ fecha+
                                    "Cliente:"+ cliente+
                                    "Descripcion:"+ descripcion;
    }
}



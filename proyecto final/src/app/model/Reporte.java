package app.model;

public class Reporte {
    public void generarReporteCliente(Cliente cliente, int puntos, ArrayList<Transaccion> transacciones){
        System.out.println("Reporte");
        System.out.println("Cuenta a nombre de:"+ cliente.getNombre());
        System.out.println("Cedula:"+cliente.getCedula());
        System.out.println("Correo:"+ cliente.getCorreo());
        System.out.println("Numero telefonico:"+cliente.getTelefono());
        System.out.println("Puntos actuales"+puntos);
        System.out.println("Rango: " + RangoCliente.determinarRango(puntos));
        System.out.println("Número de transacciones: " + transacciones.size());
        
    }
}


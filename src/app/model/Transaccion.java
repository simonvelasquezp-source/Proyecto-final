package app.model;
import app.util.Notificador;

import java.time.*;
import java.util.ArrayList;

public abstract class Transaccion {

    private double monto;
    private LocalDate fecha;
    private String cliente;
    private String descripcion;
    private CuentaBase cuentaDestino;

    public Transaccion(double monto, LocalDate fecha, String cliente, String descripcion, CuentaBase cuentaDestino) {
        this.monto = monto;
        this.fecha = fecha;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.cuentaDestino=cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public abstract void aplicarTransaccion(CuentaBase cuenta);

    @Override
    public String toString() {
        return "=== Transaccion === " + "Monto:" + monto +
                "Fecha:" + fecha +
                "Cliente:" + cliente +
                "Descripcion:" + descripcion;
    }

    public static void procesarTransaccionesProgramadas(
            ArrayList<Transaccion> lista,
            ArrayList<Cliente> clientes
    ) {

        // 1. Ordenar por fecha (mucho más limpio que bubble sort)
        lista.sort((a, b) -> a.getFecha().compareTo(b.getFecha()));

        System.out.println("\nProcesando transacciones programadas...");

        for (Transaccion t : lista) {

            if (t.getFecha().isEqual(LocalDate.now())) {

                System.out.println("Ejecutando transacción programada: " + t.getDescripcion());

                for (Cliente c : clientes) {
                    if (c.getNombre().equalsIgnoreCase(t.getCliente())) {

                        ArrayList<Monedero> monederos = c.getMonederos();

                        if (monederos != null && !monederos.isEmpty()) {

                            Monedero m = monederos.get(0);

                            t.aplicarTransaccion(m);


                            String mensaje = switch (t.getClass().getSimpleName()) {
                                case "Deposito" -> "Depósito automático de $" + t.getMonto() + " realizado con éxito.";
                                case "Retiro" -> "Retiro automático de $" + t.getMonto() + " ejecutado correctamente.";
                                case "Transferencia" -> "Transferencia automática de $" + t.getMonto() + " completada.";
                                default -> "Transacción automática ejecutada.";
                            };

                            Notificador.enviarWhatsApp(c, mensaje);
                            Notificador.enviarCorreo(c, "Transacción Programada Ejecutada", mensaje);

                            m.agregarTransaccion(t);
                        }
                    }
                }
            }
        }
    }

}




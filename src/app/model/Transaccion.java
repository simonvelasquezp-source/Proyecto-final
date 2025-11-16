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

    public static void procesarTransaccionesProgramadas(ArrayList<Transaccion> lista, ArrayList<Cliente> clientes) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getFecha().isAfter(lista.get(j + 1).getFecha())) {
                    Transaccion temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n Procesando transacciones programadas...");

        for (Transaccion t : lista) {
            if (t.getFecha().isEqual(LocalDate.now())) {
                System.out.println("Ejecutando transacción programada: " + t.getDescripcion());

                for (Cliente c : clientes) {
                    if (c.getNombre().equalsIgnoreCase(t.getCliente())) {
                        Monedero monedero = c.getMonederos();

                        if (monedero != null) {
                            if (t instanceof Deposito dep) {
                                monedero.depositar(dep.getMonto());
                            } else if (t instanceof Retiro ret) {
                                monedero.retirar(ret.getMonto());
                            } else if (t instanceof Transferencia trans) {
                                monedero.transferir(trans.getMonto(), trans.getDestino());
                            }

                            String mensaje = switch (t.getClass().getSimpleName()) {
                                case "Deposito" -> " Depósito automático de $" + t.getMonto() + " realizado con éxito.";
                                case "Retiro" -> " Retiro automático de $" + t.getMonto() + " ejecutado correctamente.";
                                case "Transferencia" -> " Transferencia automática de $" + t.getMonto() + " completada.";
                                default -> "ℹ Transacción automática ejecutada.";
                            };

                            Notificador.enviarWhatsApp(c, mensaje);
                            Notificador.enviarCorreo(c, "Transacción Programada Ejecutada", mensaje);

                            monedero.agregarTransaccion(t);
                        }
                    }
                }
            }
        }
}
    }




package app.model;

import java.util.ArrayList;

public class Reporte {
    public void generarReporteCliente(Cliente cliente, int puntos, ArrayList<Transaccion> transacciones) {
        System.out.println("Reporte");
        System.out.println("Cuenta a nombre de:" + cliente.getNombre());
        System.out.println("Cedula:" + cliente.getCedula());
        System.out.println("Correo:" + cliente.getCorreo());
        System.out.println("-----------------------------");
        System.out.println("Numero telefonico:" + cliente.getTelefono());
        System.out.println("Puntos actuales:" + puntos);
        System.out.println("Rango: " + RangoCliente.determinarRango(puntos));
        System.out.println("Número de transacciones: " + transacciones.size());


        double saldoTotal = 0;
        for (Monedero m : cliente.getMonederos()) {
            saldoTotal += m.getSaldo();
        }
        System.out.println("Saldo total en todos los monederos: $" + saldoTotal);


        for (Monedero m : cliente.getMonederos()) {
            System.out.println("Monedero: " + m.getNombre() + " | Saldo: $" + m.getSaldo());
            ArrayList<Transaccion> historial = m.getHistorial();
            System.out.println("Transacciones: " + historial.size());
            for (Transaccion t : historial) {
                System.out.println(" - " + t);

            }
        }
        analizarPatronesDeGasto(cliente);
    }

    public void analizarPatronesDeGasto(Cliente cliente) {
        System.out.println("     ANÁLISIS DE PATRONES DE GASTO     ");
        double totalGastado = 0;
        double totalDepositado = 0;
        int retiros = 0, depositos = 0, transferencias = 0;

        for (Monedero m : cliente.getMonederos()) {
            for (Transaccion t : m.getHistorial()) {
                if (t instanceof Retiro) {
                    retiros++;
                    totalGastado += t.getMonto();
                } else if (t instanceof Deposito) {
                    depositos++;
                    totalDepositado += t.getMonto();
                } else if (t instanceof Transferencia) {
                    transferencias++;
                    totalGastado += t.getMonto();
                }
            }
        }

        System.out.println("Total depositado: $" + totalDepositado);
        System.out.println("Total gastado: $" + totalGastado);
        System.out.println("Retiros realizados: " + retiros);
        System.out.println("Depósitos realizados: " + depositos);
        System.out.println("Transferencias realizadas: " + transferencias);


        if (totalGastado > totalDepositado * 0.8) {
            System.out.println("⚠ El cliente presenta un patrón de gasto alto (riesgo de sobregiro).");
        } else if (totalGastado > totalDepositado * 0.5) {
            System.out.println(" Patrón de gasto medio, comportamiento financiero moderado.");
        } else {
            System.out.println(" Patrón de gasto bajo, tendencia al ahorro.");
        }
    }


}

package app.model;

public class Reporte {
    public static void generarReporteCliente(Cliente cliente, ArrayList<Transaccion> transacciones) {
        System.out.println(" REPORTE ");
        mostrarDatosCliente(cliente);
        mostrarResumenMonederos(cliente);
        mostrarTransaccionesPorMonedero(cliente);
        analizarPatronesCliente(cliente);
        generarAlertas(cliente);
        generarRecomendaciones(cliente);
        resumenFinanciero(cliente);
    }


    public static void mostrarDatosCliente(Cliente cliente) {
        System.out.println("\n--- Información del cliente ---");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Cédula: " + cliente.getCedula());
        System.out.println("Correo: " + cliente.getCorreo());
        System.out.println("Número telefónico: " + cliente.getTelefono());
        System.out.println("Puntos actuales: " + cliente.getPuntos());
        System.out.println("Rango: " + cliente.getRangoActual());
        System.out.println("----------------------------------------");
    }


    public static void mostrarResumenMonederos(Cliente cliente) {
        double saldoTotal = 0;

        for (Monedero m : cliente.getMonederos()) {
            saldoTotal += m.getSaldo();
        }

        System.out.println("\n--- Resumen de monederos ---");
        System.out.println("Cantidad de monederos: " + cliente.getMonederos().size());
        System.out.println("Saldo total: $" + saldoTotal);
    }


    public static void mostrarTransaccionesPorMonedero(Cliente cliente) {
        System.out.println("\n--- Historial de transacciones por monedero ---");

        for (Monedero m : cliente.getMonederos()) {
            System.out.println("\nMonedero: " + m.getNombre() + " | Saldo: $" + m.getSaldo());
            ArrayList<Transaccion> historial = m.getHistorial();
            System.out.println("Cantidad de transacciones: " + historial.size());

            for (Transaccion t : historial) {
                System.out.println(" - " + t);
            }

            analizarPatronesMonedero(m);
        }
    }


    public static void analizarPatronesCliente(Cliente cliente) {
        System.out.println("\n--- Análisis de patrones del cliente ---");

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
        System.out.println("Retiros: " + retiros + " | Depósitos: " + depositos + " | Transferencias: " + transferencias);

        if (totalGastado > totalDepositado * 0.8) {
            System.out.println("Patrón: gasto alto (riesgo de sobregiro).");
        } else if (totalGastado > totalDepositado * 0.5) {
            System.out.println("Patrón: gasto medio, comportamiento moderado.");
        } else {
            System.out.println("Patrón: gasto bajo, tendencia al ahorro.");
        }
    }



    public static void analizarPatronesMonedero(Monedero m) {
        int retirosAltos = 0;

        for (Transaccion t : m.getHistorial()) {
            if (t instanceof Retiro && t.getMonto() > 50000) {
                retirosAltos++;
            }
        }

        System.out.println("\nPatrones del monedero:");

        if (retirosAltos >= 3) {
            System.out.println("• Patrón detectado: retiros altos frecuentes.");
        } else {
            System.out.println("• Sin patrones de gasto relevantes.");
        }
    }



    public static void generarAlertas(Cliente cliente) {
        System.out.println("\n--- Alertas ---");

        double saldoTotal = cliente.getMonederos()
                .stream()
                .mapToDouble(Monedero::getSaldo)
                .sum();

        if (saldoTotal < 10000) {
            System.out.println("⚠ Alerta: saldo total muy bajo.");
        }

        long retirosAltos = cliente.getMonederos().stream()
                .flatMap(m -> m.getHistorial().stream())
                .filter(t -> t instanceof Retiro && t.getMonto() > 30000)
                .count();

        if (retirosAltos >= 3) {
            System.out.println("⚠ Alerta: retiros altos frecuentes.");
        }

        if (retirosAltos == 0 && saldoTotal > 500000) {
            System.out.println("✔ Excelente gestión: saldo alto y pocos retiros.");
        }
    }

    public static void generarRecomendaciones(Cliente cliente) {
        System.out.println("\n--- Recomendaciones ---");

        double totalDepositado = 0;
        double totalGastado = 0;

        for (Monedero m : cliente.getMonederos()) {
            for (Transaccion t : m.getHistorial()) {
                if (t instanceof Deposito) {
                    totalDepositado += t.getMonto();
                } else if (t instanceof Retiro || t instanceof Transferencia) {
                    totalGastado += t.getMonto();
                }
            }
        }

        if (totalGastado > totalDepositado * 0.8) {
            System.out.println("Recomendación: reduce tus gastos o establece un presupuesto mensual.");
        } else if (totalDepositado > totalGastado * 2) {
            System.out.println("Recomendación: buena salud financiera; podrías considerar invertir.");
        } else {
            System.out.println("Recomendación: mantén un equilibrio saludable entre ingresos y gastos.");
        }
    }


    public static void resumenFinanciero(Cliente cliente) {
        System.out.println("\n--- Resumen financiero ---");

        double transMayor = 0;
        double transMenor = Double.MAX_VALUE;
        Monedero monederoMasActivo = null;
        int mayorCantidadTrans = 0;

        double totalTransacciones = 0;
        int cantidadTrans = 0;

        for (Monedero m : cliente.getMonederos()) {
            int transaccionesMonedero = m.getHistorial().size();
            if (transaccionesMonedero > mayorCantidadTrans) {
                mayorCantidadTrans = transaccionesMonedero;
                monederoMasActivo = m;
            }

            for (Transaccion t : m.getHistorial()) {
                double monto = t.getMonto();

                totalTransacciones += monto;
                cantidadTrans++;

                if (monto > transMayor) transMayor = monto;
                if (monto < transMenor) transMenor = monto;
            }
        }

        double promedio = (cantidadTrans == 0) ? 0 : totalTransacciones / cantidadTrans;

        System.out.println("Transacción mayor: $" + transMayor);
        System.out.println("Transacción menor: $" + transMenor);
        System.out.println("Promedio por transacción: $" + promedio);

        if (monederoMasActivo != null) {
            System.out.println("Monedero más activo: " + monederoMasActivo.getNombre() +
                    " (" + mayorCantidadTrans + " transacciones)");
        } else {
            System.out.println("No se encontraron transacciones.");
        }
    }

}


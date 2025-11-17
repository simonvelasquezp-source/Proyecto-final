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
            System.out.println(" El cliente presenta un patrón de gasto alto (riesgo de sobregiro).");
        } else if (totalGastado > totalDepositado * 0.5) {
            System.out.println(" Patrón de gasto medio, comportamiento financiero moderado.");
        } else {
            System.out.println(" Patrón de gasto bajo, tendencia al ahorro.");
        }
    }
    public void mostrarSaldo(Monedero m) {
        System.out.println("Saldo actual: " + m.getSaldo());
    }

    public void mostrarPuntos(Monedero m) {
        if (m.getPropietario() instanceof Cliente c) {
            System.out.println("Puntos actuales: " + c.getPuntos());
        } else {
            System.out.println("Este usuario no acumula puntos.");
        }
    }
    public void generarHistorial(Monedero m) {
        System.out.println("===== HISTORIAL DE TRANSACCIONES =====");
        for (Transaccion t : m.getHistorial()) {
            System.out.println(t);
        }
    }
    public ArrayList<Transaccion> obtenerTransaccionesPorTipo(Monedero m, String tipo) {
        ArrayList<Transaccion> resultado = new ArrayList<>();

        for (Transaccion t : m.getHistorial()) {
            if (t.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
    public void mostrarTransaccionesPorTipo(Monedero m, String tipo) {
        ArrayList<Transaccion> filtradas = obtenerTransaccionesPorTipo(m, tipo);

        System.out.println("\n===== TRANSACCIONES DE TIPO: " + tipo.toUpperCase() + " =====");
        if (filtradas.isEmpty()) {
            System.out.println("No hay transacciones de este tipo.");
            return;
        }

        for (Transaccion t : filtradas) {
            System.out.println(t);
        }
    }
    public void detectarPatronesDeGasto(Monedero m) {

        int retirosAltos = 0;

        for (Transaccion t : m.getHistorial()) {
            if (t instanceof Retiro && t.getMonto() > 50000) {
                retirosAltos++;
            }
        }

        System.out.println(" PATRONES DE GASTO ");

        if (retirosAltos >= 3) {
            System.out.println(" Patron detectado: Retiros altos frecuentes.");
        } else {
            System.out.println("No se detectan patrones de gasto relevantes.");
        }
    }
    public void resumenFinanciero(Cliente cliente) {
        System.out.println("===== RESUMEN FINANCIERO AVANZADO =====");

        double transaccionMayor = 0;
        double transaccionMenor = Double.MAX_VALUE;
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

                if (monto > transaccionMayor) transaccionMayor = monto;
                if (monto < transaccionMenor) transaccionMenor = monto;
            }
        }
        double promedio = (cantidadTrans == 0) ? 0 : totalTransacciones / cantidadTrans;

        System.out.println("Transacción mayor registrada: $" + transaccionMayor);
        System.out.println("Transacción menor registrada: $" + transaccionMenor);
        System.out.println("Promedio de monto por transacción: $" + promedio);

        if (monederoMasActivo != null) {
            System.out.println("Monedero más activo: " + monederoMasActivo.getNombre() +
                    " (" + mayorCantidadTrans + " transacciones)");
        } else {
            System.out.println("No se encontraron transacciones.");
        }
    }
    public void generarAlertas(Cliente cliente) {
        double saldoTotal = 0;
        for (Monedero m : cliente.getMonederos()) {
            saldoTotal += m.getSaldo();
        }

        if (saldoTotal < 10000) {
            System.out.println(" Alerta:  Tiene un saldo muy bajo.");
        }
        int retirosRecientes = 0;

        for (Monedero m : cliente.getMonederos()) {
            for (Transaccion t : m.getHistorial()) {
                if (t instanceof Retiro && t.getMonto() > 30000) {
                    retirosRecientes++;
                }
            }
        }
        if (retirosRecientes >= 3) {
            System.out.println(" Cuidado: Retiros altos frecuentes.");
        }
        if (retirosRecientes == 0 && saldoTotal > 500000) {
            System.out.println(" Buena gestión: saldo alto y pocos retiros.");
        }
    }
    public void generarRecomendaciones(Cliente cliente) {
        System.out.println(" Recomendaciones ");

        double totalGastado = 0;
        double totalDepositado = 0;

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
            System.out.println("Recomendación: Reduce tus gastos o programa un presupuesto mensual.");
        } else if (totalDepositado > totalGastado * 2) {
            System.out.println("Recomendación: Tienes buena salud financiera, podrías considerar invertir.");
        } else {
            System.out.println("Recomendación: Mantén balanceado tu flujo de dinero.");
        }
    }

}

package app.servicio;

import app.model.*;
import java.time.LocalDate;

public class GestorTransacciones {

    public void depositar(Monedero monedero, double monto) {
        if (monto > 0) {
            monedero.setSaldo(monedero.getSaldo() + monto);

            Deposito deposito = new Deposito(monto, LocalDate.now(),
                    monedero.getPropietario().getNombre(),
                    "Depósito en monedero " + monedero.getId());

            monedero.agregarTransaccion(deposito);

            Puntos.asignarPuntos((Cliente) monedero.getPropietario(), deposito);

            System.out.println("Depósito exitoso de $" + monto + " en el monedero " + monedero.getId());
        } else {
            System.out.println("Monto inválido. Debe ser mayor que cero.");
        }
    }

    public void retirar(Monedero monedero, double monto) {
        if (monto > 0 && monedero.getSaldo() >= monto) {
            monedero.setSaldo(monedero.getSaldo() - monto);

            Retiro retiro = new Retiro(monto, LocalDate.now(),
                    monedero.getPropietario().getNombre(),
                    "Retiro del monedero " + monedero.getId());

            monedero.agregarTransaccion(retiro);

            Puntos.asignarPuntos((Cliente) monedero.getPropietario(), retiro);

            System.out.println("Retiro exitoso de $" + monto + " del monedero " + monedero.getId());
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    public void transferir(Monedero origen, Monedero destino, double monto) {
        if (monto > 0 && origen.getSaldo() >= monto) {

            Transferencia transferenciaSalida = new Transferencia(
                    monto, LocalDate.now(),
                    origen.getPropietario().getNombre(),
                    "Transferencia enviada a " + destino.getPropietario().getNombre(),
                    destino);

            transferenciaSalida.aplicarTransaccion(origen);

            origen.agregarTransaccion(transferenciaSalida);

            Transferencia transferenciaEntrada = new Transferencia(
                    monto, LocalDate.now(),
                    destino.getPropietario().getNombre(),
                    "Transferencia recibida de " + origen.getPropietario().getNombre(),
                    destino);

            destino.agregarTransaccion(transferenciaEntrada);

            Puntos.asignarPuntos((Cliente) origen.getPropietario(), transferenciaSalida);
            Puntos.asignarPuntos((Cliente) destino.getPropietario(), transferenciaEntrada);

            System.out.println("Transferencia de $" + monto + " realizada exitosamente.");
        } else {
            System.out.println("Saldo insuficiente o monto inválido para la transferencia.");
        }
    }
}

    


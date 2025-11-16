package app.util;

import app.model.Cliente;

public class Notificador {
    public static void enviarNotificacion(Cliente cliente, String mensaje) {
        System.out.println("Notificación para " + cliente.getNombre() + ": " + mensaje);
    }

    public static void enviarWhatsApp(Cliente cliente, String mensaje) {
        System.out.println(" Enviando mensaje por WhatsApp a " + cliente.getTelefono() + ": " + mensaje);
    }

    public static void enviarCorreo(Cliente cliente, String asunto, String mensaje) {
        System.out.println(" Enviando correo a " + cliente.getCorreo());
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje: " + mensaje);
    }

    public static void enviarSMS(Cliente cliente, String mensaje) {
        System.out.println(" Enviando SMS a " + cliente.getTelefono() + ": " + mensaje);
    }

    public static void alertaSaldoBajo(Cliente cliente, double saldo) {
        if (saldo < 5000) {
            String mensaje = " Alerta: Tu saldo actual es de $" + saldo + ". Considera recargar tu monedero.";
            enviarWhatsApp(cliente, mensaje);
            enviarCorreo(cliente, "Alerta de Saldo Bajo", mensaje);
        }
    }

    public static void recordatorioTransaccionProgramada(Cliente cliente, String descripcion) {
        String mensaje = " Recordatorio: Tienes una transacción programada próximamente. (" + descripcion + ")";
        enviarCorreo(cliente, "Recordatorio de Transacción", mensaje);
        enviarWhatsApp(cliente, mensaje);
    }
}

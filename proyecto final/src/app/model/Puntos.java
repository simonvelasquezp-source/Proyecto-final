package app.model;

public class Puntos {
    private Usuario propietario;
    private int puntos;
    public Puntos( Usuario propietario){
        this.propietario=propietario;
        this.puntos=0;
    }
    public Usuario getPropietario(){
        return propietario;
    }
    public void setPropietario(Usuario propietario){
        this.propietario=propietario;
    }
    public int getPuntos(){
        return puntos;
    }
    public void setPuntos(int puntos){
        this.puntos=puntos;
    }
    public void agregarPuntos(int cantidad){
        this.puntos+= cantidad;
    }
    public static void asignarPuntos(Cliente cliente, Transaccion transaccion) {
        double monto = transaccion.getMonto();
        int puntosGanados = 0;

        if (transaccion instanceof Deposito) {
            puntosGanados = (int) ((monto / 1000) * 10);
        } else if (transaccion instanceof Retiro) {
            puntosGanados = (int) ((monto / 100) * 2);
        } else if (transaccion instanceof Transferencia) {
            puntosGanados = (int) ((monto / 100) * 3);
        }

        cliente.agregarPuntos(puntosGanados);

        System.out.println( puntosGanados + " puntos asignados al cliente " + cliente.getNombre());
    }
    public static String canjearPuntos(Cliente cliente, int puntosNecesarios) {
        if (cliente.getPuntos() < puntosNecesarios) return null;

        cliente.setPuntos(cliente.getPuntos() - puntosNecesarios);


        if (puntosNecesarios == 100) {
            return "Canjeado: 10% reducción en comisión por transferencias (aplicar en lógica de transferencia).";
        } else if (puntosNecesarios == 500) {
            return "Canjeado: 1 mes sin cargos por retiros (administrar por fuera).";
        } else if (puntosNecesarios == 1000) {

            if (!cliente.getMonederos().isEmpty()) {
                Monedero primer = cliente.getMonederos().get(0);
                primer.setSaldo(primer.getSaldo() + 50);
            }
            return "Canjeado: Bono de saldo de 50 unidades aplicado al primer monedero.";
        } else {
            return "Canje realizado: beneficio genérico.";
        }
    }
    public static int calcularPuntos(Transaccion transaccion) {
        double monto = transaccion.getMonto();

        if (transaccion instanceof Deposito) {
            return (int) ((monto / 1000) * 10);
        }
        if (transaccion instanceof Retiro) {
            return (int) ((monto / 100) * 2);
        }
        if (transaccion instanceof Transferencia) {
            return (int) ((monto / 100) * 3);
        }

        return 0;
    }

    public void asignarPorTransaccion(Transferencia transDestino) {

    }


    public int getPuntos() {
        return puntos;
    }
}




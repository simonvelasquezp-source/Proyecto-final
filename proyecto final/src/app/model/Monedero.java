package app.model;

public class Monedero extends CuentaBase{
    private Puntos puntos;
    public Monedero(String numeroCuenta, Usuario propietario, String id, double saldo){
        super(numeroCuenta, propietario, id);
        this.puntos=new Puntos(propietario);
        this.saldo=saldo;
    }
    public int getPuntos(){
        return puntos;
    }
    public void setPuntos(int puntos ){
        this.puntos=puntos;
    }
 @Override
    public void mostrarResumen() {
        System.out.println("Monedero de " + propietario.getNombre() + 
                           " | Saldo: " + saldo + " | Puntos: " + puntos);
    }
    public void depositar(double monto) {
        saldo += monto;
       
        int puntosGanados = (int)(monto / 1000 * 10);
        puntos.agregarPuntos(puntosGanados);
    }

    public String obtenerRangoCliente() {
        int totalPuntos = puntos.getPuntos();
        return RangoCliente.determinarRango(totalPuntos);
    }
}


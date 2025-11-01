package app.model;

public class Monedero extends CuentaBase{
    private int puntos;
    public Monedero(String numeroCuenta, Usuario propietario){
        super(numeroCuenta, propietario);

        this.puntos=0;
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
    
}

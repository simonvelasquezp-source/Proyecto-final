package app.model;

public abstract class CuentaBase {
    
    protected String numeroCuenta;
    protected double saldo;
    protected Usuario propietario;
    protected String id;

    public CuentaBase(String numeroCuenta, Usuario propietario) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = 0;
        this.id=id;
    }
    public void setSaldo(double saldo){
        this.saldo=saldo;
    }
    public double getSaldo() { 
        return saldo; }
    
    public String getId(){
        return id;
    }    
    public void setId(String id){
        this.id=id;
    }

    public void depositar(double monto) {
        if (monto > 0) saldo += monto;
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) saldo -= monto;
    }
    public abstract void mostrarResumen();
}



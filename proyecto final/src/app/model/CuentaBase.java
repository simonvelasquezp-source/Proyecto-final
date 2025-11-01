package app.model;

public abstract class CuentaBase {
    
    protected String numeroCuenta;
    protected double saldo;
    protected Usuario propietario;

    public CuentaBase(String numeroCuenta, Usuario propietario) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = 0;
    }

    public double getSaldo() { 
        return saldo; }

    public void depositar(double monto) {
        if (monto > 0) saldo += monto;
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) saldo -= monto;
    }
    public abstract void mostrarResumen();
}


package app.model;
import app.util.Notificador;
public class Monedero extends CuentaBase{
    private Puntos puntos;
    private ArrayList<Transaccion> historial;
    private ArrayList<String[]> transaccionesProgramadas;
    public Monedero(String numeroCuenta, Usuario propietario, String id, double saldo, String nombre){
        super(numeroCuenta, propietario, id);
        this.puntos=new Puntos(propietario);
        this.saldo=saldo;
        this.historial= new ArrayList<>();
        this.transaccionesProgramadas = new ArrayList<>();
    }
    public Puntos getPuntos(){
        return puntos;
    }
  public ArrayList <Transaccion> getHistorial(){
        return historial;
    }

    public Usuario getPropietario() {
        return propietario;
    }
    @Override
    public String getNombre() {
        return super.getNombre();
    }
    @Override
    public void mostrarResumen() {
        System.out.println("Monedero de " + propietario.getNombre() + 
                           " | Saldo: " + saldo + " | Puntos: " + puntos.getPuntos());
    }
    public void depositar(double monto, LocalDate fecha) {
        super.depositar(monto);

        agregarTransaccion(new Deposito(monto, LocalDate.now(), propietario.getNombre(),
                "Depósito exitoso", this));

        if (propietario instanceof Cliente c) {
            Notificador.enviarCorreo(c, "Depósito realizado",
                    "Has depositado $" + monto + " en tu monedero '" + getNombre() + "'. Nuevo saldo: $" + saldo);
        }
    }
    public void retirar(double monto, LocalDate fecha) {
        if (monto <= 0 || saldo < monto) {
            System.out.println("Fondos insuficientes.");
            return;
        }
        saldo -= monto;
        Retiro r = new Retiro(monto, fecha, propietario.getNombre(), "Retiro en " + getNombre());
        agregarTransaccion(r);

        if (propietario instanceof Cliente c) {
            Notificador.enviarWhatsApp(c, "Has retirado $" + monto + ".");
        }
    }
    public void transferir(double monto, Monedero destino, LocalDate fecha) {
        if (monto <= 0) return;
        if (saldo >= monto) {
            saldo -= monto;
            destino.saldo += monto;
            Transferencia transOrigen = new Transferencia(monto, LocalDate.now(), propietario.getNombre(),
                    "Transferencia a " + destino.getPropietario().getNombre(), destino);

            historial.add(transOrigen);
            destino.getHistorial().add(transOrigen);
            //System
            System.out.println("Transferencia de $" + monto + " a " + destino.getPropietario().getNombre() + " realizada con éxito.");

            Transferencia transDestino = new Transferencia(monto, LocalDate.now(),
                    propietario.getNombre(),
                    "Transferencia recibida de " + this.getPropietario().getNombre(),
                    this
            );
            destino.historial.add(transDestino);
            Puntos.asignarPuntos((Cliente) destino.getPropietario(), transDestino);

            int puntosGanados = Puntos.calcularPuntos(transDestino);
            System.out.println("Has ganado " + puntosGanados + " puntos por transferir dinero.");


            if (propietario instanceof Cliente cOrigen) {
                Notificador.enviarCorreo(cOrigen, "Transferencia realizada",
                        "Has transferido $" + monto + " a " + destino.getPropietario().getNombre() + ".");
                Notificador.alertaSaldoBajo(cOrigen, saldo);
            }
            if (destino.getPropietario() instanceof Cliente cDestino) {
                Notificador.enviarWhatsApp(cDestino, "Has recibido $" + monto + " de " + propietario.getNombre() + ".");
            }
        } else {
            System.out.println("Fondos insuficientes para transferir.");
        }
    }

    public String obtenerRangoCliente() {
        if (propietario instanceof Cliente c) {
            return RangoCliente.determinarRango(c.getPuntos());
        }
        return "Sin rango";
    }
    public void agregarTransaccion(Transaccion transaccion) {

        historial.add(transaccion);
    }
    public void agendarTransaccion(String tipo, double monto, LocalDate fecha, Monedero destino) {

        String idDestino = (destino == null) ? "null" : destino.getId();
        transaccionesProgramadas.add(new String[]{
                tipo,
                String.valueOf(monto),
                fecha.toString(),
                idDestino
        });

        System.out.println("Transacción programada para el " + fecha);
    }
    public void ejecutarProgramadas(LocalDate hoy, ArrayList<Monedero> todos) {
        ArrayList<String[]> ejecutadas = new ArrayList<>();
        for (String[] tp : transaccionesProgramadas) {
            LocalDate fecha = LocalDate.parse(tp[2]);
            if (fecha.equals(hoy)) {
                String tipo = tp[0];
                double monto = Double.parseDouble(tp[1]);
                String idDest = tp[3];

                switch (tipo.toLowerCase()) {
                    case "deposito":
                        this.depositar(monto, fecha);
                        break;

                    case "retiro":
                        this.retirar(monto, fecha);
                        break;

                    case "transferencia":
                        for (Monedero x : todos) {
                            if (x.getId().equals(idDest)) {
                                this.transferir(monto, x, fecha);
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("Tipo de transacción no reconocido: " + tipo);
                }
                ejecutadas.add(tp);
            }
        }
        transaccionesProgramadas.removeAll(ejecutadas);
    }
}

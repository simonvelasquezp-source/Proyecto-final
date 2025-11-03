package app.model;

public class RangoCliente {
    public static String determinarRango(int puntos) {
        if (puntos < 1000) {
            return "Bronce";
        } else if (puntos < 5000) {
            return "Plata";
        } else if (puntos < 10000) {
            return "Oro";
        } else {
            return "Platino";
        }
    }
}


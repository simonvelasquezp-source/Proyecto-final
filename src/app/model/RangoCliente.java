package app.model;

public class RangoCliente {
     public static String determinarRango(int puntos) {
        if (puntos>= 0 && puntos <= 500) {
            return "Bronce";
        } else if (puntos>= 501 && puntos <= 1000) {
            return "Plata";
        } else if (puntos>= 1001 && puntos<= 5000) {
            return "Oro";
        } else {
            return "Platino";
        }
    }
}
//Plata: 501–1000 puntos.
//■ Oro: 1001–5000 puntos.
//■ Platino: Más de 5000 puntos.
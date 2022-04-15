package clases;

import cinema.exceptions.*;

public class Principal {

    public static void main(String[] args) throws ExcepcionFilaIncorrecta, ExcepcionPersonaIncorrecta, ExcepcionButacaOcupada, ExcepcionAsientoIncorrecto, ExcepcionButacaLibre {
        Cine cine = new Cine();
        cine.iniciar();
    }
}

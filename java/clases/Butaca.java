package clases;

import lombok.Data;
import java.util.Objects;
@Data
public class Butaca {
    private int numFila;
    private int numAsiento;
    private String nombreReserva;

    public Butaca(int numFila, int numAsiento, String nombreReserva) {
        this.numFila = numFila;
        this.numAsiento = numAsiento;
        this.nombreReserva = nombreReserva;
    }

    public Butaca(int numFila, int numAsiento) {
        this.numFila = numFila;
        this.numAsiento = numAsiento;

    }


    @Override
    //Generate -> Equals and hashcode
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Butaca butaca = (Butaca) o;
        return numFila == butaca.numFila && numAsiento == butaca.numAsiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numFila, numAsiento);
    }
    @Override
    public String toString(){
        return String.format("Fila: %d, Asiento: %d, Persona: %s",
                numFila, numAsiento, nombreReserva);
    }

}

package clases;

import cinema.exceptions.*;

import java.util.Scanner;

public class Cine {

    private int numFilas;
    private int numAsientos;
    private String[][] sala;
    private boolean salir = true;
    private int opcion;
    GestionButacas gestionButacas;

    public  Cine(){
        gestionButacas = new GestionButacas();
    }

    public void iniciar() throws ExcepcionFilaIncorrecta, ExcepcionPersonaIncorrecta, ExcepcionButacaOcupada, ExcepcionAsientoIncorrecto, ExcepcionButacaLibre {
        pedirDatosIniciales();
        menu();

    }

    public void menu() throws ExcepcionFilaIncorrecta, ExcepcionPersonaIncorrecta, ExcepcionButacaOcupada, ExcepcionAsientoIncorrecto, ExcepcionButacaLibre {
         Scanner sc = new Scanner(System.in);
         boolean salir=true;
         int opcion;
         while(salir){
             System.out.println();
             System.out.println("Por favor, selecciona la opcion deseada: ");
             System.out.println();
             System.out.println("1. Muestra todas las butacas reservadas");
             System.out.println("2. Muestra las butacas reservadas por una persona");
             System.out.println("3. Reservar una butaca");
             System.out.println("4. Anular la reserva de una butaca");
             System.out.println("5. Anular todas las reservas de una persona");
             System.out.println("0. Salir");

             opcion=sc.nextInt();
             switch(opcion){
                 case 1:
                     mostrarButacas();
                     break;

                 case 2:
                     muestraButacaPersona();
                     break;

                 case 3:
                     reservaButaca();
                     break;
                 case 4:
                     eliminarButaca();
                     break;
                 case 5:
                     eliminarButacaPorPersona();
                     break;


                 case 0:
                     System.out.println("Hasta la proxima");
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Por favor, elegir una opcion correcta");

             }

         }

    }

    public void pedirDatosIniciales(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el numero de filas de la sala: ");
        numFilas = sc.nextInt();
        System.out.println("Indica el numero de asientos por fila");
        numAsientos = sc.nextInt();
        sala = new String[numFilas+1][numAsientos+1];


        for (int i = 0; i < sala.length ; i++) {
            for (int j = 0; j < sala[0].length ; j++) {
                if(i == 0 & j==0)sala[i][j]= " ";
                else if(i == 0) sala[i][j] = String.valueOf(j);
                else if(j == 0) sala[i][j] = String.valueOf(i);
                else sala[i][j] = "X";
            }
        }

    }

    // TODO: mostrarButaques: Mostra totes les butaques reservades

    public void mostrarButacas(){
        gestionButacas.getButacas()
                .forEach(System.out::println);
    }

    // TODO: mostrarButaquesPersona: Demana el nom de la persona que ha fet
    //  la reserva i mostra totes les butaques reservades per aquesta persona.

    public void muestraButacaPersona() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el nonbre de la persona: ");
        String nomPersona = sc.next();
        gestionButacas.getButacas().stream()
                .filter(butaca -> butaca.getNombreReserva().equals(nomPersona))
                // todo: comprobar que el stream se consume
                .forEach(System.out::println);
    }

    // TODO: introduirFila: Demana el nombre de fila, si aquest està entre 1 i el nombre de files
    //  totals, el retorna. Si no, retorna una excepció personalitzada ExcepcioFilaIncorrecta.

    public int introducirFila() throws ExcepcionFilaIncorrecta {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el numero de la fila: ");
        int numFila = sc.nextInt();
        if(numFila > 0 && numFila < numFilas){
            return numFila;
        }
        throw new ExcepcionFilaIncorrecta("La fila es incorrecta");
    }

    //TODO: introduirPersona: demana a l’usuari el nom de la persona i
    // el retorna si és correcte. Si conté números,
    // llença

    public String introducirPersona() throws ExcepcionPersonaIncorrecta {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el nonbre de la persona: ");
        String nomPersona = sc.next();
        if(nomPersona.matches(".*[0-9].*")) {
            throw new ExcepcionPersonaIncorrecta("El nombre de la persona es incorrecto");

        }
        return nomPersona;

    }

    //TODO: introduirSeient: Demana el seient, si el número està entre 1 i el número total de seients,
    // el retorna. Sino retorna una excepció del tipus ExcepcioSeientIncorrecte.

    public int introducirAsiento() throws ExcepcionAsientoIncorrecto {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el numero del asiento: ");
        int numAsiento = sc.nextInt();
        if(numAsiento > 0 && numAsiento < numAsientos){
            return numAsiento;
        }
        throw new ExcepcionAsientoIncorrecto("El asiento es incorrecto");
    }
    // TODO: reservarButaca: Demana a l’usuari un número de fila (crida al mètode introduirFila),
    //  un número de seient (crida al mètode introduirSeient), el nom de la persona que fa la
    //  reserva (crida al mètode introduirPersona) i reserva la butaca.


    public void reservaButaca() throws ExcepcionFilaIncorrecta, ExcepcionAsientoIncorrecto, ExcepcionPersonaIncorrecta, ExcepcionButacaOcupada {
        int numFila = introducirFila();
        int numAsiento = introducirAsiento();
        String nomPersona = introducirPersona();

        Butaca butaca = new Butaca(numFila,numAsiento,nomPersona);

        gestionButacas.agregarButaca(butaca);

    }
    //TODO:anularReserva: Demana a l’usuari un número de fila (crida al mètode introduirFila),
    // Un número de seient (crida al mètode introduirSeient) i elimina la reserva de la butaca.

    public void eliminarButaca() throws ExcepcionFilaIncorrecta, ExcepcionAsientoIncorrecto, ExcepcionButacaLibre {
        int numFila = introducirFila();
        int numAsiento = introducirAsiento();
        gestionButacas.eliminarButaca(numFila,numAsiento);
    }

    //TODO:anularReservaPersona: Demana a l’usuari el nom de la persona
    // (crida al mètode introduirPersona) i elimina les butaques reservades per la persona introduïda.

    public void eliminarButacaPorPersona() throws ExcepcionFilaIncorrecta, ExcepcionAsientoIncorrecto, ExcepcionPersonaIncorrecta, ExcepcionButacaOcupada, ExcepcionButacaLibre {
        int numFila = introducirFila();
        int numAsiento = introducirAsiento();
        String nomPersona = introducirPersona();

        Butaca butaca = new Butaca(numFila,numAsiento,nomPersona);
        gestionButacas.eliminarButaca(numFila,numAsiento,nomPersona);

    }
}

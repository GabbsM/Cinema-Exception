package clases;

import cinema.exceptions.ExcepcionButacaLibre;
import cinema.exceptions.ExcepcionButacaOcupada;

import java.util.ArrayList;
import java.util.List;

public class GestionButacas {

private ArrayList<Butaca>butacas;

public GestionButacas(){

    butacas = new ArrayList();
}
//TODO: Cerca a l’ArrayList de butaques, la butaca que coincideixi amb les dades rebudes com a paràmetre
//     (fila i seient). Si la troba retornarà la posició de la butaca a l’ArrayList i sinó la troba
//     retornarà -1.

    public int buscarButaca(int numFila,int numAsiento){
        for (int i = 0; i < butacas.size(); i++) {
            Butaca butaca = butacas.get(i);
            if(butaca.getNumFila() == numFila && butaca.getNumAsiento()==numAsiento){
                return i;
            }

        }
        return -1;
    }


    public int buscarButaca(int numFila,int numAsiento,String nomPersona){
        for (int i = 0; i < butacas.size(); i++) {
            Butaca butaca = butacas.get(i);
            if(butaca.getNumFila() == numFila && butaca.getNumAsiento()==numAsiento){
                return i;
            }

        }
        return -1;
    }

//TODO:    //eliminarButaca: serà l’encarregat d’eliminar una butaca de l’ArrayList de butaques.
//    // Rebrà com a paràmetres el número de fila i el seient i l’eliminarà de l’ArrayList.
//    // Si la fila i el seient no coincideixen amb el d’una butaca reservada ( s’utilitzarà el
//    // mètode cercarButaca), llençarà una excepció personalitzada ExcepcioButacaLliure.

    public void eliminarButaca (int numFila,int numAsiento) throws ExcepcionButacaLibre {

        int indiceButaca = buscarButaca(numFila,numAsiento);
        if(indiceButaca == -1){
            throw new ExcepcionButacaLibre("La butaca esta libre");
        }else {
            butacas.remove(indiceButaca);
        }
    }

    public void eliminarButaca (int numFila,int numAsiento,String nomPersona) throws ExcepcionButacaLibre {

        int indiceButaca = buscarButaca(numFila,numAsiento,nomPersona);
        if(indiceButaca == -1){
            throw new ExcepcionButacaLibre("La butaca esta libre");
        }else {
            butacas.remove(indiceButaca);
        }
    }

//TODO:    //afegirButaca: serà l’encarregat d’afegir una butaca al vector de butaques.
//    // Rebrà com a paràmetre un objecte de la classe cine.Butaca i l’afegirà a l’ArrayList.
//    // Si la fila i el seient de la butaca rebuda com a paràmetre coincideixen amb el d’una butaca
//    // que ja es trobi a l’ArrayList (s’utilitzarà el mètode cercarButaca), es llençarà l’excepció
//    // personalitzada ExcepcioButacaOcupada.

    public void agregarButaca(Butaca butaca) throws ExcepcionButacaOcupada {
        int indiceButaca = buscarButaca(butaca.getNumFila(),butaca.getNumAsiento() );
        if(indiceButaca == -1){
            butacas.add(butaca);
        }else {
            throw new ExcepcionButacaOcupada("La butaca esta ocupada");
        }

    }
//TODO: getButaques: Retornarà l’atribut butaques.

    public List<Butaca> getButacas(){
        return butacas;
    }

}

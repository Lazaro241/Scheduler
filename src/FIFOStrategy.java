import java.util.LinkedList;
import java.util.Queue;

public class FIFOStrategy implements ReemplazoPaginaStrategy {
    private final Queue<Integer> ordenDeCarga = new LinkedList<>(); //Cola que guarda las posiciones en RAM de cada pagina en base a orden de carga
    @Override
    public int encontrarPosicionReemplazo(RAM ram) {
        return ordenDeCarga.poll(); //Devuelve la posicion en memoria que contenga la pagina mas antigua
    }

    @Override
    public void notificar(Proceso proceso){
        ordenDeCarga.add(proceso.getPosicionEnRam()); //Añade al final de la cola la ID de la pagina que se ha añadido a RAM
    }
}

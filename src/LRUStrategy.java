import java.util.HashMap;

public class LRUStrategy implements ReemplazoPaginaStrategy{
    private HashMap<Integer, Long> tiempoDeUso = new HashMap<>(); //Mapa que relacionara la posicion de RAM y el tiempo de ultimo acceso de la misma

    @Override
    public int encontrarPosicionReemplazo(RAM ram) {
        int celdaLRU = -1;
        Long tiempomin = Long.MAX_VALUE; //Se le asigna el valor mas alto posible al tiempo minimo inicialmente para asegurarse que cualquier tiempo de las celdas sea menor

        for(Proceso p : ram.getCeldas()){ //Se revisan todas las posiciones de memoria
            if(p!=null){
                int celdaActual = p.getPosicionEnRam();
                Long tiempo = tiempoDeUso.getOrDefault(celdaActual, Long.MAX_VALUE); //Se asigna un valor de tiempo a la variable, puede ser el de la celda actual, o en el caso de que no se tenga el valor maximo posible

                if(tiempomin>tiempo){ //Se pregunta si el tiempo de acceso minimo que se tiene sea mas antiguo que el tiempo de la celda actual
                    tiempomin = tiempo; //Al ser mas antiguo, el tiempo de la celda actual se convierte en el minimo
                    celdaLRU = celdaActual; //La posicion que se enviara para reemplazar se actualiza a la del nuevo tiempo mas antiguo de uso
                }
            }
        }

        return celdaLRU; //Se devuelve la posicion con la pagina menos recientemente usada
    }

    @Override
    public void notificar(Proceso proceso) {
        tiempoDeUso.put(proceso.getPosicionEnRam(), System.nanoTime()); //Se relaciona en el mapa Hash la posicion de RAM con el tiempo del ultimo acceso 
    }
}

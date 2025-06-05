public class ClockStrategy implements ReemplazoPaginaStrategy{
    private int puntero = 0; //Apunta para la ultima posicion de RAM que se ha revisado en el ciclo para reemplazar

     @Override
    public int encontrarPosicionReemplazo(RAM ram) {
        Proceso[] celdas = ram.getCeldas(); //Guarda el arreglo que representa las posiciones de memoria de la RAM
        int n = celdas.length; //Guarda el tamaño de la RAM, el numero de celdas que tiene

        while (true) { //Continua el ciclo hasta que encuentra una posicion para reemplazar
            Proceso actual = celdas[puntero]; //Se guarda la pagina actual

            if (actual.isBitUso()) { //Se pregunta si el bit de uso de la pagina actual es verdadero (1)
                actual.setBitUso(false); //Si lo es, se pasa a falso (0)
                puntero = (puntero + 1) % n; //El puntero apunta a la siguiente posicion de la RAM, si se pasa de la ultima posicion posible vuelve a 0
            } else {
                int reemplazado = puntero; //Si no, la pagina no tiene segunda oportunidad, por lo que se guarda la posicion como la que sera reemplazada
                puntero = (puntero + 1) % n;
                return reemplazado; //Devuelve la posicion de la pagina que se reemplazara
            }
        }
    }

    @Override
    public void notificar(Proceso proceso) {
        // No necesita notificar nada
    }
}

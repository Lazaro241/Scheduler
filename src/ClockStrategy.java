public class ClockStrategy implements ReemplazoPaginaStrategy{
    private int puntero = 0;

     @Override
    public int encontrarPosicionReemplazo(RAM ram) {
        Proceso[] celdas = ram.getCeldas();
        int n = celdas.length;

        while (true) {
            Proceso actual = celdas[puntero];

            if (actual.isBitUso()) {
                actual.setBitUso(false);
                puntero = (puntero + 1) % n;
            } else {
                int reemplazado = puntero;
                puntero = (puntero + 1) % n;
                return reemplazado;
            }
        }
    }

    @Override
    public void notificar(Proceso proceso) {
        // No necesita notificar nada
    }
}

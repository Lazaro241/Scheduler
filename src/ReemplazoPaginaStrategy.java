public interface ReemplazoPaginaStrategy {
    public int encontrarPosicionReemplazo(RAM ram);
    public void notificar(Proceso proceso);
}

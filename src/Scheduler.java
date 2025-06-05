public class Scheduler {
    private static RAM ram;
    private ReemplazoPaginaStrategy algoritmo;
    private int aciertos = 0;
    private int fallos = 0;

    public Scheduler(ReemplazoPaginaStrategy algoritmo, RAM ram){
        this.algoritmo = algoritmo;
        Scheduler.ram = ram;
    }

    public void setEstrategia(ReemplazoPaginaStrategy algoritmo){
        this.algoritmo = algoritmo;
    }

    public void cargarPagina(int idPagina){

        Proceso pagina = new Proceso(idPagina);

        if(ram.contienePagina(idPagina)){
            System.out.println("Pagina "+idPagina+" ya está en RAM.");
            aciertos++;
            if(algoritmo.getClass()==LRUStrategy.class){
                algoritmo.notificar(pagina);
            }
            return;
        }

        fallos++;

        int libre = ram.buscarIndiceLibre();

        if(libre!=-1){
            ram.cargarProceso(libre, pagina);
            pagina.setPosicionEnRam(libre);
            System.out.println("Proceso "+pagina.getId()+" cargado correctamente en posicion "+ libre);
        } else {
            int reemplazo = algoritmo.encontrarPosicionReemplazo(ram);
            ram.cargarProceso(reemplazo, pagina);
            pagina.setPosicionEnRam(reemplazo);
            System.out.println("Proceso "+pagina.getId()+" cargado correctamente en posicion "+ reemplazo);
        }
        algoritmo.notificar(pagina);
    }

    public int getAciertos(){
        return this.aciertos;
    }

    public int getFallos(){
        return this.fallos;
    }
}

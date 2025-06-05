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
        this.algoritmo = algoritmo; //Cambia algoritmo de reemplazo
    }

    public void cargarPagina(int idPagina){

        Proceso pagina = new Proceso(idPagina); //Crea la pagina que se almacenara en memoria

        if(ram.contienePagina(idPagina)){ //Revisa si la pagina que se solicita no se encuentra ya en memoria
            System.out.println("Pagina "+idPagina+" ya está en RAM.");
            aciertos++; //Si esta, se cuenta un acierto de pagina
            if(algoritmo.getClass()==LRUStrategy.class){ //Comprueba si el algoritmo utilizado es LRU
                algoritmo.notificar(pagina); //Si es, tiene que actualizar el ultimo uso de la pagina
            }
            return;
        }

        fallos++; //Se suma un fallo de pagina

        int libre = ram.buscarIndiceLibre(); //Solicita un espacio de memoria que se encuentre libre, devolvera -1 si no hay

        if(libre!=-1){ //Pregunta si se encontro un espacio libre
            ram.cargarProceso(libre, pagina); //Si es que si, se carga en la posicion libre
            pagina.setPosicionEnRam(libre); // Se guarda en la pagina su posicion
            System.out.println("Proceso "+pagina.getId()+" cargado correctamente en posicion "+ libre);
        } else {
            int reemplazo = algoritmo.encontrarPosicionReemplazo(ram); //Si no, crea una variable para guardar la posicion de reemplazo, y ejecuta el algoritmo de reemplazo
            ram.cargarProceso(reemplazo, pagina); //Carga la pagina en la posicion de reemplazo encontrada
            pagina.setPosicionEnRam(reemplazo);
            System.out.println("Proceso "+pagina.getId()+" cargado correctamente en posicion "+ reemplazo);
        }
        algoritmo.notificar(pagina); //Dependiendo del algoritmo, se va a notificar la carga para tener en cuenta en proximos fallos
    }

    public int getAciertos(){
        return this.aciertos; //Devuelve los aciertos de pagina
    }

    public int getFallos(){
        return this.fallos; //Devuelve los fallos de pagina
    }
}

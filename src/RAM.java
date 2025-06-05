public class RAM {
    private final Proceso[] celdas;

    public RAM(int capacidad){
        this.celdas = new Proceso[capacidad];
    }

    public Proceso[] getCeldas(){
        return celdas;
    }

    public void cargarProceso(int posicion, Proceso proceso){
        this.celdas[posicion] = proceso; //Almacena una pagina en el espacio indicado
    }

    public boolean contienePagina(int idPagina){
        for(Proceso p : celdas){ //Revisa todos los espacios para paginas
            if((p!=null)&&p.getId()==idPagina){
                return true; //Si la encuentra, devuelve true
            }
        }
        return false; //Si no, devuelve false
    }

    public int buscarIndiceLibre(){
        for(int i=0;i<celdas.length;i++){ //Revisa toda la capacidad de la RAM
            if(celdas[i]==null){
                return i; //Si encuentra un espacio vacio, devuelve la posicion de ese espacio
            }
        }
        return -1; //Si no, devuelve -1
    }
    
    public void mostrarRAM(){
        System.out.print("RAM: ");
        for (Proceso proceso : celdas) { //Pasa por todos los espacios, mostrando en consola lo que hay en cada espacio
            if(proceso==null){
                System.out.print("[Vacio]");
            } else {
                System.out.print(proceso.toString());
            }
        }
    }
}

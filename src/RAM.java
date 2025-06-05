public class RAM {
    private final Proceso[] celdas;

    public RAM(int capacidad){
        this.celdas = new Proceso[capacidad];
    }

    public Proceso[] getCeldas(){
        return celdas;
    }

    public void cargarProceso(int posicion, Proceso proceso){
        this.celdas[posicion] = proceso;
    }

    public boolean contienePagina(int idPagina){
        for(Proceso p : celdas){
            if((p!=null)&&p.getId()==idPagina){
                return true;
            }
        }
        return false;
    }

    public int buscarIndiceLibre(){
        for(int i=0;i<celdas.length;i++){
            if(celdas[i]==null){
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarRAM(){
        System.out.print("RAM: ");
        for (Proceso proceso : celdas) {
            if(proceso==null){
                System.out.print("[Vacio]");
            } else {
                System.out.print(proceso.toString());
            }
        }
    }
}

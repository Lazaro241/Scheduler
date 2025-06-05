public class Main {
    public static void main(String[] args) throws Exception {
        ReemplazoPaginaStrategy[] algoritmos = {new FIFOStrategy(), new ClockStrategy(), new LRUStrategy()};

        int[] peticiones = {1, 2, 3, 1, 4, 5, 2, 1, 2, 3, 4, 5};

        for (ReemplazoPaginaStrategy algoritmo : algoritmos){
            System.out.println("ALGORITMO: "+algoritmo.getClass());
            System.out.println("+++++++++++++++++++++++++++++++++++");
            RAM nuevaRAM = new RAM(3);
            Scheduler scheduler = new Scheduler(algoritmo, nuevaRAM);
            for (int pagina : peticiones) {
                System.out.println("Petición de página " + pagina);
                scheduler.cargarPagina(pagina);
                nuevaRAM.mostrarRAM();
                System.out.println();
                System.out.println("--------------------------");
            }
            System.out.println("Fallos totales: "+scheduler.getFallos());
            System.out.println("Aciertos totales: "+scheduler.getAciertos()+"\n\n");
        }
        

        
    }
}

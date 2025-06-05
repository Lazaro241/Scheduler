public class Main {
    public static void main(String[] args) throws Exception {
        RAM ram = new RAM(3);
        ReemplazoPaginaStrategy fifo = new FIFOStrategy();
        ReemplazoPaginaStrategy clock = new ClockStrategy();
        ReemplazoPaginaStrategy lru = new LRUStrategy();
        Scheduler scheduler = new Scheduler(lru, ram);

        int[] peticiones = {1, 2, 3, 1, 4, 5, 2, 1, 2, 3, 4, 5};

        for (int pagina : peticiones) {
            System.out.println("Petición de página " + pagina);
            scheduler.cargarPagina(pagina);
            ram.mostrarRAM();
            System.out.println();
            System.out.println("--------------------------");
        }

        System.out.println("Fallos totales: "+scheduler.getFallos());
        System.out.println("Aciertos totales: "+scheduler.getAciertos());
    }
}

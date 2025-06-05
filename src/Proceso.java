public class Proceso {
    private final int id;
    private int posicionEnRam;
    private boolean bitUso;

    public Proceso(int id) {
        this.id = id;
        this.bitUso = true;
    }

    @Override
    public String toString(){
        return "["+this.id+"]";
    }

    public int getId() {
        return id;
    }


    public boolean isBitUso() {
        return bitUso;
    }

    public void setBitUso(boolean bitUso) {
        this.bitUso = bitUso;
    }

    public void setPosicionEnRam(int posicion){
        this.posicionEnRam=posicion;
    }

    public int getPosicionEnRam(){
        return this.posicionEnRam;
    }
    

}

import java.time.LocalDateTime;

public class LocParcare {
    private boolean esteOcupat;
    private String nrInmatriculare;
    private LocalDateTime oraIntrareParcare;
    private int nrLocParcare;


// se creeaza constructorul pentru locul de parcare gol.
    public LocParcare(int nrLoc){
        esteOcupat          = false;
        nrInmatriculare     = null;
        oraIntrareParcare   = null;
        nrLocParcare        = nrLoc;
    }

    // creez constructorul cu parametrii (constructor folosit cand locul de parcare, cand masina ocupa fictiv locul respectiv.
    public LocParcare(String nr, int nrLoc){
        esteOcupat          = true;
        nrInmatriculare     = nr;
        oraIntrareParcare   = LocalDateTime.now();
        nrLocParcare        = nrLoc;
    }

    public boolean isEsteOcupat() {
        return esteOcupat;
    }

    public void setEsteOcupat(boolean esteOcupat) {
        this.esteOcupat = esteOcupat;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public LocalDateTime getOraIntrareParcare() {
        return oraIntrareParcare;
    }

    public void setOraIntrareParcare(LocalDateTime oraIntrareParcare) {
        this.oraIntrareParcare = oraIntrareParcare;
    }

    public int getNrLocParcare() {
        return nrLocParcare;
    }

    public void setNrLocParcare(int nrLocParcare) {
        this.nrLocParcare = nrLocParcare;
    }
}

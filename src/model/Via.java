package model;

public class Via {
    private int llargada, id_dificultat, id_escola, id_sector, id_escalador;
    private String nom, orientacio, estat, tipus_roca, estil;

    public Via(int llargada, int id_dificultat, int id_escalador, int id_escola, int id_sector,
               String nom, String orientacio, String estat, String tipus_roca, String estil){

        this.llargada = llargada;
        this.id_dificultat = id_dificultat;
        this.id_escalador = id_escalador;
        this.id_escola = id_escola;
        this.id_sector = id_sector;
        this.nom = nom;
        this.orientacio = orientacio;
        this.estat = estat;
        this.tipus_roca = tipus_roca;
        this.estil = estil;
    }



    public int getLlargada() {
        return llargada;
    }

    public int getId_dificultat() {
        return id_dificultat;
    }

    public int getId_escola() {
        return id_escola;
    }

    public int getId_sector() {
        return id_sector;
    }

    public int getId_escalador() {
        return id_escalador;
    }

    public String getNom() {
        return nom;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public String getEstat() {
        return estat;
    }

    public String getTipus_roca() {
        return tipus_roca;
    }

    public String getEstil() {
        return estil;
    }


    public void setLlargada(int llargada) {
        this.llargada = llargada;
    }

    public void setId_dificultat(int id_dificultat) {
        this.id_dificultat = id_dificultat;
    }

    public void setId_escola(int id_escola) {
        this.id_escola = id_escola;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
    }

    public void setId_escalador(int id_escalador) {
        this.id_escalador = id_escalador;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOrientacio(String orientacio) {
        this.orientacio = orientacio;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public void setTipus_roca(String tipus_roca) {
        this.tipus_roca = tipus_roca;
    }

    public void setEstil(String estil) {
        this.estil = estil;
    }
}

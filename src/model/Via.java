package model;

public class Via {
    private final String[] TIPUS_ROCA = {"conglomerat", "granit", "calcaria"};
    private final String[] ORIENTACIONS = {"S", "E", "O", "N", "SE", "NE", "NO", "SO"};
    private final String[] ESTATS = {"tancada", "apte", "construccio"};
    private final String[] ESTILS = {"gel", "esportiva", "classica"};

    private int llargada, id_dificultat, id_escola, id_sector, id_escalador;
    private String nom, orientacio, estat, tipus_roca, estil;

    public Via(String nom, int llargada, int id_dificultat, String orientacio, String estat,  int id_escola, int id_sector,
               String tipus_roca, int id_escalador, String estil) throws Exception {

        //verificacions
        if (!verificarDificultat(id_dificultat)){
            throw new Exception("La dificultat de la via ha de ser d'1 entre 19");
        }

        if (!verificarSector(id_sector)){
            throw new Exception("El sector ha de ser d'1 i 3");
        }

        if (!verificar(orientacio, ORIENTACIONS)){
            throw new Exception("La orientacio ha de ser [N, E, O, S, NE, NO, SE, SO]");
        }

        if (!verificar(estat, ESTATS)){
            throw new Exception("L'estat ha de ser [Tancada, Apte, Construccio]");
        }



        if (!verificar(estil, ESTILS)){
            throw new Exception("Els estils ha de ser [Gel, Esportiva, Classica]");
        }

        //assignacions
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

    //verificacions
    private boolean verificarDificultat(int dificultat){
        return dificultat > 0 && dificultat < 19;
    }

    private boolean verificar(String element, String[] array){
        for (int i = 0; i <array.length; i++) {
            if (element.equals(array[i])){
                return true;
            }
        }

        return false;
    }

    private boolean verificarSector(int valor){
        return valor > 0 && valor < 3;
    }
    @Override
    public String toString() {
        return "Nom: " + getNom() + ":\n" +
                "Llargada: " + getLlargada() + "\n" +
                "Dificultat: " + getId_dificultat() + "\n" +
                "Orientació: " + getOrientacio() + "\n" +
                "Estat: " + getEstat() + "\n" +
                "Escola ID: " + getId_escola() + "\n" +
                "Sector ID: " + getId_sector() + "\n" +
                "Tipus de roca: " + getTipus_roca() + "\n" +
                "Estil: " + getEstat() + "\n" +
                "Escalador ID: " + getId_escalador() + "\n";

    }
}

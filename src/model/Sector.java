package model;

public class Sector {
    private int num_vies, id_escola;
    private String nom, acces, dificultat, regulacions;
    private String coordenades;

    private int id;

    public Sector(String nom, String coordenades, String acces, int num_vies, String dificultat, String regulacions, int id_escola) throws Exception {
        if(!verificarCoordenadas(coordenades)){
            throw new Exception("Les coordenades han de ser p.ex: [43, 52]");
        }

        if(!verificarEscola(id_escola)){
            throw new Exception("Les escoles han de estar entre 1 i 3");
        }

        if(!verificarDificultat(dificultat)){
            throw new Exception("La dificultat ha de ser: [alta, mitjana o baixa]");
        }

        this.num_vies = num_vies;
        this.id_escola = id_escola;
        this.nom = nom;
        this.acces = acces;
        this.dificultat = dificultat;
        this.regulacions = regulacions;
        this.coordenades = coordenades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNum_vies() {
        return num_vies;
    }

    public int getId_escola() {
        return id_escola;
    }

    public String getNom() {
        return nom;
    }

    public String getAcces() {
        return acces;
    }

    public String getDificultat() {
        return dificultat;
    }

    public String getRegulacions() {
        return regulacions;
    }

    public String getCoordenades() {
        return coordenades;
    }


    public void setNum_vies(int num_vies) {
        this.num_vies = num_vies;
    }

    public void setId_escola(int id_escola) {
        this.id_escola = id_escola;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    public void setRegulacions(String regulacions) {
        this.regulacions = regulacions;
    }

    public void setCoordenades(String coordenades) {
        this.coordenades = coordenades;
    }

    //verificar coordenadas
    private static boolean verificarCoordenadas(String coordenades){
        return coordenades.matches("^-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?$");

    }

    //verificar dificultad
    private static boolean verificarDificultat(String dificultat){
        String[] dificultats = {"alta", "mitjana", "baixa"};

        for (int i = 0; i < dificultats.length; i++) {
            if (dificultat.equals(dificultats[i])) return true;
        }

        return false;
    }

    //verificar escola
    private static boolean verificarEscola(int id_escola){
        return id_escola > 0 && id_escola <= 3; //Acceder a la base de datos y coger el length de la tabla mas adelante
    }

    @Override
    public String toString() {
        return "Sector " + getNom() + ":\n" +
                "Coordenades: " + getCoordenades() + " \n" +
                "Accés: " + getAcces() + " \n" +
                "Numero de vies: " + getNum_vies() + " \n" +
                "Dificultat: " + getDificultat() + " \n" +
                "Regulacions: " + getRegulacions() + " \n" +
                "id_escola: " + getId_escola();
    }

}

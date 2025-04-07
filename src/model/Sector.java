package model;

public class Sector {
    private int num_vies, id_escola;
    private String nom, acces, dificultat, regulacions;
    private float coordenades;

    public Sector(int num_vies, int id_escola,
                  String nom, String acces, String dificultat, String regulacions,
                  float coordenades) {

        this.num_vies = num_vies;
        this.id_escola = id_escola;
        this.nom = nom;
        this.acces = acces;
        this.dificultat = dificultat;
        this.regulacions = regulacions;
        this.coordenades = coordenades;
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

    public float getCoordenades() {
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

    public void setCoordenades(float coordenades) {
        this.coordenades = coordenades;
    }
}

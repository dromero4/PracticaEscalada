package model;

public class Escola {
    private int id, num_vies;
    private String nom, poblacio, acces, dificultat, regulacions;

    public Escola(int id, int num_vies, String nom, String poblacio, String acces, String dificultat, String regulacions){
        this.id = id;
        this.num_vies = num_vies;
        this.nom = nom;
        this.acces = acces;
        this.poblacio = poblacio;
        this.dificultat = dificultat;
        this.regulacions = regulacions;
    }

    public int getId() {
        return id;
    }

    public int getNum_vies() {
        return num_vies;
    }

    public String getNom() {
        return nom;
    }

    public String getPoblacio() {
        return poblacio;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNum_vies(int num_vies) {
        this.num_vies = num_vies;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
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
}

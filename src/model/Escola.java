package model;

public class Escola {
    private int num_vies;
    private String nom, poblacio, acces, dificultat, regulacions;

    public Escola(String nom, String poblacio, String acces, int num_vies, String dificultat, String regulacions) throws Exception {
        if(!verificarDificultat(dificultat)){
            throw new Exception("El valor de dificultat ha de ser: [baixa, mitjana, alta]");
        }
        this.num_vies = num_vies;
        this.nom = nom;
        this.acces = acces;
        this.poblacio = poblacio;
        this.dificultat = dificultat;
        this.regulacions = regulacions;
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

    //verificacions

    private boolean verificarDificultat(String dificultat){
        String[] dificultats = {"baixa", "mitjana", "alta"};

        for (int i = 0; i < dificultats.length; i++) {
            if (dificultat.equals(dificultats[i])){
                return true;
            }
        }

        return false;
    }
}

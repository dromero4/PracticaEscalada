package model;

public class Candidat {
    private Long id;
    private String nom;
    private int edat;

    public Candidat(Long id, String nom, int edat) {
        this.id = id;
        this.nom = nom;
        this.edat = edat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    @Override
    public String toString() {
        return "Candidat [id=" + id + ", nom=" + nom + ", edat=" + edat + "]";
    }
}

package model;

public class Dificultat {
    private int id;
    private float valoracio;
    private String grau;

    public Dificultat(int ind, String grau, float valoracio){
        this.id = id;
        this.grau = grau;
        this.valoracio = valoracio;
    }

    public int getId() {
        return id;
    }

    public float getValoracio() {
        return valoracio;
    }

    public String getGrau() {
        return grau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValoracio(float valoracio) {
        this.valoracio = valoracio;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }
}

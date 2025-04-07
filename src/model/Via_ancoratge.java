package model;

public class Via_ancoratge {
    private int id_via, id_ancoratge;

    public Via_ancoratge(int id_via, int id_ancoratge){
        this.id_via = id_via;
        this.id_ancoratge = id_ancoratge;
    }

    public int getId_via() {
        return id_via;
    }

    public void setId_via(int id_via) {
        this.id_via = id_via;
    }

    public int getId_ancoratge() {
        return id_ancoratge;
    }

    public void setId_ancoratge(int id_ancoratge) {
        this.id_ancoratge = id_ancoratge;
    }


}

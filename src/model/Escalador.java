package model;

public class Escalador {
    private int edat;
    private String nom, nick, nivell, via_favorita, estil;

    public Escalador( int edat, String nom, String nick, String nivell, String via_favorita, String estil){
        this.edat = edat;
        this.nom = nom;
        this.nick = nick;
        this.nivell = nivell;
        this.via_favorita = via_favorita;
        this.estil = estil;
    }


    public int getEdat() {
        return edat;
    }

    public String getNom() {
        return nom;
    }

    public String getNick() {
        return nick;
    }

    public String getNivell() {
        return nivell;
    }

    public String getVia_favorita() {
        return via_favorita;
    }

    public String getEstil() {
        return estil;
    }


    public void setEdat(int edat) {
        this.edat = edat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public void setVia_favorita(String via_favorita) {
        this.via_favorita = via_favorita;
    }

    public void setEstil(String estil) {
        this.estil = estil;
    }
}

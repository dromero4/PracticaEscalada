package model;

public class Escalador {
    private int edat;
    private String nom, nick, nivell, via_favorita, estil;

    private int id;

    public Escalador(String nom, String nick, int edat, String nivell, String via_favorita, String estil) throws Exception {
        if(!verificarEstil(estil))  throw new Exception("L'estil ha de ser: [esportiva, gel, classica]");
        if(!verificarNivell(nivell)) throw new Exception("Nivells disponibles: \"4\",\"4+\",\"5\",\"5+\",\"6a\",\"6a+\",\"6b\",\"6b+\",\"6c\",\"6c+\",\"7a\",\"7a+\",\"7b\",\"7b+\",\"7c\",\"7c+\",\"8a\",\"8a+\",\"8b\"");

        this.edat = edat;
        this.nom = nom;
        this.nick = nick;
        this.nivell = nivell;
        this.via_favorita = via_favorita;
        this.estil = estil;
    }

    //Primary key
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    //verificar estil
    //esportiva, gel, classica
    private static boolean verificarEstil(String estil){
        String[] estils = {"esportiva", "gel", "classica", "clàssica"};

        for (int i = 0; i < estils.length; i++) {
            if (estil.toLowerCase().equals(estils[i])) return true;
        }

        return false;
    }

    //verificar nivell
    //4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b
    private static boolean verificarNivell(String nivell){
        String[] nivells = {"4","4+","5","5+","6a","6a+","6b","6b+","6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b"};

        for (int i = 0; i < nivells.length; i++) {
            if (nivell.equals(nivells[i])) return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Nom: " + getNom() + ":\n" +
                "Nick: " + getNick() + "\n" +
                "Edat: " + getEdat() + "\n" +
                "Nivell: " + getNivell() + "\n" +
                "Via favorita: " + getVia_favorita() + "\n" +
                "Estil: " + getEstil() + "\n" ;

    }
}

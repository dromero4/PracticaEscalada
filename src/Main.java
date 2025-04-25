import dao.mysql.*;
import model.Escalador;
import model.Escola;
import model.Sector;
import model.Via;
import view.View;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    static Dotenv dotenv = Dotenv.load();

    static String url = dotenv.get("DB_URL");
    static String user =  dotenv.get("DB_USER");
    static String password = dotenv.get("DB_PASSWORD");

    static Connection connection = Con.openConnection(url, user, password);
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        int opcio;
        do {
            View.showMenu();
            opcio = scan.nextInt();
            switch (opcio){
                case 1: //Casos predefinits
                    View.showCasosPredefinits();
                    int opcioCasos = scan.nextInt();
                    scan.nextLine();
                    switch (opcioCasos){
                        case 1 -> viesDisponibles();
                        case 2 -> viesPerRang();
                        case 3 -> viesPerEstat();
                        case 4 -> escolesAmbRestriccions();
                        //case 5 -> sectorsVies();
                        //case 6 -> escaladorsMaxNivell();
                        //case 7 -> viesAptesRecentment();
                        //case 8 -> viesMesLlarguesEscola();
                    }
                    break;
                case 2: //Escoles
                    View.showMenuCRUD("Escola");
                    int opcioCRUD_Escola = scan.nextInt();
                    scan.nextLine();
                    switch (opcioCRUD_Escola){
                        case 1 -> afegirEscola();
                        case 2 -> modificarEscola();
                        case 3 -> mostrar_1_Escola();
                        case 4 -> mostrarEscola();
                        case 5 -> eliminar_1_escola();
                    }
                    break;
                case 3:
                    View.showMenuCRUD("Vies");
                    int opcioCRUD_Via = scan.nextInt();
                    scan.nextLine();
                    switch (opcioCRUD_Via){
                        case 1 -> afegirVia();
                        case 2 -> modificarVia();
                        case 3 -> mostrar_1_Via();
                        case 4 -> mostrarVia();
                        case 5 -> eliminar_1_via();
                    }
                    break;
                case 4:
                    View.showMenuCRUD("Sector");
                    int opcioCRUD_Sector = scan.nextInt();
                    scan.nextLine();

                    switch (opcioCRUD_Sector){
                        case 1 -> afegirSector();
                        case 2 -> modificarSector();
                        case 3 -> mostrar_1_Sector();
                        case 4 -> mostrarSector();
                        case 5 -> eliminar_1_sector();
                    }
                    break;
                case 5:
                    View.showMenuCRUD("Escalador");
                    int opcioCRUD_Escalador = scan.nextInt();
                    scan.nextLine();

                    switch (opcioCRUD_Escalador){
                        case 1 -> afegirEscalador();
                        case 2 -> modificarEscalador();
                        case 3 -> mostrar_1_Escalador();
                        case 4 -> mostrarEscalador();
                        case 5 -> eliminar_1_escalador();
                    }
                    break;
            }
        } while (opcio != 0);

    }

    //Funcions per inserir
    private static void afegirEscola(){

        System.out.print("Nom: ");
        String nom = scan.nextLine();

        System.out.print("Numero de vies: ");
        int num_vies = scan.nextInt();
        scan.nextLine();

        System.out.print("Població: ");
        String poblacio = scan.nextLine();

        System.out.print("Accés: ");
        String acces = scan.nextLine();

        System.out.print("Dificultat: ");
        String dificultat = scan.next();
        scan.nextLine();

        System.out.print("Regulacions: (Deixar en blanc si no hi ha cap)");
        String regulacions = scan.nextLine();



        try {
            Escola escola = new Escola(nom, poblacio, acces, num_vies, dificultat, regulacions);

            EscolaDAO escolaDAO = new EscolaDAO(connection);
            escolaDAO.inserir(escola);

            View.successMessage();
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void afegirVia(){
        System.out.print("Nom: ");
        String nom = scan.nextLine();

        System.out.print("Llargada ");
        int llargada = scan.nextInt();
        scan.nextLine();

        System.out.print("Dificultat: [1-19]");
        int dificultat = scan.nextInt();
        scan.nextLine();

        System.out.print("Orientació: [ex: SO]");
        String orientacio = scan.next();


        System.out.print("Estat: [tancada, Apte, construncció]");
        String estat = scan.next();

        System.out.print("Escola: [ID] ");
        int escola = scan.nextInt();

        System.out.print("Sector: [ID] ");
        int sector = scan.nextInt();
        scan.nextLine();

        System.out.print("Tipus de roca: [conglomerat, granit, calcaria] ");
        String roca = scan.nextLine();

        System.out.print("Escalador. [ID]");
        int escalador = scan.nextInt();
        scan.nextLine();

        System.out.print("Estil: [gel, esportiva, classica]");
        String estil = scan.nextLine();


        try {
            Via via = new Via(nom, llargada, dificultat, orientacio, estat, escola, sector, roca, escalador, estil);

            ViaDAO ViaDAO = new ViaDAO(connection);
            ViaDAO.inserir(via);

            View.successMessage();
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void afegirSector(){
        System.out.print("Nom: ");
        String nom = scan.nextLine();

        System.out.print("Coordenades: [X, Y] "); //verificar
        String coordenades = scan.nextLine();

        scan.nextLine();

        System.out.print("Accés: ");
        String acces = scan.nextLine();

        System.out.print("Numero de vies: ");
        int num_vies = scan.nextInt();
        scan.nextLine();

        System.out.print("Dificultat: [Baixa, mitjana, alta]"); //verificar
        String dificultat = scan.next();
        scan.nextLine();

        System.out.print("Regulacions: ");
        String regulacions = scan.nextLine();

        System.out.print("Escola: [ID] "); //verificar
        int escola = scan.nextInt();
        scan.nextLine();


        try {
            Sector sector = new Sector(nom, coordenades, acces, num_vies, dificultat, regulacions, escola);


            SectorDAO SectorDAO = new SectorDAO(connection);
            SectorDAO.inserir(sector);

            View.successMessage();
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void afegirEscalador() {
        System.out.print("Nom: ");
        String nom = scan.nextLine();

        System.out.print("Nick: ");
        String nick = scan.nextLine();

        System.out.print("Edat: ");
        int edat = scan.nextInt();
        scan.nextLine();

        System.out.print("Nivell: "); //verificar
        String nivell = scan.next();
        scan.nextLine();

        System.out.print("Via favorita: ");
        String via_favorita = scan.nextLine();

        System.out.print("Estil: "); //verificar
        String estil = scan.next();
        scan.nextLine();

        try {
            Escalador escalador = new Escalador(nom, nick, edat, nivell, via_favorita, estil);

            EscaladorDAO escaladorDAO = new EscaladorDAO(connection);
            escaladorDAO.inserir(escalador);

            View.successMessage();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    //Funcions per modificar
    private static void modificarEscola() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Introdueix el nom de l'escola a modificar: ");
            String nomInput = scan.nextLine();

            EscolaDAO escolaDAO = new EscolaDAO(connection);
            int id = escolaDAO.obtenirID(nomInput); // Obtenim l'ID a partir del nom

            Escola escolaExist = escolaDAO.obtenir(id); // Obtenim l'objecte complet

            if (escolaExist == null) {
                System.out.println("No s'ha trobat cap escola amb aquest nom.");
                return;
            }

            escolaExist.setId(id); // Per si de cas no estava ja assignat

            System.out.println("Deixa en blanc si no vols modificar el camp.");

            System.out.print("Nom actual (" + escolaExist.getNom() + "): ");
            String nouNom = scan.nextLine();
            if (!nouNom.isEmpty()) escolaExist.setNom(nouNom);

            System.out.print("Població actual (" + escolaExist.getPoblacio() + "): ");
            String novaPoblacio = scan.nextLine();
            if (!novaPoblacio.isEmpty()) escolaExist.setPoblacio(novaPoblacio);

            System.out.print("Accés actual (" + escolaExist.getAcces() + "): ");
            String nouAcces = scan.nextLine();
            if (!nouAcces.isEmpty()) escolaExist.setAcces(nouAcces);

            System.out.print("Nombre de vies actual (" + escolaExist.getNum_vies() + "): ");
            String numViesInput = scan.nextLine();
            if (!numViesInput.isEmpty()) escolaExist.setNum_vies(Integer.parseInt(numViesInput));

            System.out.print("Dificultat actual (" + escolaExist.getDificultat() + "): ");
            String novaDificultat = scan.nextLine();
            if (!novaDificultat.isEmpty()) escolaExist.setDificultat(novaDificultat);

            System.out.print("Regulacions actuals (" + escolaExist.getRegulacions() + "): ");
            String novesRegulacions = scan.nextLine();
            if (!novesRegulacions.isEmpty()) escolaExist.setRegulacions(novesRegulacions);

            // Cridem al DAO per fer el update
            escolaDAO.modificar(escolaExist);

            View.successMessage();
        } catch (Exception e) {
            System.out.println("Error al modificar l'escola: " + e.getMessage());
        }
    }
    private static void modificarVia(){
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Introdueix el nom de la via a modificar: ");
            String nomInput = scan.nextLine();

            ViaDAO viaDAO = new ViaDAO(connection);
            int id = viaDAO.obtenirID(nomInput); // Obtenim l'ID a partir del nom

            Via viaExists = viaDAO.obtenir(id); // Obtenim l'objecte complet

            if (viaExists == null) {
                System.out.println("No s'ha trobat cap escola amb aquest nom.");
                return;
            }

            viaExists.setId(id); // Per si de cas no estava ja assignat

            System.out.println("Deixa en blanc si no vols modificar el camp.");

            System.out.print("Nom actual (" + viaExists.getNom() + "): ");
            String nouNom = scan.nextLine();
            if (!nouNom.isEmpty()) viaExists.setNom(nouNom);

            System.out.print("Llargada actual (" + viaExists.getLlargada() + "): ");
            String novaLlargada = scan.nextLine();
            if (!novaLlargada.isEmpty()) viaExists.setLlargada(Integer.parseInt(novaLlargada));

            System.out.print("Dificultat actual (" + viaExists.getId_dificultat() + "): ");
            String novaDificultat = scan.nextLine();
            if (!novaDificultat.isEmpty()) viaExists.setId_dificultat(Integer.parseInt(novaDificultat));

            System.out.print("Orientació actual (" + viaExists.getOrientacio() + "): ");
            String novaOrientacio = scan.nextLine();
            if (!novaOrientacio.isEmpty()) viaExists.setOrientacio(novaOrientacio);

            System.out.print("Estat actual (" + viaExists.getEstat() + "): ");
            String nouEstat = scan.nextLine();
            if (!nouEstat.isEmpty()) viaExists.setEstat(nouEstat);

            System.out.print("Escola ID actual (" + viaExists.getId_escola() + "): ");
            String novaEscola = scan.nextLine();
            if (!novaEscola.isEmpty()) viaExists.setId_escola(Integer.parseInt(novaEscola));

            System.out.print("Sector ID actual (" + viaExists.getId_sector() + "): ");
            String nouSector = scan.nextLine();
            if (!nouSector.isEmpty()) viaExists.setId_sector(Integer.parseInt(nouSector));

            System.out.print("Tipus de roca actual (" + viaExists.getTipus_roca() + "): ");
            String novaRoca = scan.nextLine();
            if (!novaRoca.isEmpty()) viaExists.setTipus_roca(novaRoca);

            System.out.print("Escalador ID actual (" + viaExists.getId_dificultat() + "): ");
            String nouEscalador = scan.nextLine();
            if (!nouEscalador.isEmpty()) viaExists.setId_escalador(Integer.parseInt(nouEscalador));

            System.out.print("Estil actual (" + viaExists.getEstil() + "): ");
            String nouEstil = scan.nextLine();
            if (!nouEstil.isEmpty()) viaExists.setEstil(nouEstil);

            // Cridem al DAO per fer el update
            viaDAO.modificar(viaExists);

            View.successMessage();
        } catch (Exception e) {
            System.out.println("Error al modificar la via: " + e.getMessage());
        }
    }
    private static void modificarSector(){
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Introdueix el nom del sector a modificar: ");
            String nomInput = scan.nextLine();

            SectorDAO sectorDAO = new SectorDAO(connection);
            int id = sectorDAO.obtenirID(nomInput); // Obtenim l'ID a partir del nom

            Sector sectorExists = sectorDAO.obtenir(id); // Obtenim l'objecte complet

            if (sectorExists == null) {
                System.out.println("No s'ha trobat cap escola amb aquest nom.");
                return;
            }

            sectorExists.setId(id); // Per si de cas no estava ja assignat

            System.out.println("Deixa en blanc si no vols modificar el camp.");

            System.out.print("Nom actual (" + sectorExists.getNom() + "): ");
            String nouNom = scan.nextLine();
            if (!nouNom.isEmpty()) sectorExists.setNom(nouNom);

            System.out.print("Coordenades actuals (" + sectorExists.getCoordenades() + "): ");
            String novesCoordenades = scan.nextLine();
            if (!novesCoordenades.isEmpty()) sectorExists.setCoordenades(novesCoordenades);

            System.out.print("Accés actual (" + sectorExists.getAcces() + "): ");
            String nouAcces = scan.nextLine();
            if (!nouAcces.isEmpty()) sectorExists.setAcces(nouAcces);

            System.out.print("Nombre de vies actual (" + sectorExists.getNum_vies() + "): ");
            String numViesInput = scan.nextLine();
            if (!numViesInput.isEmpty()) sectorExists.setNum_vies(Integer.parseInt(numViesInput));

            System.out.print("Dificultat actual (" + sectorExists.getDificultat() + "): ");
            String novaDificultat = scan.nextLine();
            if (!novaDificultat.isEmpty()) sectorExists.setDificultat(novaDificultat);

            System.out.print("Regulacions actuals (" + sectorExists.getRegulacions() + "): ");
            String novesRegulacions = scan.nextLine();
            if (!novesRegulacions.isEmpty()) sectorExists.setRegulacions(novesRegulacions);

            System.out.print("Escola ID actual (" + sectorExists.getId_escola() + "): ");
            String novaEscola = scan.nextLine();
            if (!novaEscola.isEmpty()) sectorExists.setRegulacions(novaEscola);

            // Cridem al DAO per fer el update
            sectorDAO.modificar(sectorExists);

            View.successMessage();
        } catch (Exception e) {
            System.out.println("Error al modificar el sector: " + e.getMessage());
        }
    }
    private static void modificarEscalador(){
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Introdueix el nick de l'escalador a modificar: ");
            String nomInput = scan.nextLine();

            EscaladorDAO escaladorDAO = new EscaladorDAO(connection);
            int id = escaladorDAO.obtenirID(nomInput); // Obtenim l'ID a partir del nom

            Escalador escaladorExists = escaladorDAO.obtenir(id); // Obtenim l'objecte complet

            if (escaladorExists == null) {
                System.out.println("No s'ha trobat cap escalador amb aquest nick.");
                return;
            }

            escaladorExists.setId(id); // Per si de cas no estava ja assignat

            System.out.println("Deixa en blanc si no vols modificar el camp.");

            System.out.print("Nom actual (" + escaladorExists.getNom() + "): ");
            String nouNom = scan.nextLine();
            if (!nouNom.isEmpty()) escaladorExists.setNom(nouNom);

            System.out.print("Nick actual (" + escaladorExists.getNick() + "): ");
            String nouNick = scan.nextLine();
            if (!nouNick.isEmpty()) escaladorExists.setNick(nouNick);

            System.out.print("Edat actual (" + escaladorExists.getEdat() + "): ");
            String novaEdat = scan.nextLine();
            if (!novaEdat.isEmpty()) escaladorExists.setEdat(Integer.parseInt(novaEdat));

            System.out.print("Nivell actual (" + escaladorExists.getNivell() + "): ");
            String nouNivell = scan.nextLine();
            if (!nouNivell.isEmpty()) escaladorExists.setNivell(nouNivell);

            System.out.print("Via favorita actual (" + escaladorExists.getVia_favorita() + "): ");
            String novaVia = scan.nextLine();
            if (!novaVia.isEmpty()) escaladorExists.setVia_favorita(novaVia);

            System.out.print("Estil actual (" + escaladorExists.getEstil() + "): ");
            String nouEstil = scan.nextLine();
            if (!nouEstil.isEmpty()) escaladorExists.setEstil(nouEstil);

            // Cridem al DAO per fer el update
            escaladorDAO.modificar(escaladorExists);

            View.successMessage();
        } catch (Exception e) {
            System.out.println("Error al modificar l'escalador: " + e.getMessage());
        }
    }

    //Funcions per mostrar 1
    private static void mostrar_1_Escola(){
        //Primero necesitamos el nombre de la escuela
        System.out.print("Quina escola vols mostrar? ");
        String nom_escola = scan.nextLine();

        if (nom_escola.isEmpty()) {
            System.out.println("El nom de l'escola no pot estar buit.");
            return;  // Salir si el nombre está vacío
        }

        //Luego necesitamos el ID
        try{
            EscolaDAO escolaDAO = new EscolaDAO(connection);
            int id = escolaDAO.obtenirID(nom_escola);

            Escola escola = escolaDAO.obtenir(id); // <---

            System.out.println(escola);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private static void mostrar_1_Via(){
        System.out.print("Quina via vols mostrar? ");
        String nom_via = scan.nextLine();

        if (nom_via.isEmpty()){
            System.out.println("El nom de la via no pot ser buit");
            return;
        };

        try {
            ViaDAO viaDAO = new ViaDAO(connection);

            int id = viaDAO.obtenirID(nom_via);

            Via via = viaDAO.obtenir(id);
            System.out.println(via);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void mostrar_1_Sector(){
        System.out.print("Quin sector vols mostrar? ");
        String nom_sector = scan.nextLine();

        if (nom_sector.isEmpty()){
            System.out.println("El nom del sector no pot ser buit");
            return;
        };

        try {
            SectorDAO sectorDAO = new SectorDAO(connection);

            int id = sectorDAO.obtenirID(nom_sector);

            System.out.println("ID: " + id);

            Sector sector = sectorDAO.obtenir(id);
            System.out.println(sector);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void mostrar_1_Escalador(){
        System.out.print("Quin escalador vols mostrar? ");
        String nick_escalador = scan.nextLine();

        if (nick_escalador.isEmpty()){
            System.out.println("El nom de l'escalador no pot ser buit");
            return;
        };

        try {
            EscaladorDAO escalador = new EscaladorDAO(connection);

            int id = escalador.obtenirID(nick_escalador);

            Escalador escalador_1 = escalador.obtenir(id);
            System.out.println(escalador_1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Funcions per eliminar 1
    private static void eliminar_1_escola() {
        System.out.print("Nom de l'escola a eliminar: ");
        String nomEscola = scan.nextLine();

        try {
            EscolaDAO escolaDAO = new EscolaDAO(connection);
            int id = escolaDAO.obtenirID(nomEscola);
            Escola escola = escolaDAO.obtenir(id);
            escola.setId(id);
            escolaDAO.eliminar(escola);
            View.successMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void eliminar_1_via() {
        System.out.print("Nom de la via a eliminar: ");
        String nomVia = scan.nextLine();

        try {
            ViaDAO viaDAO = new ViaDAO(connection);
            int id = viaDAO.obtenirID(nomVia);
            Via via = viaDAO.obtenir(id);
            via.setId(id);
            viaDAO.eliminar(via);
            View.successMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void eliminar_1_sector() {
        System.out.print("Nom del sector a eliminar: ");
        String nomEscola = scan.nextLine();

        try {
            SectorDAO sectorDAO = new SectorDAO(connection);
            int id = sectorDAO.obtenirID(nomEscola);
            Sector sector = sectorDAO.obtenir(id);
            sector.setId(id);
            sectorDAO.eliminar(sector);
            View.successMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void eliminar_1_escalador() {
        System.out.print("Nick de l'escalador a eliminar: ");
        String nickEscalador = scan.nextLine();

        try {
            EscaladorDAO escaladorDAO = new EscaladorDAO(connection);
            int id = escaladorDAO.obtenirID(nickEscalador);
            Escalador escalador = escaladorDAO.obtenir(id);
            escalador.setId(id);
            escaladorDAO.eliminar(escalador);
            View.successMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Funcions per mostrar tot
    private static void mostrarEscola(){
        try {
            EscolaDAO escolaDAO = new EscolaDAO(connection);
            List<Escola> escoles = escolaDAO.obtenirTots();

            escoles.forEach(System.out::println);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void mostrarVia(){
        try {
            ViaDAO viaDAO = new ViaDAO(connection);
            List<Via> vies = viaDAO.obtenirTots();

            vies.forEach(System.out::println);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void mostrarSector(){
        try {
            SectorDAO sectorDAO = new SectorDAO(connection);
            List<Sector> sectors = sectorDAO.obtenirTots();

            sectors.forEach(System.out::println);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
    private static void mostrarEscalador(){
        try {
            EscaladorDAO escaladorDAO = new EscaladorDAO(connection);
            List<Escalador> escaladors = escaladorDAO.obtenirTots();

            escaladors.forEach(System.out::println);
        } catch (Exception error){
            System.out.println(error.getMessage());
        }
    }


    //Funcions per casos especifics
    private static void viesDisponibles(){
        System.out.println("Quina escola vols mostrar?");
        String nom = scan.nextLine();

        try {
            ViaDAO viaDAO = new ViaDAO(connection);

            List<Via> vies = viaDAO.viesDisponibles(nom);

            if(vies.isEmpty()){
                throw new Exception("No hi ha cap via apte a aquesta escola...");
            }

            vies.forEach(System.out::println);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }





    }
    private static void viesPerRang() {
        System.out.print("Introdueix el primer valor: ");
        String primer = scan.next();
        scan.nextLine();

        System.out.print("Introdueix el segons valor: ");
        String segon = scan.next();
        scan.nextLine();

        try{
            ViaDAO viaDAO = new ViaDAO(connection);

            List<Via> vies = viaDAO.viesPerRang(primer, segon);

            if(vies.isEmpty()){
                throw new Exception("No hi ha cap via amb aquest rang");
            }

            for (Via v : vies) {
                System.out.println("Nom: " + v.getNom() + "\n" +
                        "Llargada: " + v.getLlargada() + "\n" +
                        "Dificultat ID: " + v.getId_dificultat() +"\n" +
                        "Estat: " + v.getEstat());
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
    private static void viesPerEstat(){
        System.out.print("Per quin estat vols filtrar? ");
        String estat = scan.next();
        scan.nextLine();

        try{
            ViaDAO viaDAO = new ViaDAO(connection);

            List<Via> vies = viaDAO.viesPerEstat(estat);

            if(vies.isEmpty()){
                throw new Exception("No hi ha cap via amb aquest estat");
            }

            vies.forEach(System.out::println);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void escolesAmbRestriccions(){
        try{
            EscolaDAO escolaDAO = new EscolaDAO(connection);

            List<Escola> escoles = escolaDAO.escolesAmbRestriccions();

            if(escoles.isEmpty()){
                throw new Exception("No hi ha cap escola amb restriccions");
            }

            escoles.forEach(System.out::println);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
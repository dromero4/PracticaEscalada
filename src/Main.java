import dao.mysql.*;
import model.Escalador;
import model.Escola;
import model.Sector;
import model.Via;
import view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                case 1: //Escoles
                    View.showMenuCRUD("Escola");
                    int opcioCRUD_Escola = scan.nextInt();
                    switch (opcioCRUD_Escola){
                        case 1 -> afegirEscola();
                        case 2 -> modificarEscola();
                        case 3 -> mostrar_1_Escola();
                        case 4 -> mostrarEscola();
                    }
                    break;
                case 2:
                    View.showMenuCRUD("Vies");
                    int opcioCRUD_Via = scan.nextInt();

                    switch (opcioCRUD_Via){
                        case 1 -> afegirVia();
                        case 2 -> modificarVia();
                        case 3 -> mostrar_1_Via();
                        case 4 -> mostrarVia();
                    }
                    break;
                case 3:
                    View.showMenuCRUD("Sector");
                    int opcioCRUD_Sector = scan.nextInt();
                    scan.nextLine();

                    switch (opcioCRUD_Sector){
                        case 1 -> afegirSector();
                        case 2 -> modificarSector();
                        case 3 -> mostrar_1_Sector();
                        case 4 -> mostrarSector();
                    }
                    break;
                case 4:
                    View.showMenuCRUD("Escalador");
                    int opcioCRUD_Escalador = scan.nextInt();
                    scan.nextLine();

                    switch (opcioCRUD_Escalador){
                        case 1 -> afegirEscalador();
                        case 2 -> modificarEscalador();
                        case 3 -> mostrar_1_Escalador();
                        case 4 -> mostrarEscalador();
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

        System.out.print("Regulacions: ");
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

    //Funcions per modificar <----
    private static void modificarEscola(){}
    private static void modificarVia(){}
    private static void modificarSector(){}
    private static void modificarEscalador(){}

    //Funcions per mostrar 1
    private static void mostrar_1_Escola(){}
    private static void mostrar_1_Via(){}
    private static void mostrar_1_Sector(){}
    private static void mostrar_1_Escalador(){}

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
    private static void mostrarVia(){}
    private static void mostrarSector(){}
    private static void mostrarEscalador(){}


}
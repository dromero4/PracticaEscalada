import dao.mysql.Con;
import dao.mysql.EscolaDAO;
import model.Escola;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        View.showMenu();
        int opcio = scan.nextInt();

        switch (opcio){
            case 1: //Escoles
                View.showMenuCRUD("Escola");
                int opcioCRUD = scan.nextInt();
                switch (opcioCRUD){
                    case 1:
                        scan.nextLine();
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


                            Dotenv dotenv = Dotenv.load();

                            String url = dotenv.get("DB_URL");
                            String user =  dotenv.get("DB_USER");
                            String password = dotenv.get("DB_PASSWORD");

                            Connection connection = Con.openConnection(url, user, password);
                            EscolaDAO escolaDAO = new EscolaDAO(connection);
                            escolaDAO.crearEscola(escola);

                            View.successMessage();
                        } catch (Exception error){
                            System.out.println(error.getMessage());
                        }

                        break;
                }
                break;
        }
    }
}
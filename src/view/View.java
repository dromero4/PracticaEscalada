package view;

public class View {
    public static void showMenu(){
        System.out.println("=== Aplicacio escalada ===");
        System.out.println("Disposem de:");
        System.out.println("1. Casos predefinits");
        System.out.println("2. Escoles");
        System.out.println("3. Vies");
        System.out.println("4. Sectors");
        System.out.println("5. Escaladors");
        System.out.print("Quin element vols tocar?: ");
    }

    public static void showCasosPredefinits(){
        System.out.println("=== Casos predefinits ===");
        System.out.println("1. Mostra les vies d'una escola que es troben disponibles");
        System.out.println("2. Cercar vies per dificultat en un rang específic");
        System.out.println("3. Cercar vies segons estat [Apte, Construcció, Tancada]");
        System.out.println("4. Consultar escoles amb restriccions actives actualment");
        System.out.println("5. Mostrar sectors amb més de X vies disponibles");
        System.out.println("6. Mostrar escaladors amb el mateix nivell màxim assolit");
        System.out.println("7. Mostrar les vies que han passat a 'Apte' recentment");
        System.out.println("8. Mostrar les vies més llargues d'una escola determinada");
    }

    public static void showMenuCRUD(String str){
        System.out.println("== Qué vols fer? ==");
        System.out.println("1. Crear " + str.toLowerCase());
        System.out.println("2. Modificar " + str.toLowerCase());
        System.out.println("3. Llistar 1 " + str.toLowerCase());
        System.out.println("4. Llistar tot " + str.toLowerCase());
        System.out.println("5. Eliminar " + str.toLowerCase());
    }

    public static void showMenuModificarEscola(){
        System.out.println("Quin element vols modificar? ");
        System.out.println("1. Nom");
        System.out.println("2. Poblacio");
        System.out.println("3. Acces");
        System.out.println("4. Numero de vies");
        System.out.println("5. Dificultat");
        System.out.println("6. Regulacions");
    }

    public static void showMenuModificarVia(){
        System.out.println("Quin element vols modificar? ");
        System.out.println("1. Nom");
        System.out.println("2. Llargada");
        System.out.println("3. Dificultat");
        System.out.println("4. Orientacio");
        System.out.println("5. Estat");
        System.out.println("6. Escola [ID]");
        System.out.println("7. Sector [ID]");
        System.out.println("8. Tipus de roca");
        System.out.println("9. Escalador [ID]");
        System.out.println("10. Estil");
    }

    public static void showMenuModificarSector(){
        System.out.println("Quin element vols modificar? ");
        System.out.println("1. Nom");
        System.out.println("2. Coordenades");
        System.out.println("3. Acces");
        System.out.println("4. Numero de vies");
        System.out.println("5. Dificultat");
        System.out.println("6. Regulacions");
        System.out.println("6. Escola [ID]");
    }

    public static void showMenuModificarEscaladors(){
        System.out.println("Quin element vols modificar? ");
        System.out.println("1. Nom");
        System.out.println("2. Nick");
        System.out.println("3. Edat");
        System.out.println("4. Nivell");
        System.out.println("5. Via favorita");
        System.out.println("6. Estil");
    }


    public static void successMessage(){
        System.out.println("Tot ha anat correcte!");
    }
}

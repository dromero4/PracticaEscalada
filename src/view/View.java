package view;

public class View {
    public static void showMenu(){
        System.out.println("=== Aplicacio escalada ===");
        System.out.println("Disposem de:");
        System.out.println("1. Escoles");
        System.out.println("2. Vies");
        System.out.println("3. Sectors");
        System.out.println("4. Escaladors");
        System.out.print("Quin element vols tocar?: ");
    }

    public static void showMenuCRUD(String str){
        System.out.println("== Qué vols fer? ==");
        System.out.println("1. Crear " + str);
        System.out.println("2. Modificar " + str);
        System.out.println("3. Llistar 1 " + str);
        System.out.println("4. Llistar tot " + str);
        System.out.println("5. Eliminar " + str);
    }

    public static void successMessage(){
        System.out.println("Tot ha anat correcte!");
    }
}

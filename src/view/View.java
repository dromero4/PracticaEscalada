package view;

public class View {
    public static void showMenu(){
        System.out.println("=== Aplicacio escalada ===");
        System.out.println("Disposem de:");
        System.out.println("1. Escoles");
        System.out.println("2. Vies");
        System.out.println("3. Sectors");
        System.out.println("4. Escaladors");
    }

    public static void showMenuCRUD(){
        System.out.println("== Qué vols fer? ==");
        System.out.println("1. Crear");
        System.out.println("2. Modificar");
        System.out.println("3. Llistar un");
        System.out.println("4. Llistar tots");
        System.out.println("5. Eliminar");
    }
}

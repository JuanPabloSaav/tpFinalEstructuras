import java.util.Scanner;

public class mainSystem{
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Ingrese la opcion deseada");
            System.out.println("1. Menu de Ciudades");
            System.out.println("2. Menu de Equipos");
            System.out.println("3. Menu de Partidos");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                        
                    break;
                case 3:
            
                    break;
                default:
                    break;
            }

        } while (opcion != 0);
        sc.close();
    }
}
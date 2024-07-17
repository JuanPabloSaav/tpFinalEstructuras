package menu;

import java.util.Scanner;
import estructuras.conjuntistas.Avl;
import objetos.Equipo;
import estructuras.lineales.Lista;

public class menuEquipos{

    private static Scanner sc = new Scanner(System.in);

    public static void menu(Avl arbolEquipos){
        int opcion = -1;
        do {
            System.out.println("Ingrese la opcion deseada");
            System.out.println("1. Agregar Equipo");
            System.out.println("2. Buscar Equipo");
            System.out.println("3. Buscar en rango");
            System.out.println("4. Mostrar todos los equipos");
            System.out.println("0. Salir");
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    agregarEquipo(arbolEquipos);
                    break;
                case 2:
                    buscarEquipo(arbolEquipos);
                    break;
                case 3:
                    buscarPorRango(arbolEquipos);
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }

    public static void agregarEquipo(Avl arbolEquipos){
        String apellidoTecnico, grupo = "a", pais;
        int puntosGanados, golesAFavor, golesEnContra;

        System.out.println("Ingrese el apellido del tecnico");
        do {
            apellidoTecnico = sc.nextLine();
            if (apellidoTecnico.equals("")) {
                System.out.println("Ingrese un apellido valido");
            }
        } while (apellidoTecnico.equals(""));

        System.out.println("Ingrese el grupo del equipo");
        int opcion = -1;
        do {
            System.out.println("1. A");
            System.out.println("2. B");
            System.out.println("3. C");
            System.out.println("4. D");
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    grupo = "a";
                    break;
                
                case 2:
                    grupo = "b";
                    break;
                
                case 3:
                    grupo = "c";
                    break;
                
                case 4:
                    grupo = "d";
                    break;
            
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        } while (opcion == -1);

        System.out.println("Ingrese el pais");
        do {
            pais = sc.nextLine().toLowerCase();
            if (pais.equals("")) {
                System.out.println("Ingrese un pais valido");
            }
        } while (pais.equals(""));

        System.out.println("Ingrese los puntos ganados");
        do {
            try {
                puntosGanados = sc.nextInt();
            } catch (Exception e) {
                puntosGanados = -1;
            }
            if (puntosGanados < 0) {
                System.out.println("Ingrese un numero valido");
            }
        } while (puntosGanados < 0);

        System.out.println("Ingrese los goles a favor");
        do {
            try {
                golesAFavor = sc.nextInt();
            } catch (Exception e) {
                golesAFavor = -1;
            }
        } while (golesAFavor < 0);
        
        System.out.println("Ingrese los goles en contra");
        do {
            try {
                golesEnContra = sc.nextInt();
            } catch (Exception e) {
                golesEnContra = -1;
            }
        } while (golesEnContra < 0);

        arbolEquipos.insertar(new Equipo(apellidoTecnico, grupo, pais));
    }

    private static void buscarEquipo(Avl arbolEquipos){
        String pais = "";
        do {
            try {
                pais = sc.nextLine().toLowerCase();
                if (pais.equals("")) {
                    System.out.println("Ingrese un pais valido");
                }
            } catch (Exception e) {
                pais = "";
            }
        } while (pais.equals(""));
        Lista lista = arbolEquipos.listar();
        Equipo equipo = null;
        int longitud = lista.longitud();
        for (int i = 1; i < longitud; i++) {
            Equipo aux = (Equipo) lista.recuperar(i);
            if (aux.getPais().equals(pais)) {
                equipo = aux;
                break;
            }
        }
        System.out.println("Equipo "+ equipo.getPais() + " encontrado:");
        System.out.println("Puntos ganados: " + equipo.getPuntosGanados() +
        "\nGoles a favor: " + equipo.getGolesAFavor() +
        "\nGoles en contra: " + equipo.getGolesEnContra());
    }

    private static void buscarPorRango(Avl arbolEquipos){
        String pais1, pais2;
        System.out.println("Ingrese el primer pais");
        do {
            try {
                pais1 = sc.nextLine().toLowerCase();
                if (pais1.equals("")) {
                    System.out.println("Ingrese un pais valido");
                }
            } catch (Exception e) {
                pais1 = "";
            }
        } while (pais1.equals(""));
        
        System.out.println("Ingrese el segundo pais");
        do {
            try {
                pais2 = sc.nextLine().toLowerCase();
                if (pais2.equals("")) {
                    System.out.println("Ingrese un pais valido");
                }
            } catch (Exception e) {
                pais2 = "";
            }
        } while (pais2.equals(""));

        Lista lista = arbolEquipos.listarRango(pais1, pais2);
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) lista.recuperar(i);
            System.out.println("Equipo: " + equipo.toString());
        }
    }
}
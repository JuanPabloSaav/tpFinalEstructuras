package menu;

import estructuras.conjuntistas.Avl;
import estructuras.conjuntistas.TablaHash;
import estructuras.lineales.Lista;
import logSystem.Log;
import estructuras.grafo.Grafo;
import objetos.dominioPartido;
import objetos.rangoPartido;
import objetos.Equipo;
import objetos.Ciudad;
import java.util.Scanner;

public class menuPartidos {
    private static Scanner sc = new Scanner(System.in);

    public static void menu(TablaHash tablaPartidos, Avl arbolEquipos, Grafo ciudades){
        int opcion = -1;
        do {
            System.out.println("Ingrese la opcion deseada");
            System.out.println("1. Agregar Partido");
            System.out.println("2. Buscar partido");
            System.out.println("3. Mostrar todos los partidos");
            System.out.println("0. Salir");
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    agregarPartido(tablaPartidos, arbolEquipos, ciudades);
                    break;
            
                case 2:
                    buscarPartido(tablaPartidos);
                    break;

                case 3:
                    mostrarPartidos(tablaPartidos);
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }

    public static void agregarPartido(TablaHash tablaPartidos, Avl arbolEquipos, Grafo ciudades){
        int golesEq1, golesEq2;
        String ronda, estadio;
        Equipo eq2, eq1;

        do {

            
            System.out.println("Ingrese el pais del primer equipo");
            eq1 = solicitarEquipo(arbolEquipos);
            System.out.println("Ingrese el pais del segundo equipo");
            eq2 = solicitarEquipo( arbolEquipos);

            if (eq1.compareTo(eq2) == 0) {
                System.out.println("Un equipo no puede jugar contra si mismo, intente de nuevo");
                eq1 = null;
                eq2 = null;
            }

        } while (eq1 == null || eq2 == null);

        System.out.println("Ingrese los goles del primer equipo");
        golesEq1 = solicitarGoles();

        System.out.println("Ingrese los goles del segundo equipo");
        golesEq2 = solicitarGoles();

        System.out.println("¿En que ronda se jugo el partido?");
        ronda = solicitarRonda();
        if (eq1.compareTo(eq2) > 0) {
            Equipo aux = eq1;
            eq1 = eq2;
            eq2 = aux;
            
        }

        System.out.println("Ingrese la ciudad donde se jugo el partido");
        Ciudad ciudad = solicitarCiudad(ciudades);

        System.out.println("Ingrese el nombre del estadio donde se jugo el partido");
        estadio = solicitarEstadio();
        if (golesEq1 > golesEq2) {
            eq1.setPuntosGanados(3+eq1.getPuntosGanados());
        }else if (golesEq1 < golesEq2) {
            eq2.setPuntosGanados(3+eq2.getPuntosGanados());
        }else{
            eq1.setPuntosGanados(1+eq1.getPuntosGanados());
            eq2.setPuntosGanados(1+eq2.getPuntosGanados());
        }
        tablaPartidos.asociar(new dominioPartido(eq1, eq2), new rangoPartido(ronda, ciudad, estadio,golesEq1, golesEq2));
    }

    public static void buscarPartido(TablaHash tablaPartidos){
        String paisEq1, paisEq2;

        System.out.println("Ingrese el pais del primer equipo");
        paisEq1 = sc.nextLine();
        System.out.println("Ingrese el pais del segundo equipo");
        paisEq2 = sc.nextLine();

        paisEq1 = paisEq1.toLowerCase();
        paisEq2 = paisEq2.toLowerCase();

        dominioPartido dp = new dominioPartido(new Equipo(paisEq1), new Equipo(paisEq2));
        Lista listaPartidos = tablaPartidos.obtenerValor(dp);
        int longitud = listaPartidos.longitud();
        if (longitud > 0) {
            System.out.println("Se encontraron los siguientes partidos");
            System.out.println(paisEq1.toUpperCase() + " vs " + paisEq2.toUpperCase());
            for (int i = 1; i <= longitud; i++) {
                rangoPartido rp = (rangoPartido) listaPartidos.recuperar(i);
                System.out.println(rp.toString());
            }
        }else{
            System.out.println("No se encontraron partidos");
        }
    }

    private static void mostrarPartidos(TablaHash tablaPartidos){
        Lista lista = tablaPartidos.listar();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Object[] datos = (Object[]) lista.recuperar(i);
            dominioPartido dp = (dominioPartido) datos[0];
            rangoPartido rp = (rangoPartido) datos[1];
            System.out.println(dp.getEq1().getPais() + "vs" + dp.getEq2().getPais() + ": ");
            System.out.println("Estadio: "+rp.getEstadio()
            +"\nCiudad: "+rp.getCiudad().getNombre()
            +"\nRonda: "+rp.getRonda() 
            + "\nGoles "+dp.getEq1().getPais()+": "+rp.getGolesEq1()
            +"\nGoles "+dp.getEq2().getPais()+": " +rp.getGolesEq2() + "\n");
        }
    }

    private static Equipo solicitarEquipo(Avl equipos){
        Equipo equipo = null;
        do {
            equipo = menuEquipos.buscarEquipo(sc.nextLine().toLowerCase(), equipos);
            if (equipo == null) {
                System.out.println("Equipo no encontrado, intente de nuevo");
            }
        } while (equipo == null);
        return equipo;
    }

    private static int solicitarGoles(){
        int goles;
        do {
            try {
                goles = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido");
                goles = -1;
            }
        } while (goles >= 0);
        return goles;
    }

    private static String solicitarRonda(){
        String ronda = "";
        int opcion = -1;
        do {
            System.out.println("1. Grupo");
            System.out.println("2. Cuartos");
            System.out.println("3. Semifinal");
            System.out.println("4. Final");
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    ronda = "grupo";
                    break;
            
                case 2:
                    ronda = "cuartos";
                    break;

                case 3:
                    ronda = "semifinal";
                    break;
                
                case 4:
                    ronda = "final";
                    break;
                
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        } while (opcion != -1);
        return ronda;
    }

    private static Ciudad solicitarCiudad(Grafo ciudades){
        Ciudad ciudad = null;
        do {
            try {
                String nombreCiudad = sc.nextLine();
                if (nombreCiudad.equals("")) {
                    System.out.println("Ingrese un nombre valido");
                }else{
                    ciudad = menuCiudades.buscarCiudad(ciudades, nombreCiudad);
                }
            } catch (Exception e) {
                Log.write("Error al buscar la ciudad");
            }
        } while (ciudad == null);
        return ciudad;
    }

    private static String solicitarEstadio(){
        String estadio = "";
        do {
            try {
                estadio = sc.nextLine();
                if (estadio.equals("")) {
                    System.out.println("Ingrese un nombre valido");
                }
            } catch (Exception e) {
                Log.write("Error al buscar el estadio");
            }
        } while (estadio.equals(""));
        return estadio;
    }
}

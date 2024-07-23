package menu;

import java.util.Scanner;
import estructuras.conjuntistas.Avl;
import objetos.Equipo;
import objetos.EquipoGoles;
import estructuras.lineales.Lista;

public class menuEquipos {

    private static Scanner sc = new Scanner(System.in);

    public static void menu(Avl arbolEquipos) {
        int opcion = -1;
        do {
            System.out.println("Ingrese la opcion deseada");
            System.out.println("1. Agregar Equipo");
            System.out.println("2. Buscar Equipo");
            System.out.println("3. Buscar en rango");
            System.out.println("4. Mostrar todos los equipos");
            System.out.println("5. Eliminar equipo");
            System.out.println("6. Modificar equipo");
            System.out.println("7. Mostrar equipos ordenados por goles a favor");
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
                    solicitarEquipo(arbolEquipos);
                    break;
                case 3:
                    buscarPorRango(arbolEquipos);
                    break;
                case 4:
                    mostrarEquipos(arbolEquipos);
                    break;
                case 5:
                    eliminarEquipo(arbolEquipos);
                    break;
                case 6:
                    modificarEquipo(arbolEquipos);
                    break;
                case 7:
                    mostrarEquiposOrdenadosPorGoles(arbolEquipos);
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }

    public static void agregarEquipo(Avl arbolEquipos) {
        String apellidoTecnico, grupo, pais;

        apellidoTecnico = solicitarApellidoTecnico();
        grupo = solicitarGrupo();
        pais = solicitarPais();

        arbolEquipos.insertar(new Equipo(apellidoTecnico, grupo, pais));
    }

    public static Equipo buscarEquipo(String nombrePais, Avl arbolEquipos) {
        Lista lista = new Lista();
        Equipo eq = null;

        if (!nombrePais.equals("")) {
            lista = arbolEquipos.listar();
            int longitud = lista.longitud();
            for (int i = 1; i <= longitud; i++) {
                Equipo equipo = (Equipo) lista.recuperar(i);
                if (equipo.getPais().equals(nombrePais)) {
                    eq = equipo;
                    break;
                }
            }

        }

        return eq;
    }

    private static void solicitarEquipo(Avl arbolEquipos) {
        String pais = solicitarPais();
        Equipo equipo = buscarEquipo(pais, arbolEquipos);
        System.out.println("Equipo " + equipo.getPais() + " encontrado:");
        System.out.println("Puntos ganados: " + equipo.getPuntosGanados() +
                "\nGoles a favor: " + equipo.getGolesAFavor() +
                "\nGoles en contra: " + equipo.getGolesEnContra());
    }

    private static void buscarPorRango(Avl arbolEquipos) {
        String pais1, pais2;
        System.out.println("Primer pais");
        pais1 = solicitarPais();

        System.out.println("Segundo pais");
        pais2 = solicitarPais();

        Lista lista = arbolEquipos.listarRango(pais1, pais2);
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) lista.recuperar(i);
            System.out.println("Equipo: " + equipo.toString());
        }
    }

    private static void mostrarEquipos(Avl arbolEquipos) {
        Lista lista = arbolEquipos.listar();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) lista.recuperar(i);
            System.out.println("Equipo: " + equipo.toString());
        }
    }

    private static void eliminarEquipo(Avl arbolEquipos) {
        String pais = solicitarPais();
        Equipo equipo = buscarEquipo(pais, arbolEquipos);
        if (equipo != null) {
            arbolEquipos.eliminar(equipo);
        } else {
            System.out.println("No se encontro el equipo");
        }
    }

    private static void modificarEquipo(Avl arbolEquipos) {
        String pais = solicitarPais();
        Equipo equipo = buscarEquipo(pais, arbolEquipos);
        if (equipo != null) {
            int opcion = -1;
            try {
                do {
                    System.out.println("Ingrese la opcion deseada");
                    System.out.println("1. Modificar apellido del tecnico");
                    System.out.println("2. Modificar grupo");
                    System.out.println("3. Modificar pais");
                    System.out.println("4. Modificar puntos ganados");
                    System.out.println("5. Modificar goles a favor");
                    System.out.println("6. Modificar goles en contra");
                    System.out.println("0. Salir");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            String apellidoTecnico = solicitarApellidoTecnico();
                            equipo.setApellidoTecnico(apellidoTecnico);
                            break;
                        case 2:
                            String grupo = solicitarGrupo();
                            equipo.setGrupo(grupo);
                            break;
                        case 3:
                            String paisNuevo = solicitarPais();
                            Equipo equipoAux = buscarEquipo(paisNuevo, arbolEquipos);
                            if (equipoAux != null) {
                                equipo.setPais(paisNuevo);
                                arbolEquipos.eliminar(equipo);
                                arbolEquipos.insertar(equipo);
                            }else{
                                System.out.println("Ya existe un equipo con ese pais");
                            }
                            break;
                        case 4:
                            int puntosGanados = solicitarPuntosGanados();
                            equipo.setPuntosGanados(puntosGanados);
                            break;
                        case 5:
                            int golesAFavor = solicitarGolesAFavor();
                            equipo.setGolesAFavor(golesAFavor);
                            break;
                        case 6:
                            int golesEnContra = solicitarGolesEnContra();
                            equipo.setGolesEnContra(golesEnContra);
                            break;
                        default:
                            break;
                    }
                } while (opcion < 0 && opcion >= 7);
            } catch (Exception e) {
                opcion = -1;
            }
        } else {
            System.out.println("No se encontro el equipo");
        }
    }

    private static void mostrarEquiposOrdenadosPorGoles(Avl arbolEquipos) {
        Lista lista = arbolEquipos.listar();
        Avl arbolGoles = new Avl();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            EquipoGoles equipo = new EquipoGoles(((Equipo) lista.recuperar(i)).getPais(), ((Equipo) lista.recuperar(i)).getGolesAFavor());
            arbolGoles.insertar(equipo);
        }
        lista = arbolGoles.listar();
        longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            EquipoGoles equipo = (EquipoGoles) lista.recuperar(i);
            System.out.println("Equipo: " + equipo.toString());
        }
    }

    private static String solicitarApellidoTecnico() {
        String apellidoTecnico = "";
        do {
            System.out.println("Ingrese el apellido del tecnico");
            try {
                apellidoTecnico = sc.nextLine().toLowerCase();
                if (apellidoTecnico.equals("")) {
                    System.out.println("Ingrese un apellido valido");
                }
            } catch (Exception e) {
                apellidoTecnico = "";
            }
        } while (apellidoTecnico.equals(""));
        return apellidoTecnico;
    }

    private static String solicitarGrupo() {
        String grupo = "";
        int opcionGrupo = -1;
        do {
            System.out.println("Ingrese el grupo");
            System.out.println("1. A");
            System.out.println("2. B");
            System.out.println("3. C");
            System.out.println("4. D");
            try {
                opcionGrupo = sc.nextInt();
            } catch (Exception e) {
                opcionGrupo = -1;
            }
            switch (opcionGrupo) {
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
        } while (opcionGrupo == -1);
        return grupo;
    }

    private static String solicitarPais() {
        String pais = "";
        do {
            System.out.println("Ingrese el pais");
            try {
                pais = sc.nextLine().toLowerCase();
                if (pais.equals("")) {
                    System.out.println("Ingrese un pais valido");
                }
            } catch (Exception e) {
                pais = "";
            }
        } while (pais.equals(""));
        return pais;
    }

    private static int solicitarPuntosGanados() {
        int puntosGanados = -1;
        do {
            System.out.println("Ingrese los puntos ganados");
            try {
                puntosGanados = sc.nextInt();
            } catch (Exception e) {
                puntosGanados = -1;
            }
            if (puntosGanados < 0) {
                System.out.println("Ingrese un numero valido");
            }
        } while (puntosGanados < 0);
        return puntosGanados;
    }

    private static int solicitarGolesAFavor() {
        int golesAFavor = -1;
        do {
            System.out.println("Ingrese los goles a favor");
            try {
                golesAFavor = sc.nextInt();
            } catch (Exception e) {
                golesAFavor = -1;
            }
        } while (golesAFavor < 0);
        return golesAFavor;
    }

    private static int solicitarGolesEnContra() {
        int golesEnContra = -1;
        do {
            System.out.println("Ingrese los goles en contra");
            try {
                golesEnContra = sc.nextInt();
            } catch (Exception e) {
                golesEnContra = -1;
            }
        } while (golesEnContra < 0);
        return golesEnContra;
    }
}
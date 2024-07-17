package menu;

import java.util.Scanner;

import estructuras.grafo.Grafo;
import estructuras.grafo.NodoEnlace;
import estructuras.grafo.NodoVertice;
import estructuras.lineales.Lista;
import logSystem.Log;
import objetos.Ciudad;

public class menuCiudades {

    private static Scanner sc = new Scanner(System.in);

    public static void menu(Grafo ciudades){
        int opcion = -1;
        do {
            System.out.println("1. Agregar ciudad");
            System.out.println("2. Eliminar ciudad");
            System.out.println("3. Modificar ciudad");
            System.out.println("4. Listar ciudades");
            System.out.println("5. Mostras ruta de menor tiempo de viaje");
            System.out.println("6. Mostrar camino que pase por menos ciudades");
            System.out.println("0. Salir");
            try {
                opcion = sc.nextInt();
                if (opcion < 0) {
                    System.out.println("Opción inválida, intenta de nuevo");
                }
            } catch (Exception e) {
                System.out.println("Opción inválida, intenta de nuevo");
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    agregarCiudad(ciudades);
                    break;
                case 2:
                    eliminarCiudad(ciudades);
                    break;
                case 3:
                    modificarCiudad(ciudades);
                    break;
                case 4:
                    solicitarCiudad(ciudades);
                    break;
                case 5:
                    buscarCaminoDeMenortiempo(ciudades);
                    break;
                case 6:
                    buscarCaminoMasCorto(ciudades);
                    break;
                default:
                    break;
            }
        } while (opcion < 0);
        sc.close();
    }

    public static void agregarCiudad(Grafo ciudades){
        String nombre = "";
        int opcion = -1;
        boolean alojamientoDisponible = false, esSede = false;
        Ciudad ciudad = null;
        try {
            do {
                System.out.println("Ingrese el nombre de la ciudad");
                nombre = sc.nextLine();
            } while (nombre.equals(""));

            do {
                System.out.println("¿Tiene alojamiento disponible?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        alojamientoDisponible = true;
                        break;
                    case 2:
                        alojamientoDisponible = false;
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo");
                        break;
                }
            } while (opcion <= 0 || opcion > 2);

            opcion = -1;
            do {
                System.out.println("¿Tiene alojamiento disponible?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        esSede = true;
                        break;
                    case 2:
                        esSede = false;
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo");
                        break;
                }
            } while (opcion <= 0 || opcion > 2);

            ciudad = new Ciudad(nombre, alojamientoDisponible, esSede);
            ciudades.insertarVertice(ciudad);
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    public static void eliminarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a eliminar");
            nombre = sc.nextLine();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad != null) {
                ciudades.eliminarVertice(ciudad);
            } else {
                System.out.println("La ciudad no existe");
            }
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    public static void modificarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a modificar");
            nombre = sc.nextLine();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad == null) {
                System.out.println("La ciudad no existe");
            }else{
                int opcion = -1;
                do {
                    System.out.println("¿Qué desea modificar?");
                    System.out.println("1. Modificar nombre");
                    System.out.println("2. Modificar alojamiento disponible");
                    System.out.println("3. Modificar si es sede");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre de la ciudad");
                            String nuevoNombre = sc.nextLine();
                            if (nuevoNombre.equals("")) {
                                System.out.println("Nombre inválido, intenta de nuevo");
                            } else {
                                ciudad.setNombre(nuevoNombre);    
                            }
                            break;
                        case 2:
                            opcion = -1;
                            boolean alojamientoDisponible = false;
                            do {
                                System.out.println("¿Tiene alojamiento disponible?");
                                System.out.println("1. Sí");
                                System.out.println("2. No");
                                System.out.println("3. Salir");
                                opcion = sc.nextInt();
                                switch (opcion) {
                                    case 1:
                                        alojamientoDisponible = true;
                                        ciudad.setAlojamientoDisponible(alojamientoDisponible);
                                        break;
                                    case 2:
                                        ciudad.setAlojamientoDisponible(alojamientoDisponible);
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Opción inválida, intenta de nuevo");
                                        break;
                                }
                            } while (opcion <= 0 || opcion > 2);
                            break;
                        case 3:
                            opcion = -1;
                            boolean esSede = false;
                            do {
                                System.out.println("¿Es sede?");
                                System.out.println("1. Sí");
                                System.out.println("2. No");
                                System.out.println("3. Salir");
                                opcion = sc.nextInt();
                                switch (opcion) {
                                    case 1:
                                        esSede = true;
                                        ciudad.setEsSede(esSede);
                                        break;
                                    case 2:
                                        ciudad.setEsSede(esSede);
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Opción inválida, intenta de nuevo");
                                        break;
                                }
                            } while (opcion <= 0 || opcion > 2);
                            break;
                        default:
                            System.out.println("Opción inválida, intenta de nuevo");
                            opcion = -1;
                            break;
                    }
                } while (opcion < 0);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error, intentelo de nuevo");
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    public static void solicitarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a buscar");
            nombre = sc.nextLine();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad != null) {
                System.out.println("Nombre: " + ciudad.getNombre());
                
                System.out.println("Alojamiento disponible: " + (ciudad.getAlojamientoDisponible()? "Sí": "No"));
                System.out.println("Es sede: " + (ciudad.getEsSede()? "Sí": "No"));
            } else {
                System.out.println("La ciudad no existe");
            }
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    public static Ciudad buscarCiudad(Grafo ciudades, String nombreCiudad){
        Ciudad ciudad = null;
        Lista lista = ciudades.listarVertices();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            NodoVertice aux = (NodoVertice) lista.recuperar(i);
            Ciudad ciudadAux = (Ciudad) aux.getElem();
            if (ciudadAux.getNombre().equalsIgnoreCase(nombreCiudad)) {
                ciudad = ciudadAux;
                break;
            }
        }
        return ciudad;

    }

    public static void listarCiudades(Grafo ciudades){
        Lista lista = ciudades.listarVertices();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            NodoVertice nodo = (NodoVertice) lista.recuperar(i);
            Ciudad ciudad = (Ciudad) nodo.getElem();
            System.out.println(ciudad.getNombre());
        }
    }

    public static void buscarCaminoMasCorto(Grafo ciudades){
        Ciudad origen = null, destino = null;
        Lista camino = new Lista();
        do {
            System.out.println("Ingrese la ciudad de origen");
            origen = buscarCiudad(ciudades, sc.nextLine());
            if (origen == null) {
                System.out.println("La ciudad no existe");
                break;
            }else{
                System.out.println("Ingrese la ciudad de destino");
                destino = buscarCiudad(ciudades, sc.nextLine());
                if (destino == null) {
                    System.out.println("La ciudad no existe");
                    break;
                }else{
                    camino = ciudades.caminoMasCorto(origen, destino);
                }
            }
        } while (origen == null && destino == null);
        int longitud = camino.longitud();
        if (longitud > 0) {
            System.out.println("El camino más corto es:");
            for (int i = 1; i < longitud; i++) {
                NodoVertice nodo = (NodoVertice) camino.recuperar(i);
                Ciudad ciudad = (Ciudad) nodo.getElem();
                System.out.println(ciudad.getNombre() + " -> ");
            }
            NodoVertice nodo = (NodoVertice) camino.recuperar(longitud);
            Ciudad ciudad = (Ciudad) nodo.getElem();
            System.out.println(ciudad.getNombre());
        }else{
            System.out.println("No se encontró un camino");
        }
    }

    public static void buscarCaminoDeMenortiempo(Grafo ciudades){
        Lista aux = ciudades.listarVertices();
        int longitud = aux.longitud();
        NodoVertice nodoOrigen = null;
        NodoVertice nodoDestino = null;
        if (longitud > 0 ) {
            System.out.println("Ingrese la ciudad de origen");
            String origen = sc.nextLine();
            System.out.println("Ingrese la ciudad de destino");
            String destino = sc.nextLine();
            for (int i = 1; i < longitud; i++) {
                NodoVertice nodo = (NodoVertice) aux.recuperar(i);
                Ciudad ciudad = (Ciudad) nodo.getElem();
                if (ciudad.getNombre().equalsIgnoreCase(origen)) {
                    nodoOrigen = nodo;
                }
                if (ciudad.getNombre().equalsIgnoreCase(destino)) {
                    nodoDestino = nodo;
                }
            }
            if (nodoOrigen != null && nodoDestino != null) {
                Object[] camino = buscarCaminoDeMenortiempoAux(nodoOrigen, nodoDestino);
                Lista lista = (Lista) camino[0];
                int tiempoTotal = (int) camino[1];
                System.out.println("El camino de menor tiempo es:");
                int longitudCamino = lista.longitud();
                for (int i = 1; i < longitudCamino; i++) {
                    NodoVertice nodoVertice = (NodoVertice) lista.recuperar(i);
                    Ciudad ciudad = (Ciudad) nodoVertice.getElem();
                    System.out.println(ciudad.getNombre() + " -> ");
                }
                NodoVertice nodoVertice = (NodoVertice) lista.recuperar(longitudCamino);
                Ciudad ciudad = (Ciudad) nodoVertice.getElem();
                System.out.println(ciudad.getNombre());
                System.out.println("El tiempo total es: " + tiempoTotal);
            }else{
                if (nodoOrigen == null) {
                    System.out.println("La ciudad de origen no existe");
                }
                if (nodoDestino == null) {
                    System.out.println("La ciudad de destino no existe");
                }
            }
            
        }else{
            System.out.println("No hay ciudades registradas");
        }
    }


    private static Object[] buscarCaminoDeMenortiempoAux(NodoVertice nodo, NodoVertice destino){
        Object[] camino = new Object[2];
        Lista lista = new Lista();
        int tiempoTotal = 0;
        camino[0] = lista;
        camino[1] = tiempoTotal;
        if (nodo != null) {
            if (nodo.equals(destino)) {
                ((Lista) camino[0]).insertar(nodo, 1);
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                Object[] recuperado;
                while (aux != null) {
                    recuperado = buscarCaminoDeMenortiempoAux(aux.getVertice(), destino);
                    recuperado[1] = ((int) recuperado[1]) + ((int) aux.getEtiqueta());
                    if (((int) recuperado[1]) < ((int) camino[1])) {
                        camino = recuperado;
                    }
                    aux.getSigEnlace();
                }
            }
        }
        return camino;
    }
}

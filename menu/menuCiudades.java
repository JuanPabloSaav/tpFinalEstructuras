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
            System.out.println("7. Agregar ruta");
            System.out.println("8. Eliminar ruta");
            System.out.println("9. Modificar ruta");
            System.out.println("0. Salir");
            try {
                opcion = sc.nextInt();
                if (sc.hasNextLine()) {
                    sc.nextLine(); // Vaciar el buffer si hay una línea pendiente
                }
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
                case 7:
                    agregarRuta(ciudades);
                    break;
                case 8:
                    eliminarRuta(ciudades);
                    break;
                case 9:
                    modificarRuta(ciudades);
                    break;
                default:
                    break;
            }
        } while (opcion > 0);
        sc.close();
    }

    private static void agregarCiudad(Grafo ciudades){
        String nombre = "";
        boolean alojamientoDisponible = false, esSede = false;
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad");
            nombre = solicitarCiudad();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad != null) {
                System.out.println("La ciudad ya existe");
            }else{
                System.out.println("¿Tiene alojamiento disponible?");
                alojamientoDisponible = solicitarSiNo();
                System.out.println("¿Es sede?");
                esSede = solicitarSiNo();

                ciudad = new Ciudad(nombre, alojamientoDisponible, esSede);
                System.out.println((ciudades.insertarVertice(ciudad))?"La ciudad se agregó correctamente":"No se pudo agregar la ciudad");
            }
            
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    private static void eliminarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a eliminar");
            nombre = solicitarCiudad();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad != null) {
                System.out.println(ciudades.eliminarVertice(ciudad)?"La ciudad se eliminó correctamente":"No se pudo eliminar la ciudad");
            } else {
                System.out.println("La ciudad no existe");
            }
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
        }
    }

    private static void modificarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a modificar");
            nombre = solicitarCiudad();
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
                            String nuevoNombre = "";
                            System.out.println("Ingrese el nuevo nombre de la ciudad");
                            nuevoNombre = solicitarCiudad();
                            ciudad.setNombre(nuevoNombre);
                            break;
                        case 2:
                            opcion = -1;
                            System.out.println("¿Tiene alojamiento disponible?");
                            boolean alojamientoDisponible = solicitarSiNo();
                            ciudad.setAlojamientoDisponible(alojamientoDisponible);
                            break;
                        case 3:
                            System.out.println("¿Es sede?");
                            boolean esSede = solicitarSiNo();
                            ciudad.setEsSede(esSede);
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

    private static void solicitarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a buscar");
            nombre = solicitarCiudad();
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

    private static void buscarCaminoMasCorto(Grafo ciudades){
        Ciudad origen = null, destino = null;
        Lista camino = new Lista();
        do {
            System.out.println("Ingrese la ciudad de origen");
            String nombreOrigen = solicitarCiudad();
            origen = buscarCiudad(ciudades, nombreOrigen);
            if (origen == null) {
                System.out.println("La ciudad no existe");
                break;
            }else{
                System.out.println("Ingrese la ciudad de destino");
                String nombreDestino = solicitarCiudad();
                destino = buscarCiudad(ciudades, nombreDestino);
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

    private static void buscarCaminoDeMenortiempo(Grafo ciudades){
        Lista aux = ciudades.listarVertices();
        int longitud = aux.longitud();
        NodoVertice nodoOrigen = null;
        NodoVertice nodoDestino = null;
        if (longitud > 0 ) {

            System.out.println("Ingrese la ciudad de origen");
            String origen = solicitarCiudad();
            System.out.println("Ingrese la ciudad de destino");
            String destino = solicitarCiudad();

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

    private static void agregarRuta(Grafo ciudades){
        Ciudad ciudadOrigen = null, ciudadDestino = null;
        System.out.println("Ingrese la ciudad de origen");
        String nombreOrigen = solicitarCiudad();
        System.out.println("Ingrese la ciudad de destino");
        String nombreDestino = solicitarCiudad();
        ciudadOrigen = buscarCiudad(ciudades, nombreOrigen);
        ciudadDestino = buscarCiudad(ciudades, nombreDestino);
        if (ciudadOrigen != null && ciudadDestino != null) {
            System.out.println("Ingrese el tiempo de viaje");
            int tiempo = solicitarTiempo();
            ciudades.insertarArco(ciudadOrigen, ciudadDestino, tiempo);
        }

    }

    private static void eliminarRuta(Grafo ciudades){
        Ciudad ciudadOrigen = null, ciudadDestino = null;
        System.out.println("Ingrese la ciudad de origen");
        String nombreOrigen = solicitarCiudad();
        System.out.println("Ingrese la ciudad de destino");
        String nombreDestino = solicitarCiudad();
        ciudadOrigen = buscarCiudad(ciudades, nombreOrigen);
        ciudadDestino = buscarCiudad(ciudades, nombreDestino);
        if (ciudadOrigen != null && ciudadDestino != null) {
            ciudades.eliminarArco(ciudadOrigen, ciudadDestino);
        }
    }

    private static void modificarRuta(Grafo ciudades){
        Ciudad ciudadOrigen = null, ciudadDestino = null;
        Ciudad nuevaCiudadDestino = null;
        System.out.println("Ingrese la ciudad de origen");
        String nombreOrigen = solicitarCiudad();
        System.out.println("Ingrese la ciudad de destino");
        String nombreDestino = solicitarCiudad();
        ciudadOrigen = buscarCiudad(ciudades, nombreOrigen);
        ciudadDestino = buscarCiudad(ciudades, nombreDestino);
        
        if (ciudadOrigen != null && ciudadDestino != null) {
            if (ciudades.eliminarArco(nombreOrigen, nombreDestino)) {
                int tiempo = solicitarTiempo();
                ciudades.insertarArco(nombreOrigen, nuevaCiudadDestino, tiempo);
            }else{
                System.out.println("No se encontro la ruta");
            }
        }
    }


    private static String solicitarCiudad(){
        String nombre = "";
        do {
            nombre = sc.nextLine().toLowerCase();
            if (nombre.equals("")) {
                System.out.println("Ingrese una ciudad valida");
            }
        } while (nombre.equals(""));
        return nombre;
    }

    private static boolean solicitarSiNo(){
        int opcion = -1;
        boolean respuesta = false;
        try {
            do {
                System.out.println("1. Sí");
                System.out.println("2. No");
                opcion = sc.nextInt();
                if (sc.hasNextLine()) {
                    sc.nextLine(); // Vaciar el buffer si hay una línea pendiente
                }
                switch (opcion) {
                    case 1:
                        respuesta = true;
                        break;
                    case 2:
                        respuesta = false;
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo");
                        break;
                }
            } while (opcion <= 0 || opcion > 2);
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
            System.out.println("Ocurrio un error, intentalo de nuevo");
            opcion = -1;
        }
        return respuesta;
    }

    private static int solicitarTiempo(){
        int tiempo = 0;
        try {
            do {
                tiempo = sc.nextInt();
                if (sc.hasNextLine()) {
                    sc.nextLine(); // Vaciar el buffer si hay una línea pendiente
                }
                if (tiempo <= 0) {
                    System.out.println("El tiempo de viaje debe ser mayor a 0");
                }
            } while (tiempo <= 0);
        } catch (Exception e) {
            Log.write("ERROR:"+ e.getMessage());
            System.out.println("Ocurrio un error, intentalo de nuevo");
            tiempo = -1;
        }
        return tiempo;
    }


}

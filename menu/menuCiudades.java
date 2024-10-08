package menu;

import java.util.Scanner;

import estructuras.grafo.Grafo;
import estructuras.lineales.Lista;
import logSystem.Log;
import objetos.Ciudad;

public class menuCiudades {

    private static Scanner sc = new Scanner(System.in);

    public static void menu(Grafo ciudades){
        int opcion = -1;
        do {
            System.out.println("\n1. Agregar ciudad");
            System.out.println("2. Eliminar ciudad");
            System.out.println("3. Modificar ciudad");
            System.out.println("4. Buscar ciudad");
            System.out.println("5. Mostras ruta de menor tiempo de viaje");
            System.out.println("6. Mostrar camino que pase por menos ciudades");
            System.out.println("7. Agregar ruta");
            System.out.println("8. Eliminar ruta");
            System.out.println("9. Modificar ruta");
            System.out.println("10. Listar ciudades");
            System.out.println("11. Mostrar rutas");
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
                Log.write("ERROR:"+ e.getMessage());
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
                    buscarCiudad(ciudades);
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
                case 10: 
                    listarCiudades(ciudades);
                    break;
                case 11:
                    mostrarRutas(ciudades);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo");
                    break;
            }
        } while (opcion > 0);
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
            System.out.println("Ocurrio un error, intentelo de nuevo");
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
            Log.write("ERROR:"+ e.getMessage() + " " + e.getStackTrace());
        }
    }

    private static void modificarCiudad(Grafo ciudades){
        int opcion = -1;
        Ciudad ciudad = null;
        String nombre = solicitarCiudad();
        ciudad = buscarCiudad(ciudades, nombre);
        if (ciudad != null) {
            do {
                System.out.println("1. Modificar alojamiento disponible");
                System.out.println("2. Modificar si es sede");
                System.out.println("3. Modificar nombre");
                System.out.println("0. Salir");
                try {
                    try {
                        opcion = sc.nextInt();
                    } catch (Exception e) {
                        Log.write("ERROR:"+ e.getMessage() + " " + e.getStackTrace());
                        opcion = -1;
                    }
                    if (sc.hasNextLine()) {
                        sc.nextLine(); // Vaciar el buffer si hay una línea pendiente
                    }
                    if (opcion < 0) {
                        System.out.println("Opción inválida, intenta de nuevo");
                    }
                } catch (Exception e) {
                    Log.write("ERROR:"+ e.getMessage());
                    opcion = -1;
                    sc.next(); // Vaciar el buffer si hay una línea pendiente
                }
                switch (opcion) {
                    case 1:
                        System.out.println("¿Tiene alojamiento disponible?");
                        ciudad.setAlojamientoDisponible(solicitarSiNo());
                        System.out.println("Alojamiento disponible modificado correctamente");
                        break;
                    case 2:
                        System.out.println("¿Es sede?");
                        ciudad.setEsSede(solicitarSiNo());
                        System.out.println("Sede modificada correctamente");
                        break;
                    case 3:
                        System.out.println("Ingrese el nuevo nombre");
                        String nuevoNombre = solicitarCiudad();
                        if (buscarCiudad(ciudades, nuevoNombre) == null) {
                            ciudad.setNombre(nuevoNombre);
                            System.out.println("El nombre se modificó correctamente");
                        }else{
                            System.out.println("El nombre ya existe");
                        }

                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo");
                        break;
                }
            } while (opcion > 0);
        } else {
            System.out.println("La ciudad no existe");
        }
    }

    private static void buscarCiudad(Grafo ciudades){
        String nombre = "";
        Ciudad ciudad = null;
        try {
            System.out.println("Ingrese el nombre de la ciudad a buscar");
            nombre = solicitarCiudad();
            ciudad = buscarCiudad(ciudades, nombre);
            if (ciudad != null) {
                System.out.println("Nombre: " + ciudad.getNombre().toUpperCase());
                System.out.println("Alojamiento disponible: " + (ciudad.getAlojamientoDisponible()? "Sí": "No"));
                System.out.println("Es sede: " + (ciudad.getEsSede()? "Sí": "No"));
            } else {
                System.out.println("La ciudad no existe");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error, intentelo de nuevo");
            Log.write("ERROR:"+ e.getMessage());
            sc.next(); // Vaciar el buffer si hay una línea pendiente
        }
    }

    public static Ciudad buscarCiudad(Grafo ciudades, String nombreCiudad){
        Ciudad ciudad = null;
        Lista lista = ciudades.listarVertices();
        while (!lista.esVacia() && ciudad == null) {
            Ciudad aux = (Ciudad) lista.recuperar(1);
            if (aux.getNombre().equalsIgnoreCase(nombreCiudad)) {
                ciudad = aux;
            }
            lista.eliminar(1);
        }
        return ciudad;

    }

    public static void listarCiudades(Grafo ciudades){
        Lista lista = ciudades.listarVertices();
        if (!lista.esVacia()) {
            while (!lista.esVacia()) {
                Ciudad ciudad = (Ciudad) lista.recuperar(1);
                System.out.println(ciudad.getNombre());
                lista.eliminar(1);
            }
        }else{
            System.out.println("No hay ciudades registradas");
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
            }else{
                System.out.println("Ingrese la ciudad de destino");
                String nombreDestino = solicitarCiudad();
                destino = buscarCiudad(ciudades, nombreDestino);
                if (destino == null) {
                    System.out.println("La ciudad no existe");
                }else{
                    camino = ciudades.caminoMasCorto(origen, destino);
                }
            }
        } while (origen == null && destino == null);
        if (!camino.esVacia()) {
            System.out.println("El camino más corto es:");
            while (!camino.esVacia()) {
                Ciudad ciudad = (Ciudad) camino.recuperar(1);
                camino.eliminar(1);
                System.out.print(ciudad.getNombre().toUpperCase() + (camino.esVacia()? "": " -> "));
            }
        }else{
            System.out.println("No se encontró un camino");
        }
    }

    private static void buscarCaminoDeMenortiempo(Grafo ciudades){
        System.out.println("Ingrese la ciudad de origen");
        String nombreOrigen = solicitarCiudad();
        System.out.println("Ingrese la ciudad de destino");
        String nombreDestino = solicitarCiudad();
        Ciudad origen = buscarCiudad(ciudades, nombreOrigen);
        Ciudad destino = buscarCiudad(ciudades, nombreDestino);
        if (origen != null && destino != null) {
            Lista camino = ciudades.caminoMenorTiempo(origen, destino);
            if (!camino.esVacia()) {
                System.out.println("El camino de menor tiempo es:");
                while (!camino.esVacia()) {
                    Ciudad ciudad = (Ciudad) camino.recuperar(1);
                    camino.eliminar(1);
                    System.out.print(ciudad.getNombre().toUpperCase() + (camino.esVacia()? "": " -> "));
                }
            }else{
                System.out.println("No se encontró un camino");
            }
            
        }else{
            if (origen == null) {
                System.out.println("La ciudad de origen no existe");
            }
            if (destino == null) {
                System.out.println("La ciudad de destino no existe");
            }
        }
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
            double tiempo = solicitarTiempo();
            System.out.println(ciudades.insertarArco(ciudadOrigen, ciudadDestino, tiempo)?
            "La ruta se agregó correctamente":"No se pudo agregar la ruta");
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
            System.out.println(ciudades.eliminarArco(ciudadOrigen, ciudadDestino)?
            "La ruta se eliminó correctamente":"No se pudo eliminar la ruta");
        }else{
            if (ciudadOrigen == null) {
                System.out.println("La ciudad de origen no existe");
            }
            if (ciudadDestino == null) {
                System.out.println("La ciudad de destino no existe");
            }
        }

    }

    private static void modificarRuta(Grafo ciudades){
        Ciudad ciudadOrigen = null, ciudadDestino = null;
        System.out.println("Ingrese la ciudad de origen");
        String nombreOrigen = solicitarCiudad();
        System.out.println("Ingrese la ciudad de destino");
        String nombreDestino = solicitarCiudad();
        ciudadOrigen = buscarCiudad(ciudades, nombreOrigen);
        ciudadDestino = buscarCiudad(ciudades, nombreDestino);
        if (ciudadOrigen != null && ciudadDestino != null) {
            int opcion = -1;
            double tiempo = 0;
            do {
                System.out.println("1. Modificar tiempo de viaje");
                System.out.println("2. Salir");
                // removido la modificacion de destino y origen ya que al final no se pedia 
                try {
                    opcion = sc.nextInt();
                    if (sc.hasNextLine()) {
                        sc.nextLine(); // Vaciar el buffer si hay una línea pendiente
                    }
                    if (opcion < 0) {
                        System.out.println("Opción inválida, intenta de nuevo");
                    }
                } catch (Exception e) {
                    Log.write("ERROR:"+ e.getMessage());
                    opcion = -1;
                    sc.next(); // Vaciar el buffer si hay una línea pendiente
                }
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo tiempo de viaje");
                        tiempo = solicitarTiempo();
                        System.out.println(ciudades.modificarEtiqueta(ciudadOrigen, ciudadDestino, tiempo)? 
                        "El tiempo de viaje se modificó correctamente":"No se pudo modificar el tiempo de viaje");
                        break;
                    case 2:
                        /* System.out.println("Ingrese la nueva ciudad de destino");
                        String nuevoDestino = solicitarCiudad();
                        Ciudad nuevaCiudadDestino = buscarCiudad(ciudades, nuevoDestino);
                        if (nuevaCiudadDestino != null) {
                            System.out.println("Ingrese el tiempo de viaje");
                            tiempo = solicitarTiempo();
                            if (ciudades.insertarArco(ciudadOrigen, nuevaCiudadDestino, tiempo)){
                                System.out.println(ciudades.eliminarArco(ciudadOrigen, ciudadDestino)?
                                "La ruta se modificó correctamente":"No se pudo modificar la ruta");
                            }else{
                                System.out.println("Ya existe una ruta entre las ciudades");
                            }
                        }else{
                            System.out.println("La ciudad no existe");
                        } */
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo");
                        break;
                }
            } while (opcion > 0 && opcion < 2);
        }else{
            if (ciudadOrigen == null) {
                System.out.println("La ciudad de origen no existe");
            }
            if (ciudadDestino == null) {
                System.out.println("La ciudad de destino no existe");
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
        do {
            try {
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
                        opcion = -1;
                        break;
                }
            } catch (Exception e) {
                Log.write("ERROR:"+ e.getMessage());
                System.out.println("Ocurrio un error, intentalo de nuevo");
                opcion = -1;
                sc.next(); // Vaciar el buffer si hay una línea pendiente
            }
        } while (opcion <= 0 || opcion > 2);
        return respuesta;
    }

    private static double solicitarTiempo(){
        double tiempo = 0;
        try {
            do {
                tiempo = sc.nextDouble();
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
            sc.next(); // Vaciar el buffer si hay una línea pendiente
        }
        return tiempo;
    }

    public static void mostrarRutas(Grafo ciudades){
        Lista lista = ciudades.listarArcos();
        if (!lista.esVacia()) {
            while (!lista.esVacia()) {
                Object[] arco = (Object[]) lista.recuperar(1);
                System.out.println("Origen: " + ((Ciudad) arco[0]).getNombre().toUpperCase() 
                + " Destino: " + ((Ciudad) arco[1]).getNombre().toUpperCase()
                + " Tiempo: " + (double) arco[2]);
                lista.eliminar(1);
            }
        }else{
            System.out.println("No hay rutas registradas");
        }
    }


}

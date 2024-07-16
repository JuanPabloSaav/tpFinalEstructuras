import estructuras.grafo.Grafo;
import estructuras.conjuntistas.Avl;
import estructuras.conjuntistas.TablaHash;
import estructuras.lineales.Cola;
import estructuras.lineales.Lista;

import objetos.Ciudad;
import objetos.Equipo;
import objetos.Ruta;
import objetos.dominioPartido;
import objetos.rangoPartido;

import logSystem.Log;
import menu.menuPartidos;
import menu.menuEquipos;
import menu.menuCiudades;

import java.util.Scanner;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.BufferedReader;

public class mainSystem{
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Iniciando sistema...");
        System.out.println("Inicializando sistema de registros...");
        Log.initialize();
        System.out.println("Iniciando estructuras...");
        Avl equipos = new Avl();
        Grafo ciudades = new Grafo();
        TablaHash partidos = new TablaHash(20);
        inicializarEstructuras(equipos, ciudades, partidos);
        Log.write("Iniciando registro de estructuras");
        regEstructuras(equipos, ciudades, partidos);
        menu(equipos, ciudades, partidos);
        regEstructuras(equipos, ciudades, partidos);
    }

    private static void regEstructuras(Avl equipos, Grafo ciudades, TablaHash partidos){
        Log.write("Estructuras cargadas: ");
        Lista listaEquipos = equipos.listar();
        Lista listaCiudades = ciudades.listarEnProfundidad();
        Lista listaPartidos = partidos.listar();
        Log.write("Equipos: ");
        int longitud = listaEquipos.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) listaEquipos.recuperar(i);
            Log.write(equipo.getPais() 
            + ": Grupo: "+ equipo.getGrupo() 
            + " - Tecnico: "+ equipo.getApellidoTecnico() 
            + " - Goles a favor: " + Integer.toString(equipo.getGolesAFavor()) 
            + " - Goles en contra: " + Integer.toString(equipo.getGolesEnContra())
            + " - Puntos ganados: " + Integer.toString(equipo.getPuntosGanados()));
        }
        Log.write("Ciudades: ");
        longitud = listaCiudades.longitud();
        for (int i = 1; i <= longitud; i++) {
            Ciudad ciudad = (Ciudad) listaCiudades.recuperar(i);
            Log.write(ciudad.getNombre() 
            + ": Alojamiento: "+ ciudad.getAlojamientoDisponible() 
            + " - Sede de Copa: "+ ciudad.getEsSede());
        }

        Log.write("Partidos: ");
        longitud = listaPartidos.longitud();
        for (int i = 1; i <= longitud; i++) {
            Object[] partido = (Object[]) listaPartidos.recuperar(i);
            dominioPartido domPartido = (dominioPartido) partido[0];
            rangoPartido rango = (rangoPartido) partido[1];
            Log.write(domPartido.getEq1().getPais() + " vs " + domPartido.getEq2().getPais()
            + ": Ronda: "+ rango.getRonda()
            + " - Ciudad: "+ rango.getCiudad().getNombre()
            + " - Estadio: "+ rango.getNombreEstadio()
            + " - Goles Eq1: " + Integer.toString(rango.getGolesEq1())
            + " - Goles Eq2: " + Integer.toString(rango.getGolesEq2()));
        }
        Log.write("Estructuras cargadas exitosamente");
    }

    private static void menu(Avl equipos, Grafo ciudades, TablaHash partidos){
        int opcion = -1;
        do {
            try {
                System.out.println("Ingrese la opcion deseada");
                System.out.println("1. Menu de Ciudades");
                System.out.println("2. Menu de Equipos");
                System.out.println("3. Menu de Partidos");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        menuCiudades.menu(ciudades);
                        break;
                    case 2:
                        menuEquipos.menu(equipos);  
                        break;
                    case 3:
                        menuPartidos.menu(partidos, equipos, ciudades);
                        break;
                    default:
                        System.out.println("Opcion invalida, intente otra vez");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opcion invalida, intente otra vez");
                Log.write("Error en el menu: " + e.getLocalizedMessage());
                opcion = -1;
            }

        } while (opcion != 0);
        sc.close();
    }

    private static void inicializarEstructuras(Avl equipos, Grafo ciudades, TablaHash partidos){
        try {
            Log.write("Iniciada carga de archivo");
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            Log.write("Archivo seleccionado: " + file.getName());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Cola tempPartidos = new Cola();
            Cola tempRutas = new Cola();
            Log.write("Leyendo archivo");
            while ((line = reader.readLine()) != null) {
                String[] typeData = line.split(":");
                String[] data = typeData[1].split(";");
                switch (typeData[0]) {
                    case "E":
                        Log.write("Añadiendo Equipo: " + data[0]);
                        Equipo equipo = new Equipo(
                            data[0].toLowerCase(),//Eq1
                            data[1].toLowerCase(),//Eq2
                            data[2].toLowerCase()//grupoInicial
                        );
                        equipos.insertar(equipo);
                        Log.write("Equipo añadido");
                        break;
                    case "C":
                        Log.write("Añadiendo Ciudad: " + data[0]);
                        Ciudad ciudad = new Ciudad(
                            data[0],//nombre
                            Boolean.parseBoolean(data[1].toLowerCase()),//alojamiento
                            Boolean.parseBoolean(data[2].toLowerCase())//sedeDeCopa
                        );
                        ciudades.insertarVertice(ciudad);
                        Log.write("Ciudad añadida");
                        break;
                    case "P":
                        Object[] tempPartido = {
                            data[0].toLowerCase(), //eq1
                            data[1].toLowerCase(), //eq2
                            data[2].toLowerCase(), //ronda
                            data[3].toLowerCase(), //ciudad
                            data[4].toLowerCase(), //estadio
                            Integer.parseInt(data[5]),//eq1 goles
                            Integer.parseInt(data[6])//eq2 goles
                        };
                        tempPartidos.poner(tempPartido);
                        break;
                    case "R":
                        Object[] tempRuta = {
                            data[0].toLowerCase(),//ciudad1
                            data[1].toLowerCase(),//ciudad2
                            Integer.parseInt(data[2])//distancia
                        };
                        tempRutas.poner(tempRuta);
                        break;
                    default:
                        Log.write("Linea invalida en el archivo");
                        break;
                }
            }
            inicializarPartidos(equipos, partidos, tempPartidos, ciudades);
            inicializarRutas(tempRutas, ciudades);
            reader.close();
            
        } catch (Exception e) {
            Log.write("Error al leer el archivo: " + e.getLocalizedMessage());
        }
    }

    private static void inicializarRutas(Cola tempRutas, Grafo ciudades){
        while (!tempRutas.esVacia()) {
            Object[] tempRuta = (Object[]) tempRutas.obtenerFrente();
            tempRutas.sacar();
            Log.write("Añadiendo Ruta: " + tempRuta[0] + " - " + tempRuta[1]);
            Ciudad ciudad1 = menuCiudades.buscarCiudad(ciudades, ((String) tempRuta[0]));
            Ciudad ciudad2 = menuCiudades.buscarCiudad(ciudades, ((String) tempRuta[1]));
            if (ciudad1 != null && ciudad2 != null) {
                Ruta aux = new Ruta(((int) tempRuta[2]), ciudad1, ciudad2);
                ciudades.insertarArco(ciudad1, ciudad2, aux);
                Log.write("Ruta añadida");
            }
        }
    }

    private static void inicializarPartidos(Avl equipos, TablaHash partidos, Cola tempPartidos, Grafo ciudades){
        while (!tempPartidos.esVacia()) {
            Object[] tempPartido = (Object[]) tempPartidos.obtenerFrente();
            tempPartidos.sacar();
            Log.write("Añadiendo Partido: " + tempPartido[0] + " - " + tempPartido[1]);
            String eq1 = (String) tempPartido[0];
            String eq2 = (String) tempPartido[1];
            if (eq1.compareTo(eq2) > 0) {
                String aux = eq1;
                eq1 = eq2;
                eq2 = aux;
            }
            Equipo equipo1 = menuPartidos.buscarEquipo(eq1, equipos);
            Equipo equipo2 = menuPartidos.buscarEquipo(eq2, equipos);
            Ciudad ciudad = menuCiudades.buscarCiudad(ciudades, ((String) tempPartido[3]));
            if (equipo1 != null && equipo2 != null) {
                dominioPartido domPartido = new dominioPartido(equipo1, equipo2);
                rangoPartido rango = new rangoPartido(((String)tempPartido[2]), ciudad, ((String)tempPartido[4]), ((int) tempPartido[5]),((int) tempPartido[6]));
                partidos.asociar(domPartido, rango);
                Log.write("Partido añadido");
            }
        }
        
    }
}
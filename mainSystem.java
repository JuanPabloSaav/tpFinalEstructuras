import estructuras.grafo.Grafo;
import estructuras.grafo.NodoVertice;
import estructuras.conjuntistas.Avl;
import estructuras.conjuntistas.TablaHash;
import estructuras.lineales.Cola;
import estructuras.lineales.Lista;

import objetos.Ciudad;
import objetos.Equipo;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.BufferedReader;

public class mainSystem{
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Iniciando sistema...");
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
        Lista listaCiudades = ciudades.listarVertices();
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
            NodoVertice nodo = (NodoVertice) listaCiudades.recuperar(i);
            Ciudad ciudad = (Ciudad) nodo.getElem();
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
                System.out.println("4. Mostrar todas las estructuras");
                System.out.println("0. Salir");

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
                    case 4:
                        mostrarEstructuras(equipos, ciudades, partidos);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        Log.write("Sistema cerrado");
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
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            Log.write("Archivo seleccionado: " + file.getName());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Cola tempPartidos = new Cola();
            Cola tempRutas = new Cola();
            Log.write("Leyendo archivo");
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    String[] typeData = line.split(":");
                    String[] data = typeData[1].split(";");
                    switch (typeData[0]) {
                        case "E":
                            Log.write("Añadiendo Equipo: " + data[0]);
                            data[0] = data[0].replaceFirst("\\s+", "");
                            data[1] = data[1].replaceFirst("\\s+", "");
                            data[2] = data[2].replaceFirst("\\s+", "");
                            Equipo equipo = new Equipo(
                                data[1].toLowerCase(),//ApellidoTecnico
                                data[2].toLowerCase(),//grupoInicial
                                data[0].toLowerCase()//Pais
                            );
                            equipos.insertar(equipo);
                            Log.write("Equipo añadido");
                            break;
                        case "C":
                            Log.write("Añadiendo Ciudad: " + data[0]);
                            data[0] = data[0].replaceFirst("\\s+", "");
                            data[1] = data[1].replaceFirst("\\s+", "");
                            data[2] = data[2].replaceFirst("\\s+", "");
                            Ciudad ciudad = new Ciudad(
                                data[0].toLowerCase(),//nombre
                                Boolean.parseBoolean(data[1]),//alojamiento
                                Boolean.parseBoolean(data[2])//sedeDeCopa
                            );
                            ciudades.insertarVertice(ciudad);
                            Log.write("Ciudad añadida");
                            break;
                        case "P":
                            data[0] = data[0].replaceFirst("\\s+", "");
                            data[1] = data[1].replaceFirst("\\s+", "");
                            data[2] = data[2].replaceFirst("\\s+", "");
                            data[3] = data[3].replaceFirst("\\s+", "");
                            data[4] = data[4].replaceFirst("\\s+", "");
                            data[5] = data[5].replaceFirst("\\s+", "");
                            data[6] = data[6].replaceFirst("\\s+", "");
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
                            data[0] = data[0].replaceFirst("\\s+", "");
                            data[1] = data[1].replaceFirst("\\s+", "");
                            data[2] = data[2].replaceFirst("\\s+", "");
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
            if (ciudades.insertarArco(tempRuta[0], tempRuta[1], tempRuta[2])) {
                Log.write("Ruta añadida");
            }else{
                Log.write("Error al añadir ruta");
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
            Equipo equipo1 = menuEquipos.buscarEquipo(eq1, equipos);
            Equipo equipo2 = menuEquipos.buscarEquipo(eq2, equipos);
            Ciudad ciudad = menuCiudades.buscarCiudad(ciudades, ((String) tempPartido[3]));
            if (equipo1 != null && equipo2 != null) {
                int golesEq1 = (int) tempPartido[5];
                int golesEq2 = (int) tempPartido[6];
                equipo1.setGolesAFavor(golesEq1+equipo1.getGolesAFavor());
                equipo1.setGolesEnContra(golesEq2+equipo1.getGolesEnContra());
                equipo2.setGolesAFavor(golesEq2+equipo2.getGolesAFavor());
                equipo2.setGolesEnContra(golesEq1+equipo2.getGolesEnContra());
                
                if (((String) tempPartido[2]).equals("grupo")) {
                    if (golesEq1 > golesEq2) {
                        equipo1.setPuntosGanados(3+equipo1.getPuntosGanados());
                    }else if (golesEq1 < golesEq2) {
                        equipo2.setPuntosGanados(3+equipo2.getPuntosGanados());
                    }else{
                        equipo1.setPuntosGanados(1+equipo1.getPuntosGanados());
                        equipo2.setPuntosGanados(1+equipo2.getPuntosGanados());
                    }
                }
                dominioPartido domPartido = new dominioPartido(equipo1, equipo2);
                rangoPartido rango = new rangoPartido(((String)tempPartido[2]), ciudad, ((String)tempPartido[4]), golesEq1, golesEq2);
                partidos.asociar(domPartido, rango);
                Log.write("Partido añadido");
            }
        }
        
    }

    private static void mostrarEstructuras(Avl equipos, Grafo ciudades, TablaHash partidos){
        System.out.println("Equipos: ");
        Lista listaEquipos = equipos.listar();
        int longitud = listaEquipos.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) listaEquipos.recuperar(i);
            System.out.println(equipo.getPais() 
            + ": Grupo: "+ equipo.getGrupo() 
            + " - Tecnico: "+ equipo.getApellidoTecnico() 
            + " - Goles a favor: " + Integer.toString(equipo.getGolesAFavor()) 
            + " - Goles en contra: " + Integer.toString(equipo.getGolesEnContra())
            + " - Puntos ganados: " + Integer.toString(equipo.getPuntosGanados()));
        }
        System.out.println("Ciudades: ");
        Lista listaCiudades = ciudades.listarVertices();
        longitud = listaCiudades.longitud();
        for (int i = 1; i <= longitud; i++) {
            NodoVertice nodo = (NodoVertice) listaCiudades.recuperar(i);
            Ciudad ciudad = (Ciudad) nodo.getElem();
            System.out.println(ciudad.getNombre() 
            + ": Alojamiento: "+ ciudad.getAlojamientoDisponible() 
            + " - Sede de Copa: "+ ciudad.getEsSede());
        }

        System.out.println("Partidos: ");
        Lista listaPartidos = partidos.listar();
        longitud = listaPartidos.longitud();
        for (int i = 1; i <= longitud; i++) {
            Object[] partido = (Object[]) listaPartidos.recuperar(i);
            dominioPartido domPartido = (dominioPartido) partido[0];
            rangoPartido rango = (rangoPartido) partido[1];
            System.out.println(domPartido.getEq1().getPais() + " vs " + domPartido.getEq2().getPais()
            + ": Ronda: "+ rango.getRonda()
            + " - Ciudad: "+ rango.getCiudad().getNombre()
            + " - Estadio: "+ rango.getNombreEstadio()
            + " - Goles Eq1: " + Integer.toString(rango.getGolesEq1())
            + " - Goles Eq2: " + Integer.toString(rango.getGolesEq2()));
        }
    }

}
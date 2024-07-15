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
    public static void main(String[] args) {
        System.out.println("Iniciando sistema...");
        System.out.println("Inicializando sistema de registros...");
        Log.initialize();
        System.out.println("Iniciando estructuras...");
        Avl equipos = new Avl();
        Grafo ciudades = new Grafo();
        TablaHash partidos = new TablaHash(20);
        menu(equipos, ciudades, partidos);
    }

    private static void menu(Avl equipos, Grafo ciudades, TablaHash partidos){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea ingresar los datos desde un archivo?");
        System.out.println("1. Si");
        System.out.println("Cualquier otra tecla. No");
        try {
            if (sc.nextInt() == 1) {
                inicializarEstructuras(equipos, ciudades, partidos);
            }
        } catch (Exception e) {
            Log.write("Error al leer la opcion del usuario: " + e.getLocalizedMessage());
        }
        int opcion;
        do {
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
                    menuPartidos.menu(partidos, equipos);
                    break;
                default:
                    break;
            }

        } while (opcion != 0);
        sc.close();
    }

    private static void inicializarEstructuras(Avl equipos, Grafo ciudades, TablaHash partidos){
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Cola tempPartidos = new Cola();
            Cola tempRutas = new Cola();
            while ((line = reader.readLine()) != null) {
                String[] typeData = line.split(":");
                String[] data = typeData[1].split(";");
                switch (typeData[0]) {
                    case "E":
                        Equipo equipo = new Equipo(
                            data[0].toLowerCase(), 
                            data[1].toLowerCase(), 
                            data[2].toLowerCase()
                        );
                        equipos.insertar(equipo);
                        break;
                    case "C":
                        Ciudad ciudad = new Ciudad(
                            data[0], 
                            Boolean.parseBoolean(data[1].toLowerCase()), 
                            Boolean.parseBoolean(data[2].toLowerCase())
                        );
                        ciudades.insertarVertice(ciudad);
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
                            data[0].toLowerCase(), 
                            data[1].toLowerCase(), 
                            Integer.parseInt(data[2])
                        };
                        tempRutas.poner(tempRuta);
                        break;
                    default:
                        System.out.println("Se detecto una linea invalida en el archivo.");
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
            Ciudad ciudad1 = menuCiudades.buscarCiudad(ciudades, ((String) tempRuta[0]));
            Ciudad ciudad2 = menuCiudades.buscarCiudad(ciudades, ((String) tempRuta[1]));
            if (ciudad1 != null && ciudad2 != null) {
                Ruta aux = new Ruta(((int) tempRuta[2]), ciudad1, ciudad2);
                ciudades.insertarArco(ciudad1, ciudad2, aux);
            }
        }
    }

    private static void inicializarPartidos(Avl equipos, TablaHash partidos, Cola tempPartidos, Grafo ciudades){
        while (!tempPartidos.esVacia()) {
            Object[] tempPartido = (Object[]) tempPartidos.obtenerFrente();
            tempPartidos.sacar();
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
            }
        }
        
    }
}
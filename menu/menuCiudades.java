package menu;

import estructuras.grafo.Grafo;
import estructuras.lineales.Lista;
import objetos.Ciudad;

public class menuCiudades {
    public static void menu(Grafo ciudades){

    }

    public static Ciudad buscarCiudad(Grafo ciudades, String nombreCiudad){
        Ciudad ciudad = null;
        Lista lista = ciudades.listarEnProfundidad();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Ciudad aux = (Ciudad) lista.recuperar(i);
            if (aux.getNombre().equalsIgnoreCase(nombreCiudad)) {
                ciudad = aux;
                break;
            }
        }
        return ciudad;

    }
}

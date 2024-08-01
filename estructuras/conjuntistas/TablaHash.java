package estructuras.conjuntistas;

import estructuras.lineales.Lista;
import objetos.Dominio;
import objetos.Rango;

import java.util.Arrays;

public class TablaHash {
    private int tamaño;
    private NodoHashMapeo[] tabla;
    private int primo;

    public TablaHash(int size){
        tabla = new NodoHashMapeo[size];
        tamaño = size-1;
        primo = getPrimoCercano(size);
    }

    /**
     * Asocia un dominio con un rango en la tabla hash mapeo con magia negra
     * @param dominio un objeto de tipo Dominio
     * @param rango un objeto de tipo Rango
     * @return true si se pudo asociar el dominio con el rango, false en caso contrario
     */
    public boolean asociar(Dominio dominio, Rango rango){
        boolean exito = false;
        NodoHashMapeo elemento = new NodoHashMapeo(dominio, rango, null);
        int pos = dominio.getClave() % primo;
        NodoHashMapeo bucket = tabla[pos];
        if (bucket != null) {
            while (bucket.getEnlace() != null) {
                if (bucket.getDominio().toString().equals(dominio.toString())) {
                    bucket.agregarRango(rango);
                    exito = true;
                    break;
                }
                bucket = bucket.getEnlace();
            }
            if (!exito) {
                if (bucket.getDominio().toString().equals(dominio.toString())) {
                    bucket.agregarRango(rango);
                }else{
                    bucket.setEnlace(elemento);
                }
                exito = true;
            }
        }else{
            tabla[pos] = elemento;
            exito = true;
        }

        return exito;
    }

    /* public boolean desasociar(dominioPartido dominio){
        boolean exito = false;
        NodoHashMapeo elemento = new NodoHashMapeo(dominio, null, null);
        int pos = dominio.hashCode() % primo;
        NodoHashMapeo bucket = tabla[pos];
        NodoHashMapeo anterior = null;
        while (bucket != null) {
            if (bucket.getDato().equals(elemento)) {
                anterior.setEnlace(bucket.getEnlace());
                exito = true;
            }
            anterior = bucket;
            bucket = bucket.getEnlace();
        }
        return exito;
    } */

    /**
     * Obtiene todos los rangos asociados a un dominio
     * @param dominio un objeto de tipo Dominio
     * @return un objeto de tipo Lista con los rangos asociados al dominio
     */
    public Lista obtenerValor(Dominio dominio){
        Lista lista = new Lista();
        int pos = dominio.getClave() % primo;
        NodoHashMapeo bucket = tabla[pos];
        while (bucket != null) {
            if (bucket.getDominio().equals(dominio)) {
                lista = bucket.getRango();
                break;
            }
            bucket = bucket.getEnlace();
        }
        return lista;
    }

    /**
     * Obtiene todos los dominios de la tabla hash mapeo
     * @return un objeto de tipo Lista con los dominios de la tabla hash mapeo
     */
    public Lista obtenerDominios(){
        Lista lista = new Lista();
        int longitud = 1;
        for (int i = 0; i < tamaño; i++) {
            NodoHashMapeo bucket = tabla[i];
            while (bucket != null) {
                lista.insertar(bucket.getDominio(), longitud);
                longitud++;
                bucket = bucket.getEnlace();
            }
        }
        return lista;
    }

    /**
     * Elimina un dominio de la tabla hash mapeo
     */
    public void vaciar(){
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = null;
        }
    }

    /**
     * Verifica si la tabla hash mapeo esta vacia
     * @return true si la tabla hash mapeo esta vacia, false en caso contrario
     */
    public boolean esVacia(){
        boolean vacio = true;
        for (int i = 0; i < tamaño; i++) {
            if (tabla[i] != null) {
                vacio = false;
                break;
            }
        }
        return vacio;
    }


    /**
     * Metodo que devuelve un arreglo de booleanos con los numeros primos hasta n
     * @param n limite de los numeros primos
     * @return arreglo de booleanos con los numeros primos hasta n
     */
    private static boolean[] cribaDeEratostenes(int n){
        boolean[] esPrimo = new boolean[n];
        boolean exito = false;
        Arrays.fill(esPrimo, true);
        int inicio = 2;
        esPrimo[0] = false;
        esPrimo[1] = false;
        while (!exito) {
            esPrimo[inicio] = true;
            for (int i = 2; i*inicio < n; i++) {
                if (esPrimo[i*inicio]) {
                    esPrimo[i*inicio] = false;
                }
            }
            if (Math.pow(inicio, 2) < n) {
                do {
                    inicio++;
                } while (!esPrimo[inicio] && inicio < n);
            }else{
                exito = true;
            }
        }
        return esPrimo;
    }

    private static int getPrimoCercano(int numero){
        if (numero < 2) {
            return numero;
        }else{
            boolean[] numeroPrimos = cribaDeEratostenes(numero);
            numero--;
            while (!numeroPrimos[numero] && numero > 2) {
                numero--;
            }
        }
        return numero;
    }

    /**
     * Metodo que permite listar los elementos de la tabla hash mapeo
     * @return un objeto de tipo Lista con los elementos de la tabla hash mapeo
     */
    public Lista listar(){
        Lista lista = new Lista();
        for (int i = 0; i < tamaño; i++) {
            NodoHashMapeo bucket = tabla[i];
            while (bucket != null) {
                Object[] datos = new Object[2];
                datos[0] = bucket.getDominio();
                datos[1] = bucket.getRango();
                lista.insertar(datos, lista.longitud()+1);
                bucket = bucket.getEnlace();
            }
        }
        return lista;
    } 
}

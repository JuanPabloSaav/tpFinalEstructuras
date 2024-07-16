package estructuras.conjuntistas;

import estructuras.lineales.Lista;
import objetos.dominioPartido;
import objetos.rangoPartido;

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

    public boolean asociar(dominioPartido dominio, rangoPartido rango){
        boolean exito = false;
        NodoHashMapeo elemento = new NodoHashMapeo(dominio, rango, null);
        int pos = dominio.getClave() % primo;
        System.out.println("tamaño: "+ tamaño);
        System.out.println("elemnto: "+ elemento.toString() + " hash: "+ elemento.hashCode());
        System.out.println("pos: "+ pos);
        NodoHashMapeo bucket = tabla[pos];
        if (bucket != null) {
            while (bucket.getEnlace() != null) {
                bucket = bucket.getEnlace();
            }
            bucket.setEnlace(elemento);
            exito = true;
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

    public Lista obtenerValor(dominioPartido dominio){
        Lista lista = new Lista();
        int pos = dominio.getClave() % primo;
        int i = 1;
        NodoHashMapeo bucket = tabla[pos];
        while (bucket != null) {
            lista.insertar(bucket, i);
            i++;
            bucket = bucket.getEnlace();
        }
        return lista;
    }

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

    public Lista obtenerRangos(){
        Lista lista = new Lista();
        int longitud = 1;
        for (int i = 0; i < tamaño; i++) {
            NodoHashMapeo bucket = tabla[i];
            while (bucket != null) {
                lista.insertar(bucket.getRango(), longitud);
                longitud++;
                bucket = bucket.getEnlace();
            }
        }
        return lista;
    }

    public void vaciar(){
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = null;
        }
    }

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

    public String toString(){
        String cadena = "";
        for (int i = 0; i <= tamaño; i++) {
            NodoHashMapeo bucket = tabla[i];
            cadena += "Posicion " + i + ": \n";
            while (bucket != null) {
                cadena += bucket.toString() + "\n";
                bucket = bucket.getEnlace();
            }
            cadena += "\n";
        }
        return cadena;
    }

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

    public Lista listar(){
        Lista lista = new Lista();
        for (int i = 0; i < tamaño; i++) {
            NodoHashMapeo bucket = tabla[i];
            while (bucket != null) {
                Object[] datos = {bucket.getDominio(), bucket.getRango()};
                lista.insertar(datos, lista.longitud()+1);
                bucket = bucket.getEnlace();
            }
        }
        return lista;
    }
}

package estructuras.conjuntistas;

import objetos.Dominio;
import objetos.Rango;
import estructuras.lineales.Lista;

public class NodoHashMapeo {
    private Dominio dominio;
    private Lista rango = new Lista();
    private NodoHashMapeo enlace;

    public NodoHashMapeo(Dominio dominio, Rango rango, NodoHashMapeo enlace){
        this.dominio = dominio;
        //podria solicitar la lista en vez del rango pero no creo que en al primera creacion de un
        //NodoHash se tenga una lista de rangos > 1 elemento
        this.rango.insertar(rango, 1);
        //el enlace puede ser null
        this.enlace = enlace;
    }

    /**
     * Metodo que permite obtener el dominio del nodo
     * @return un objeto de tipo Dominio
     */
    public Dominio getDominio() {
        return dominio;
    }

    /**
     * Metodo que permite modificar el dominio del nodo
     * @param dominio un objeto de tipo Dominio
     */
    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    /**
     * Metodo que permite obtener el rango del nodo
     * @return una lista con los rangos del nodo
     */
    public Lista getRango() {
        return rango.clone();
    }

    /**
     * Metodo que permite modificar el rango del nodo
     * @param rango una lista con los rangos del nodo
     */
    public void setRango(Lista rango) {
        this.rango = rango;
    }
    
    /**
     * Metodo que permite agregar un rango a la lista de rangos del nodo
     * @param rango un objeto de tipo Rango
     */
    public void agregarRango(Rango rango){
        //pos 1 para que siempre este primero el rango mas reciente
        this.rango.insertar(rango, 1);
    }

    /**
     * Metodo que permite obtener el enlace del nodo
     * @return un nodoHashMapeo con el enlace del nodo
     */
    public NodoHashMapeo getEnlace() {
        return enlace;
    }

    /**
     * Metodo que permite modificar el enlace del nodo
     * @param enlace un nodoHashMapeo con el enlace del nodo
     */
    public void setEnlace(NodoHashMapeo enlace) {
        this.enlace = enlace;
    }

    /**
     * Metodo que permite comparar dos nodos por su dominio
     * @param nodo un nodoHashMapeo
     * @return true si los dominios son iguales, false en caso contrario
     */
    public boolean equals(NodoHashMapeo nodo){
        return dominio.equals(nodo.getDominio());
    }

    /**
     * Metodo que permite comparar dos nodos por su dominio
     * @param nodo un nodoHashMapeo
     * @return true si los dominios son iguales, false en caso contrario
     */
    public int hashCode(){
        return dominio.hashCode();
    }

    @Override
    public String toString(){
        //Mejor no mostrar el rango para evitar problemas de impresion (al fin y al cabo es una lista)
        return "Dominio: "+ dominio.toString() + "\nHash: "+ hashCode() +"\n";
    }
}

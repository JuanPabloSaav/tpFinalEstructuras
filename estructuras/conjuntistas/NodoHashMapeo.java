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

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public Lista getRango() {
        return rango.clone();
    }

    public void setRango(Lista rango) {
        this.rango = rango;
    }
    
    public void agregarRango(Rango rango){
        //pos 1 para que siempre este primero el rango mas reciente
        this.rango.insertar(rango, 1);
    }

    public NodoHashMapeo getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeo enlace) {
        this.enlace = enlace;
    }

    public boolean equals(NodoHashMapeo nodo){
        return dominio.equals(nodo.getDominio());
    }

    public int hashCode(){
        return dominio.hashCode();
    }

    public String toString(){
        //Mejor no mostrar el rango para evitar problemas de impresion (al fin y al cabo es una lista)
        return "Dominio: "+ dominio.toString() + "\nHash: "+ hashCode() +"\n";
    }
}

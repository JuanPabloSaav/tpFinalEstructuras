package estructuras.conjuntistas;

import objetos.dominioPartido;
import objetos.rangoPartido;

public class NodoHashMapeo {
    private dominioPartido dominio;
    private rangoPartido rango;
    private NodoHashMapeo enlace;

    public NodoHashMapeo(dominioPartido dominio, rangoPartido rango, NodoHashMapeo enlace){

        this.dominio = dominio;
        this.rango = rango;
        this.enlace = enlace;
    }

    public dominioPartido getDominio() {
        return dominio;
    }

    public void setDominio(dominioPartido dominio) {
        this.dominio = dominio;
    }

    public rangoPartido getRango() {
        return rango;
    }

    public void setRango(rangoPartido rango) {
        this.rango = rango;
    }

    public NodoHashMapeo getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeo enlace) {
        this.enlace = enlace;
    }

    public int hashCode(){
        return dominio.hashCode();
    }

    public String toString(){
        return "Dominio: "+ dominio.toString() + "\nRango: "+ rango.toString() + "\nHash: "+ hashCode() +"\n";
    }
}

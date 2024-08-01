package estructuras.conjuntistas;
@SuppressWarnings({"rawtypes"})


public class NodoAvl {
    private Comparable elem;
    private int altura;
    private NodoAvl izquierdo;
    private NodoAvl derecho;

    public NodoAvl(Comparable elem){
        this.elem = elem;
        izquierdo = null;
        derecho = null;
        recalcularAltura();
    }

    public NodoAvl(Comparable elem, NodoAvl izquierdo, NodoAvl derecho){
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        recalcularAltura();
    }

    /**
     * Metodo que devuelve el elemento del nodo
     * @return elemento del nodo
     */
    public Comparable getElem(){
        return this.elem;
    }

    /**
     * Metodo que devuelve el hijo izquierdo del nodo
     * @return hijo izquierdo del nodo
     */
    public NodoAvl getIzquierdo(){
        return this.izquierdo;
    }

    /**
     * Metodo que devuelve el hijo derecho del nodo
     * @return hijo derecho del nodo
     */
    public NodoAvl getDerecho(){
        return this.derecho;
    }

    /**
     * Metodo que devuelve la altura del nodo
     * @return altura del nodo
     */
    public int getAltura(){
        return this.altura;
    }

    /**
     * Metodo que permite modificar el elemento del nodo
     * @param elem elemento del nodo
     */
    public void setElem(Comparable elem){
        this.elem = elem;
    }

    /**
     * Metodo que permite modificar el hijo izquierdo del nodo
     * @param izquierdo hijo izquierdo del nodo
     */
    public void setIzquierdo(NodoAvl izquierdo){
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAvl derecho){
        this.derecho = derecho;
    }

    public void recalcularAltura(){
        int alturaIzquierdo = 0, alturaDerecho = 0;
        if (izquierdo != null) {
            alturaIzquierdo = recalcularAlturaAux(izquierdo, alturaIzquierdo);

        }
        if (derecho != null) {
            alturaDerecho = recalcularAlturaAux(derecho, alturaDerecho);

        }
        if (alturaIzquierdo > alturaDerecho) {
            this.altura = alturaIzquierdo;
        }else{
            this.altura = alturaDerecho;
        }
    }

    private int recalcularAlturaAux(NodoAvl nodo, int alturaActual){
        if (nodo != null) {
            alturaActual++;
            int alturaDerecho = 0, alturaIzquierdo = 0;
            if (nodo.izquierdo != null) {
                alturaIzquierdo = recalcularAlturaAux(nodo.izquierdo, alturaActual);
            }
            if (nodo.derecho != null) {
                alturaDerecho = recalcularAlturaAux(nodo.derecho, alturaActual);
            }
            if (alturaIzquierdo > alturaDerecho) {
                alturaActual += alturaIzquierdo;
            }else{
                alturaActual += alturaDerecho;
            }
        }
        return alturaActual;
    }

    public String toString(){
        String cadena = "Padre: "+elem.toString() + "-> hijo izquierdo: ";
        if (izquierdo != null) {
            cadena += izquierdo.getElem().toString() + " ";
        }
        cadena += "hijo derecho: ";
        if (derecho != null) {
            cadena += derecho.getElem().toString() + " ";
        }
        return cadena;
    }
}

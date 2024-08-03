package estructuras.conjuntistas;
@SuppressWarnings({"rawtypes"})


public class NodoAvl {
    private Comparable elem;
    private int altura;
    private NodoAvl izquierdo;
    private NodoAvl derecho;

    public NodoAvl(Comparable elem){
        this.elem = elem;
        altura = 0;
    }

    public NodoAvl(Comparable elem, NodoAvl izquierdo, NodoAvl derecho){
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        altura = 0;
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
        int altIzq = -1;
        int altDer = -1;
        if (izquierdo != null) {
            altIzq = izquierdo.getAltura();
        }
        if (derecho != null) {
            altDer = derecho.getAltura();
        }
        altura = Math.max(altIzq, altDer) + 1;
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

package estructuras.grafo;


public class NodoVertice {
    private Object elem;
    private NodoEnlace primerEnlace;
    private NodoVertice sigVertice;

    public NodoVertice(NodoEnlace primerEnlace, NodoVertice sigVertice, Object elem) {
        this.primerEnlace = primerEnlace;
        this.sigVertice = sigVertice;
        this.elem = elem;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoEnlace getPrimerEnlace() {
        return primerEnlace;
    }

    public void setPrimerEnlace(NodoEnlace primerEnlace) {
        this.primerEnlace = primerEnlace;
    }

    public NodoVertice getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVertice sigVertice) {
        this.sigVertice = sigVertice;
    }

    public boolean equals(NodoVertice obj) {
        return elem.equals(obj.getElem());
    }

    @Override
    public String toString() {
        return elem.toString();
    }
}

package estructuras.grafo;

public class NodoEnlace {
    private NodoVertice vertice;
    private NodoEnlace sigEnlace;

    public NodoEnlace(NodoVertice vertice, NodoEnlace sigEnlace) {
        this.vertice = vertice;
        this.sigEnlace = sigEnlace;
    }

    public NodoVertice getVertice() {
        return vertice;
    }

    public void setVertice(NodoVertice vertice) {
        this.vertice = vertice;
    }

    public NodoEnlace getSigEnlace() {
        return sigEnlace;
    }

    public void setSigEnlace(NodoEnlace sigEnlace) {
        this.sigEnlace = sigEnlace;
    }

    @Override
    public String toString() {
        return "NodoEnlace{" + "vertice=" + vertice + ", sigEnlace=" + sigEnlace + '}';
    }
}

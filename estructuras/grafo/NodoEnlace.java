package estructuras.grafo;

public class NodoEnlace {
    private NodoVertice vertice;
    private NodoEnlace sigEnlace;
    private double etiqueta;

    public NodoEnlace(NodoVertice vertice, NodoEnlace sigEnlace, double etiqueta) {
        this.vertice = vertice;
        this.sigEnlace = sigEnlace;
        this.etiqueta = etiqueta;
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

    public double getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(double etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return vertice.toString() + " (" + etiqueta + ")";
    }
}

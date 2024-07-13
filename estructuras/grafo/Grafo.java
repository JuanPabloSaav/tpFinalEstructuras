package estructuras.grafo;

import estructuras.lineales.Lista;

public class Grafo {
    private NodoVertice inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        if (inicio != null && !existeVertice(elem)) {
            NodoVertice aux = new NodoVertice(null, inicio, elem);
            inicio = aux;
        }else{
            inicio = new NodoVertice(null, null, elem);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object elem){
        boolean exito = false;
        if (inicio != null) {
            NodoVertice aux = inicio;
            NodoVertice ant = null;
            while (aux != null) {
                if (aux.getElem().equals(elem)) {
                    if (ant != null) {
                        ant.setSigVertice(aux.getSigVertice());
                    }else{
                        inicio = aux.getSigVertice();
                    }
                    exito = true;
                    
                }
                ant = aux;
                aux = aux.getSigVertice();
            }
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta){
        boolean exito = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            if (nodoOrigen != null && nodoDestino != null) {
                if (!existeArco(nodoOrigen, nodoDestino)) {
                    nodoOrigen.setPrimerEnlace(new NodoEnlace(nodoDestino, nodoOrigen.getPrimerEnlace(), etiqueta));
                    nodoDestino.setPrimerEnlace(new NodoEnlace(nodoOrigen, nodoDestino.getPrimerEnlace(), etiqueta));
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean existeArco(Object origen, Object destino){
        boolean existe = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            if (nodoOrigen != null && nodoDestino != null) {
                existe = existeArco(nodoOrigen, nodoDestino);
            }
        }
        return existe;
    }

    private boolean existeArco(NodoVertice origen, NodoVertice destino){
        boolean existe = false;
        NodoEnlace aux = origen.getPrimerEnlace();
        while (aux != null && !existe) {
            if (aux.getVertice().equals(destino)) {
                existe = true;
            }
            aux = aux.getSigEnlace();
        }
        return existe;
    }

    public boolean eliminarArco(Object origen, Object destino){
        boolean exito = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            if (nodoOrigen != null) {
                NodoEnlace aux = nodoOrigen.getPrimerEnlace();
                NodoVertice nodoDestino = null;
                while (aux != null) {
                    if (aux.getSigEnlace() != null && aux.getSigEnlace().getVertice().equals(destino)) {
                        nodoDestino = aux.getSigEnlace().getVertice();
                        aux.setSigEnlace(aux.getSigEnlace().getSigEnlace());
                    }
                    aux = aux.getSigEnlace();
                }
                aux = nodoDestino.getPrimerEnlace();
                while (aux != null) {
                    if (aux.getSigEnlace() != null && aux.getSigEnlace().getVertice().equals(nodoOrigen)) {
                        aux.setSigEnlace(aux.getSigEnlace().getSigEnlace());
                        exito = true;
                    }
                    aux = aux.getSigEnlace();
                }
            }
        }
        return exito;
    }

    public boolean existeVertice(Object elem){
        boolean existe = false;
        if (inicio != null && buscarVertice(elem) != null) {
            existe = true;
        }
        return existe;
    }

    private NodoVertice buscarVertice(Object elem){
        NodoVertice aux = inicio;
        while (aux != null) {
            if (aux.getElem().equals(elem)) {
                break;
            }
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean existeCamino(Object origen, Object destino){
        boolean existe = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            if (nodoOrigen != null && nodoDestino != null) {
                Lista lista = new Lista();
                existe = existeCaminoAux(nodoOrigen, nodoDestino, lista);
            }
        }
        return existe;
    }

    //TODO: probar
    private boolean existeCaminoAux(NodoVertice nodo, NodoVertice destino, Lista visitados){
        boolean existe = false;
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, 1);
            NodoEnlace aux = nodo.getPrimerEnlace();
            while (aux != null && !existe) {
                if (aux.getVertice().equals(destino)) {
                    existe = true;
                }else{
                    existe = existeCaminoAux(aux.getVertice(), destino, visitados);
                }
                aux = aux.getSigEnlace();
            }
        }
        return existe;
    }

    private boolean nodoVisitado(NodoVertice nodo, Lista visitados){
        boolean visitado = false;
        int longitud = visitados.longitud();
        for (int i = 1; i <= longitud; i++) {
            if (visitados.recuperar(i).equals(nodo)) {
                visitado = true;
                break;
            }
        }
        return visitado;
    }

    public Lista caminoMasCorto(NodoVertice nodo, NodoVertice destino){
        Lista camino = new Lista();
        camino = caminoMasCortoAux(nodo, destino);
        return camino;
    }

    private Lista caminoMasCortoAux(NodoVertice nodo, NodoVertice destino){
        Lista menorCamino = new Lista();
        if (nodo != null) {
            if (nodo.equals(destino)) {
                menorCamino.insertar(nodo, 1);
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                Lista caminoRecuperado = new Lista();
                menorCamino = new Lista();
                while (aux != null) {
                    caminoRecuperado = caminoMasCortoAux(aux.getVertice(), destino);
                    if (caminoRecuperado.longitud() < menorCamino.longitud()) {
                        menorCamino = caminoRecuperado;
                    }
                    aux.getSigEnlace();
                }
                menorCamino.insertar(nodo, 1);
            }
        }
        return menorCamino;
    }

    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        Lista lista = new Lista();
        if (inicio != null) {
            visitados = listarEnProfundidadAux(inicio, visitados, lista);
        }
        return lista;
    }

    private Lista listarEnProfundidadAux(NodoVertice nodo, Lista visitados, Lista lista){
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, 1);
            lista.insertar(nodo.getElem(), 1);
            NodoEnlace aux = nodo.getPrimerEnlace();
            while (aux != null) {
                listarEnProfundidadAux(aux.getVertice(), visitados, lista);
                aux = aux.getSigEnlace();
            }
        }
        return visitados;
    }

    public Lista listarEnAnchura(){
        Lista visitados = new Lista();
        Lista lista = new Lista();
        if (inicio != null) {
            visitados = listarEnAnchuraAux(inicio, visitados, lista);
        }
        return lista;
    }

    private Lista listarEnAnchuraAux(NodoVertice nodo, Lista visitados, Lista lista){
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, 1);
            lista.insertar(nodo.getElem(), 1);
            NodoEnlace aux = nodo.getPrimerEnlace();
            while (aux != null) {
                visitados.insertar(aux.getVertice(), 1);
                lista.insertar(aux.getVertice().getElem(), 1);
                aux = aux.getSigEnlace();
            }
            aux = nodo.getPrimerEnlace();
            while (aux != null) {
                listarEnAnchuraAux(aux.getVertice(), visitados, lista);
                aux = aux.getSigEnlace();
            }
        }
        return visitados;
    }
}

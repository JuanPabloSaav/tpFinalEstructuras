package estructuras.grafo;

import estructuras.lineales.Lista;

public class Grafo {
    private NodoVertice inicio;

    /**
     * Constructor de la clase Grafo
     */
    public Grafo() {
        this.inicio = null;
    }

    /**
     * Inserta un vertice en el grafo si no existe, si ya existe no lo inserta
     * @param elem Elemento a insertar
     * @return true si se inserto correctamente, false en caso contrario
     */
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

    /**
     * Elimina un vertice del grafo si existe
     * @param elem Elemento a eliminar
     * @return true si se elimino correctamente, false en caso contrario
     */
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

    /**
     * Inserta un arco entre dos vertices si no existe, si ya existe no lo inserta
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @param etiqueta Etiqueta del arco
     * @return true si se inserto correctamente, false en caso contrario
     */
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

    /**
     * Verifica si existe un arco entre dos vertices
     * @param origen
     * @param destino
     * @return
     */
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

    /**
     * Elimina un arco entre dos vertices
     * @param origen Vertice origen
     * @param destino Vertice destino
     * @return true si se elimino correctamente, false en caso contrario
     */
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

    /**
     * Verifica si existe un vertice en el grafo
     * @param elem Elemento a buscar
     * @return true si existe, false en caso contrario
     */
    public boolean existeVertice(Object elem){
        boolean existe = false;
        if (inicio != null && buscarVertice(elem) != null) {
            existe = true;
        }
        return existe;
    }

    /**
     * Busca un vertice en el grafo
     * @param elem Elemento a buscar
     * @return NodoVertice si lo encuentra, null en caso contrario
     */
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

    /**
     * Verifica si existe un arco entre dos vertices
     * @param origen Vertice origen
     * @param destino Vertice destino
     * @return true si existe, false en caso contrario
     */
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
    /**
     * Verifica si existe un camino entre dos vertices
     * @param nodo Vertice origen
     * @param destino Vertice destino
     * @param visitados Lista de vertices visitados
     * @return true si existe, false en caso contrario
     */
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

    /**
     * Verifica si un nodo ya fue visitado
     * @param nodo Vertice a verificar
     * @param visitados Lista de vertices visitados
     * @return true si ya fue visitado, false en caso contrario
     */
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

    /**
     * Devuelve un camino entre dos vertices
     * @param nodo Vertice origen
     * @param destino Vertice destino
     * @return Lista con el camino
     */
    public Lista caminoMasCorto(Object nodo, Object destino){
        Lista camino = new Lista();
        NodoVertice nodoOrigen = buscarVertice(nodo);
        NodoVertice nodoDestino = buscarVertice(destino);
        camino = caminoMasCortoAux(nodoOrigen, nodoDestino);
        return camino;
    }

    /**
     * Devuelve un camino entre dos vertices
     * @param nodo Vertice origen
     * @param destino Vertice destino
     * @return Lista con el camino
     */
    private Lista caminoMasCortoAux(NodoVertice nodo, NodoVertice destino){
        Lista menorCamino = new Lista();
        if (nodo != null) {
            if (nodo.equals(destino)) {
                menorCamino.insertar(nodo, 1);
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                Lista caminoRecuperado = new Lista();
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

    /**
     * Lista los vertices del grafo en profundidad
     * @return Lista con los vertices
     */
    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        Lista lista = new Lista();
        if (inicio != null) {
            visitados = listarEnProfundidadAux(inicio, visitados, lista);
        }
        return lista;
    }

    /**
     * Lista los vertices del grafo en profundidad
     * @param nodo Vertice origen
     * @param visitados Lista de vertices visitados
     * @param lista Lista con los vertices
     * @return Lista con los vertices
     */
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

    /**
     * Lista los vertices del grafo en anchura
     * @return Lista con los vertices
     */
    public Lista listarEnAnchura(){
        Lista visitados = new Lista();
        Lista lista = new Lista();
        if (inicio != null) {
            visitados = listarEnAnchuraAux(inicio, visitados, lista);
        }
        return lista;
    }

    /**
     * Lista los vertices del grafo en anchura
     * @param nodo Vertice origen
     * @param visitados Lista de vertices visitados
     * @param lista Lista con los vertices
     * @return Lista con los vertices
     */
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

    public Lista listarVertices(){
        Lista lista = new Lista();
        NodoVertice aux = inicio;
        while (aux != null) {
            lista.insertar(aux.getElem(), 1);
            aux = aux.getSigVertice();
        }
        return lista;
    }
}

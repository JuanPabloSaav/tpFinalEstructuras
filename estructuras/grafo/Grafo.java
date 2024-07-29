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
            exito = true;
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
    public boolean insertarArco(Object origen, Object destino, double etiqueta){
        boolean exito = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            if (nodoOrigen != null && nodoDestino != null) {
                if (!existeArco(nodoOrigen, nodoDestino)) {
                    NodoEnlace aux = nodoOrigen.getPrimerEnlace();
                    NodoEnlace anterior = null;
                    while (aux != null) {
                        anterior = aux;
                        aux = aux.getSigEnlace();
                    }
                    if (anterior == null) {
                        nodoOrigen.setPrimerEnlace(new NodoEnlace(nodoDestino, null, etiqueta));
                    }else{
                        anterior.setSigEnlace(new NodoEnlace(nodoDestino, null, etiqueta));
                    }
                    
                    aux = nodoDestino.getPrimerEnlace();
                    anterior = null;
                    while (aux != null) {
                        anterior = aux;
                        aux = aux.getSigEnlace();
                    }
                    if (anterior == null) {
                        nodoDestino.setPrimerEnlace(new NodoEnlace(nodoOrigen, null, etiqueta));
                    }else{
                        anterior.setSigEnlace(new NodoEnlace(nodoOrigen, null, etiqueta));
                    }
                    exito = true;
                }
            }
        }
        return exito;
    }

    /**
     * Verifica si existe un arco entre dos vertices (solo si existe uno entre esos dos, 
     * no verifica si hay un camino)
     * @param origen Vertice origen
     * @param destino Vertice destino
     * @return true si existe, false en caso contrario
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
                NodoEnlace ant = null;
                while (aux != null) {
                    if (aux.getVertice().getElem().equals(destino)) {
                        if (ant != null) {
                            nodoDestino = aux.getVertice();
                            ant.setSigEnlace(aux.getSigEnlace());
                        }else{
                            nodoDestino = aux.getVertice();
                            nodoOrigen.setPrimerEnlace(aux.getSigEnlace());
                        }
                        break;
                    }
                    ant = aux;
                    aux = aux.getSigEnlace();
                }
                aux = nodoDestino.getPrimerEnlace();
                ant = null;
                while (aux != null && !exito) {
                    if (aux.getVertice().getElem().equals(origen)) {
                        if (ant != null) {
                            ant.setSigEnlace(aux.getSigEnlace());
                        }else{
                            nodoDestino.setPrimerEnlace(aux.getSigEnlace());
                        }
                        exito = true;
                    }
                    ant = aux;
                    aux = aux.getSigEnlace();
                }
            }
        }
        return exito;
    }

    public double getTiempoArco(Object origen, Object destino){
        double tiempo = 0.0;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            if (nodoOrigen != null) {
                NodoEnlace aux = nodoOrigen.getPrimerEnlace();
                while (aux != null) {
                    if (aux.getVertice().getElem().equals(destino)) {
                        tiempo = aux.getEtiqueta();
                        break;
                    }
                    aux = aux.getSigEnlace();
                }
            }
        }
        return tiempo;
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
        Lista visitados = new Lista();
        if (inicio != null && (nodoOrigen != null && nodoDestino != null)) {
            camino = caminoMasCortoAux(nodoOrigen, nodoDestino, visitados);
        }
        return camino;
    }

    /**
     * Devuelve un camino entre dos vertices
     * @param nodo Vertice origen
     * @param destino Vertice destino
     * @return Lista con el camino
     */
    private Lista caminoMasCortoAux(NodoVertice nodo, NodoVertice destino, Lista visitados){
        Lista menorCamino = new Lista();
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, visitados.longitud() + 1);
            if (nodo.equals(destino)) {
                menorCamino.insertar(nodo.getElem(), 1);
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                Lista caminoRecuperado = new Lista();
                while (aux != null) {
                    caminoRecuperado = caminoMasCortoAux(aux.getVertice(), destino, visitados);
                    if (!caminoRecuperado.esVacia()) {
                        if (menorCamino.esVacia() || caminoRecuperado.longitud() < menorCamino.longitud()) {
                            menorCamino = caminoRecuperado;
                        }
                    }
                    aux = aux.getSigEnlace();
                }
                if (!menorCamino.esVacia()) {
                    menorCamino.insertar(nodo.getElem(), 1);
                }
            }
            visitados.eliminar(visitados.longitud());
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

    /**
     * Devuelve el camino con menor tiempo entre dos vertices
     * @param inicio Vertice de inicio
     * @param destino Vertice de destino
     * @return Lista con el camino de menor tiempo, si no existe retorna una lista vacia
     */

    public Lista caminoMenorTiempo(Object inicio, Object destino){
        Object[] camino = new Object[2];
        camino[0] = new Lista();// Se le asigna a camino una lista vacia para evitar un retorno null
        Lista visitados = new Lista();
        // Se busca el nodo de inicio y destino
        NodoVertice nodoInicio = buscarVertice(inicio);
        NodoVertice nodoDestino = buscarVertice(destino);
        if (inicio != null && nodoInicio != null && nodoDestino != null) {
            camino = buscarCaminoMenorTiempoAux(nodoInicio, nodoDestino, visitados);
        }
        // Se retorna el camino (podria quedar bien mostrar el tiempo pero no se si es necesario)
        return (Lista) camino[0];
    }

    /*
     * Metodo auxiliar para buscar el camino con menor tiempo entre dos vertices
     * Este metodo usa recursividad para buscar el camino con menor tiempo
     * comenzando desde el vertice de inicio, recorre hasta el vertice destino y desde ahi comienza a agregar
     * los vertices al camino, lo retorna y prueba con el siguiente vertice asociado al vertice de inicio en busca
     * de un camino mas corto, si el metodo devuelve una lista con un tiempo menor esta sobreescribe la anterior.
     */
    private Object[] buscarCaminoMenorTiempoAux(NodoVertice nodo, NodoVertice destino, Lista visitados){
        // Se crea un arreglo de dos posiciones, la primera es una lista que contendra el camino y la segunda el tiempo
        Object[] camino = new Object[2];
        camino[0] = new Lista();
        camino[1] = 0.0;
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, visitados.longitud() + 1);
            // Si el nodo es igual al destino se agrega a la lista camino y se retorna
            if (nodo.equals(destino)) {
                ((Lista) camino[0]).insertar(nodo.getElem(), 1);
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                Object[] caminoRecuperado = new Object[2];
                caminoRecuperado[0] = new Lista();
                caminoRecuperado[1] = 0.0;
                // Este while busca entre todos los vertices asociados al vertice actual el camino mas corto
                while (aux != null) {
                    // Se usa el vertice asociado al inicio para buscar el camino mas corto
                    caminoRecuperado = buscarCaminoMenorTiempoAux(aux.getVertice(), destino, visitados);
                    Lista temp = (Lista) caminoRecuperado[0];
                    // Si el camino recuperado no es vacio se le suma el tiempo del arco actual
                    if (!temp.esVacia()){
                        (caminoRecuperado[1]) = ((double) caminoRecuperado[1]) + aux.getEtiqueta();
                        // Si el camino actual es vacio o el tiempo del camino recuperado es menor se sobreescribe
                        if (((Lista) camino[0]).esVacia() || (((double) caminoRecuperado[1]) <= ((double) camino[1]))) {
                            camino[0] = caminoRecuperado[0];
                            camino[1] = caminoRecuperado[1];
                        }
                    }
                    aux = aux.getSigEnlace();
                }
                // Si el camino actual no es vacio se agrega el vertice actual al camino
                if (!((Lista) camino[0]).esVacia()) {
                    ((Lista) camino[0]).insertar(nodo.getElem(), 1);
                }
            }
            // Se elimina el vertice actual de la lista de visitados por si se quiere buscar otro camino que contenga este vertice
            visitados.eliminar(visitados.longitud());
        }
        return camino;
    }

    public Lista listarArcos(){
        Lista visitados = new Lista();
        Lista lista = new Lista();
        if (inicio != null) {
            visitados = listarArcosAux(inicio, visitados, lista);
        }
        return lista;
    }

    private Lista listarArcosAux(NodoVertice nodo, Lista visitados, Lista lista){
        if (nodo != null && !nodoVisitado(nodo, visitados)) {
            visitados.insertar(nodo, 1);
            NodoEnlace aux = nodo.getPrimerEnlace();
            while (aux != null) {
                if (!nodoVisitado(aux.getVertice(), visitados)) {
                    Object[] arco = new Object[3];
                    arco[0] = nodo.getElem();
                    arco[1] = aux.getVertice().getElem();
                    arco[2] = aux.getEtiqueta();
                    lista.insertar(arco, 1);
                }
                aux = aux.getSigEnlace();
            }
            aux = nodo.getPrimerEnlace();
            while (aux != null) {
                listarArcosAux(aux.getVertice(), visitados, lista);
                aux = aux.getSigEnlace();
            }
        }
        return visitados;
    }

    public void vaciar(){
        inicio = null;
    }

    public boolean esVacio(){
        return inicio == null;
    }
}

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
        if (elem != null) {
            if (inicio != null && !existeVertice(elem)) {
                NodoVertice aux = new NodoVertice(null, inicio, elem);
                inicio = aux;
                exito = true;
            }else{
                inicio = new NodoVertice(null, null, elem);
                exito = true;
            }
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
            NodoVertice aux = inicio;//Nodo actual
            NodoVertice ant = null;//Nodo anterior a aux
            NodoVertice origen = null;//Nodo origen del arco a eliminar
            boolean encontrado = false;//flag para saber si se encontro el vertice
            /*
             * Se recorre la lista de vertices hasta encontrar el vertice a eliminar
             * si se encuentra se guarda el nodo origen del arco a eliminar
             * y se elimina el vertice de la lista
             * (El nodo se almacena para eliminar los arcos que lo contienen)
             */
            while (aux != null && !encontrado) {
                if (aux.getElem().equals(elem)) {
                    encontrado = true;
                    origen = aux;
                    if (ant != null) {
                        ant.setSigVertice(aux.getSigVertice());
                    }else{
                        inicio = aux.getSigVertice();
                    }
                }
                ant = aux;
                aux = aux.getSigVertice();
            }
            /*
             * Si se encontro el vertice se eliminan los arcos que lo contienen
             * buscando desde los enlaces del mismo nodo origen anteriormente encontrado
             */
            if (encontrado) {
                NodoEnlace enlace = origen.getPrimerEnlace();
                while (enlace != null) {
                    eliminarArcoAux(origen, enlace.getVertice());//se reutiliza el metodo de eliminar arco
                    enlace = enlace.getSigEnlace();
                }
                exito = true;
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
            //se buscan los nodos origen y destino
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            //si ambos nodos existen se procede a insertar el arco
            if (nodoOrigen != null && nodoDestino != null) {
                //si no existe el arco se procede a insertarlo
                if (!existeArcoAux(nodoOrigen, nodoDestino)) {
                    NodoEnlace aux = nodoOrigen.getPrimerEnlace();
                    //se inserta el arco en el nodo origen al inicio de la lista
                    if (aux != null) {//o crea un nuevo enlace y le asigna aux como siguiente
                        NodoEnlace temp = new NodoEnlace(nodoDestino, aux, etiqueta);
                        nodoOrigen.setPrimerEnlace(temp);
                    }else{//o crea un nuevo enlace y lo inserta al inicio de la lista
                        nodoOrigen.setPrimerEnlace(new NodoEnlace(nodoDestino, null, etiqueta));
                    }
                    
                    aux = nodoDestino.getPrimerEnlace();
                    //se inserta el arco en el nodo destino al inicio de la lista
                    if (aux != null) {
                        NodoEnlace temp = new NodoEnlace(nodoOrigen, aux, etiqueta);
                        nodoDestino.setPrimerEnlace(temp);
                    }else{
                        nodoDestino.setPrimerEnlace(new NodoEnlace(nodoOrigen, null, etiqueta));
                    }
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
                existe = existeArcoAux(nodoOrigen, nodoDestino);
            }
        }
        return existe;
    }

    /**
     * Verifica si existe un arco entre dos vertices (solo si existe uno entre esos dos, 
     * no verifica si hay un camino)
     * @param origen Vertice origen
     * @param destino Vertice destino
     * @return true si existe, false en caso contrario
     */
    private boolean existeArcoAux(NodoVertice origen, NodoVertice destino){
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
        NodoVertice origenVertice = buscarVertice(origen);
        NodoVertice destinoVertice = buscarVertice(destino);
        boolean exito = false;
        if (origenVertice != null && destinoVertice != null) {
            exito = eliminarArcoAux(origenVertice, destinoVertice);
        }
        return exito;
    }

    private boolean eliminarArcoAux(NodoVertice origen, NodoVertice destino){
        boolean exito = false;
        if (inicio != null) {
            if (origen != null) {
                NodoEnlace aux = origen.getPrimerEnlace();
                NodoEnlace ant = null;
                boolean encontrado = false;
                /*
                 * Se recorre la lista de enlaces del vertice origen hasta encontrar el vertice destino
                 * si se encuentra se elimina el arco
                 */
                while (aux != null && !encontrado) {
                    if (aux.getVertice().equals(destino)) {
                        if (ant != null) {
                            ant.setSigEnlace(aux.getSigEnlace());
                        }else{
                            origen.setPrimerEnlace(aux.getSigEnlace());
                        }
                        encontrado = true;
                    }
                    ant = aux;
                    aux = aux.getSigEnlace();
                }
                //Se reutilizan las variables para eliminar el arco en el vertice destino
                aux = destino.getPrimerEnlace();
                ant = null;
                /*
                 * Se recorre la lista de enlaces del vertice destino hasta encontrar el vertice origen
                 * si se encuentra se elimina el arco (no deberia existir caso en que no se encuentre)
                 */
                while (aux != null && !exito) {
                    if (aux.getVertice().equals(origen)) {
                        if (ant != null) {
                            ant.setSigEnlace(aux.getSigEnlace());
                        }else{
                            destino.setPrimerEnlace(aux.getSigEnlace());
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
            /*
            * Para evitar buscar dos veces el vertice destino se busca
            * desde el nodo origen hacia el destino y se obtiene el arco con el tiempo
            * Se podria usar buscarVertice(destino) pero al final si o si voy a tener que buscar
            * otra vez el vertice destino para obtener el tiempo
            */
            if (nodoOrigen != null) {
                NodoEnlace aux = nodoOrigen.getPrimerEnlace();
                boolean encontrado = false;
                while (aux != null && !encontrado) {
                    if (aux.getVertice().getElem().equals(destino)) {
                        tiempo = aux.getEtiqueta();
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
        if (buscarVertice(elem) != null) {
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
        boolean encontrado = false;
        NodoVertice nodo = null;
        while (aux != null && !encontrado) {
            if (aux.getElem().equals(elem)) {
                encontrado = true;
                nodo = aux; //un aplauso para el auxiliar
            }
            aux = aux.getSigVertice();
        }
        return nodo;
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
        int i = 1;
        while (i <= longitud && !visitado) {
            if (visitados.recuperar(i).equals(nodo)) {
                visitado = true;
            }
            i++;
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
            camino = caminoMasCortoAux(nodoOrigen, nodoDestino, visitados, 1, new int[1], camino, new Lista());
        }
        return camino;
    }

    /**
     * Devuelve un camino entre dos vertices
     * @param nodo Vertice origen
     * @param destino Vertice destino
     * @return Lista con el camino
     */
    //TODO: rever este metodo y evitar que busque por todos los caminos posibles
    private Lista caminoMasCortoAux(NodoVertice nodo, NodoVertice destino, Lista visitados, int cantActual, int[] cantMin, Lista camino , Lista caminoAux){
        if (nodo != null && !nodoVisitado(nodo, visitados) && (camino.esVacia() || cantActual < cantMin[0]) && cantMin[0] != 2) {
            visitados.insertar(nodo, 1); //se inserta el nodo en la lista de visitados
            //se mantiene una lista auxiliar para almacenar el camino actual
            caminoAux.insertar(nodo.getElem(), caminoAux.longitud() + 1);
            //se realiza un escaneo en anchura de todos los vertices adyacentes al nodo
            //si este es el destino se almacena el camino y se actualiza la cantidad minima
            Object[] encontrado = escaneoEnAnchura(nodo, destino, visitados, caminoAux);
            //dado que solo se quiere saber el camino mas corto se procede a ignorar encontrar[1]
            if ((boolean) encontrado[0]) {
                //cantMin[0] es cantActual+1 porque se cuenta el nodo destino encontrado en el escaneo
                cantMin[0] = cantActual+1;
                //caminoAux esta actualizado ya que lo unico con lo que se trabajo en escaneoEnAnchura
                //fue con su referencia
                camino = caminoAux.clone();
                //se elimina el destino del caminoAux cuando ya se guardo en camino
                caminoAux.eliminar(caminoAux.longitud());
            }else{//si hay mala suerte se sigue buscando como recorrido en profundidad
                NodoEnlace aux = nodo.getPrimerEnlace();
                //si cantMin[0] es 2 se detiene la busqueda porque ya se encontro el minimo camino posible
                while (aux != null && cantMin[0] != 2) { 
                    camino = caminoMasCortoAux(aux.getVertice(), destino, visitados, cantActual+1, cantMin, camino, caminoAux);
                    aux = aux.getSigEnlace();
                }
            }
            caminoAux.eliminar(caminoAux.longitud());
            visitados.eliminar(1);
        }
        return camino;
    }

    /**
     * Escanea al nodo en busca de vertices adyacentes que concuerden con el destino,
     * una vez encontrado se agrega al camino retorna un Object[] con un booleano que indica si se encontro
     * el destino y la etiqueta del arco que une el nodo con el destino
     * @param nodo Vertice origen desde donde se buscara el destino en sus adyacentes
     * @param destino Vertice destino
     * @param visitados Lista de vertices visitados para evitar buscar de mas
     * @param caminoAux Lista auxiliar que se usa para almacenar el camino
     * @return un Object[] que contiene en Object[0] un booleano que indica si se encontro el destino
     * y en Object[1] la etiqueta del arco que une el nodo con el destino
     */
    private Object[] escaneoEnAnchura(NodoVertice nodo, NodoVertice destino, Lista visitados, Lista caminoAux){
        Object[] encontrado = new Object[2];
        encontrado[0] = false;
        encontrado[1] = (double) 0;

        if (nodo != null) { //revisa si el nodo es nulo

            NodoEnlace aux = nodo.getPrimerEnlace();//obtiene el primer enlace del nodo

            //comienza a revisar todos los vertices adyacentes al nodo en busca del destino
            while (aux != null && !((boolean) encontrado[0])) {

                //si el vertice no fue visitado y es el destino se agrega al camino
                if (!nodoVisitado(aux.getVertice(), visitados) && aux.getVertice().equals(destino)) {
                    encontrado[1] = aux.getEtiqueta();
                    caminoAux.insertar(aux.getVertice().getElem(), caminoAux.longitud() + 1);
                    encontrado[0] = true;
                }

                //si no sigue buscando en los siguientes vertices adyacentes
                aux = aux.getSigEnlace();
            }
        }
        return encontrado;

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
        camino[1] = (double) 0;// Se le asigna a camino un valor double 0 para evitar un retorno null
        Lista visitados = new Lista();
        // Se busca el nodo de inicio y destino
        NodoVertice nodoInicio = buscarVertice(inicio);
        NodoVertice nodoDestino = buscarVertice(destino);
        if (inicio != null && nodoInicio != null && nodoDestino != null) {
            buscarCaminoMenorTiempoAux(nodoInicio, nodoDestino, visitados, 0, camino, new Lista());
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
    //TODO: testear este metodo
    private Lista buscarCaminoMenorTiempoAux(NodoVertice nodo, NodoVertice destino, Lista visitados, double cantActual, Object[] camino, Lista caminoAux){
        if (nodo != null && !nodoVisitado(nodo, visitados) && 
        (((double) camino[1]) > cantActual || ((double) camino[1]) == 0) ) {
            visitados.insertar(nodo, 1);
            caminoAux.insertar(nodo.getElem(), caminoAux.longitud() + 1);
            Object[] encontrado = escaneoEnAnchura(nodo, destino, visitados, caminoAux);
            if ((boolean) encontrado[0]) {
                camino[0] = caminoAux.clone();
                // se borra el destino del caminoAux cuando ya se guardo en camino
                caminoAux.eliminar(caminoAux.longitud());
                camino[1] = (cantActual + ((double) encontrado[1]));
            }else{
                NodoEnlace aux = nodo.getPrimerEnlace();
                while (aux != null) {
                    double temp = aux.getEtiqueta();
                    camino[0] = buscarCaminoMenorTiempoAux(aux.getVertice(), destino, visitados, (cantActual+temp), camino, caminoAux);
                    aux = aux.getSigEnlace();
                }
            }
            visitados.eliminar(1);
            caminoAux.eliminar(caminoAux.longitud());
        }
        return (Lista) camino[0];
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
            //se recorren los enlaces del nodo y se insertan en la lista
            while (aux != null) {
                //si el nodo destino no fue visitado se inserta en la lista
                if (!nodoVisitado(aux.getVertice(), visitados)) {
                    Object[] arco = new Object[3];
                    arco[0] = nodo.getElem();
                    arco[1] = aux.getVertice().getElem();
                    arco[2] = aux.getEtiqueta();
                    lista.insertar(arco, 1);
                }
                aux = aux.getSigEnlace();
            }
            //se recorren los vertices adyacentes al nodo
            NodoVertice temp = nodo.getSigVertice();
            while (temp != null) {
                listarArcosAux(temp, visitados, lista);
                temp = temp.getSigVertice();
            }
        }
        return visitados;
    }

    public boolean modificarEtiqueta(Object origen, Object destino, double etiqueta){
        boolean exito = false;
        if (inicio != null) {
            NodoVertice nodoOrigen = buscarVertice(origen);
            NodoVertice nodoDestino = buscarVertice(destino);
            if (nodoOrigen != null && nodoDestino != null) {
                exito = modificarEtiquetaAux(nodoOrigen, nodoDestino, etiqueta);
            }
        }
        return exito;
    }

    private boolean modificarEtiquetaAux(NodoVertice origen, NodoVertice destino, double etiqueta){
        boolean exito = false;
        if (origen != null) {
            NodoEnlace aux = origen.getPrimerEnlace();
            boolean encontrado = false;
            //se busca el arco en el nodo origen
            while (aux != null && !encontrado) {
                //si se encuentra se modifica la etiqueta
                if (aux.getVertice().equals(destino)) {
                    aux.setEtiqueta(etiqueta);
                    encontrado = true;
                }
                aux = aux.getSigEnlace();
            }
            //si se encontro el arco se busca el arco en el nodo destino
            if (encontrado) {
                //se busca el arco en el nodo destino
                aux = destino.getPrimerEnlace();
                while (aux != null && !exito) {
                    //si se encuentra se modifica la etiqueta
                    if (aux.getVertice().equals(origen)) {
                        aux.setEtiqueta(etiqueta);
                        exito = true;
                    }
                    aux = aux.getSigEnlace();
                }
            }
        }
        return exito;
    }

    public void vaciar(){
        inicio = null;
    }

    public boolean esVacio(){
        return inicio == null;
    }
}

package estructuras.conjuntistas;


import estructuras.lineales.Lista;

@SuppressWarnings({"rawtypes", "unchecked"})
public class  Avl {
    private NodoAvl raiz;

    public Avl(){
        raiz = null;
    }

    public boolean insertar(Comparable elem){
        boolean exito = false;
        if (elem != null & raiz != null) {
            exito = insertarAux(elem, this.raiz);
            raiz = reBalanceo(raiz);
        }else if(raiz == null){
            raiz = new NodoAvl(elem);
            exito = true;
        }
        return exito;
    }

    private boolean insertarAux(Comparable elem, NodoAvl nodo){
        boolean exito = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem())>0) {
                if (nodo.getDerecho() != null) {
                    insertarAux(elem, nodo.getDerecho());
                    nodo.setDerecho(reBalanceo(nodo.getDerecho()));
                }else{
                    nodo.setDerecho(new NodoAvl(elem));
                }
                exito = true;
            }else if (elem.compareTo(nodo.getElem())<=0) {
                if (nodo.getIzquierdo() != null) {
                    insertarAux(elem, nodo.getIzquierdo());
                    nodo.setIzquierdo(reBalanceo(nodo.getIzquierdo()));
                }else{
                    nodo.setIzquierdo(new NodoAvl(elem));
                }
                exito = true;
            }
            nodo.recalcularAltura();
        }
        return exito;
    }

    public boolean eliminar(Comparable elem){
        boolean[] eliminado = {false};
        if (raiz != null) {
            this.raiz = eliminarAux(raiz, elem, eliminado);
            this.raiz = reBalanceo(raiz);
        }
        return eliminado[0];
    }

    private NodoAvl eliminarAux(NodoAvl nodo, Comparable elem, boolean[] eliminado){
        if (nodo != null) {
            int comparacion = elem.compareTo(nodo.getElem());
            if (comparacion < 0) {
                nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elem, eliminado));
                nodo.recalcularAltura();
                nodo.setIzquierdo(reBalanceo(nodo.getIzquierdo()));
            }else if(comparacion > 0){
                nodo.setDerecho(eliminarAux(nodo.getDerecho(), elem, eliminado));
                nodo.recalcularAltura();
                nodo.setDerecho(reBalanceo(nodo.getDerecho()));
            }else{
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    nodo = null;
                    eliminado[0] = true;
                }else if(nodo.getIzquierdo() != null && nodo.getDerecho() == null){
                    nodo = nodo.getIzquierdo();
                    eliminado[0] = true;
                }else if(nodo.getIzquierdo() == null && nodo.getDerecho() != null){
                    nodo = nodo.getDerecho();
                    eliminado[0] = true;
                }else{
                    NodoAvl reemplazo = buscarReemplazo(nodo.getIzquierdo());
                    nodo.setElem(reemplazo.getElem());
                    nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), reemplazo.getElem(), eliminado));
                    eliminado[0] = true;
                }
            }
        }
        return nodo;
    }

    private NodoAvl buscarReemplazo(NodoAvl nodo){
        NodoAvl reemplazo = nodo;
        if (nodo.getDerecho() != null) {
            reemplazo = buscarReemplazo(nodo.getDerecho());
        }
        return reemplazo;
    }

    private NodoAvl reBalanceo(NodoAvl nodoHijo){
        int balanceo = balanceo(nodoHijo);
        if (balanceo > 1) {
            int balanceoHijo = balanceo(nodoHijo.getIzquierdo());
            if (balanceoHijo >= 0) {
                nodoHijo = rotarDerecha(nodoHijo);
            }else{
                nodoHijo.setIzquierdo(rotarIzquierda(nodoHijo.getIzquierdo()));
                nodoHijo = rotarDerecha(nodoHijo);
            }
        }else if(balanceo < -1){
            int balanceoHijo = balanceo(nodoHijo.getDerecho());
            if (balanceoHijo <= 0) {
                nodoHijo = rotarIzquierda(nodoHijo);
            }else{
                nodoHijo.setDerecho(rotarDerecha(nodoHijo.getDerecho()));
                nodoHijo = rotarIzquierda(nodoHijo);
            }
        }
        return nodoHijo;
    }

    private int balanceo(NodoAvl nodo){
        int balanceo = 0;
        if (nodo != null) {
            balanceo = (altura(nodo.getIzquierdo())+1) - (altura(nodo.getDerecho())+1);
        }
        return balanceo;
    }

    private int altura(NodoAvl nodo){
        int altura = -1;
        if (nodo != null) {
           //nodo.recalcularAltura(); 
           altura = nodo.getAltura();
        }
        
        return altura;
    }

    private NodoAvl rotarIzquierda(NodoAvl nodo){
        NodoAvl h = nodo.getDerecho();
        NodoAvl hh = h.getIzquierdo();
        h.setIzquierdo(nodo);
        nodo.setDerecho(hh);
        return h;
    }

    private NodoAvl rotarDerecha(NodoAvl nodo){
        NodoAvl h = nodo.getIzquierdo();
        NodoAvl hh = h.getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(hh);
        return h;
    }

    public boolean pertenece(Comparable elem){
        return perteneceAux(elem, raiz);
    }

    private boolean perteneceAux(Comparable elem, NodoAvl nodo){
        boolean pertenece = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                pertenece = true;
            }else if(elem.compareTo(nodo.getElem()) > 0){
                pertenece = perteneceAux(elem, nodo.getDerecho());
            }else{
                pertenece = perteneceAux(elem, nodo.getIzquierdo());
            }
        }
        return pertenece;
    }

    public boolean esVacio(){
        return raiz == null;
    }

    public Lista listar(){
        Lista lista = new Lista();
        listarAux(raiz, lista);
        return lista;
    }

    private void listarAux(NodoAvl nodo, Lista lista){
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public void vaciar(){
        raiz = null;
    }

    public Comparable minimoElemento(){
        NodoAvl nodo = raiz;
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getElem();
    }

    public Comparable maximoElemento(){
        NodoAvl nodo = raiz;
        while (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
        }
        return nodo.getElem();
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax){
        Lista lista = new Lista();
        listarRangoAux(raiz, lista, elemMin, elemMax);
        return lista;
    }

    private void listarRangoAux(NodoAvl nodo, Lista lista, Comparable elemMin, Comparable elemMax){
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemMin) > 0) {
                listarRangoAux(nodo.getIzquierdo(), lista, elemMin, elemMax);
            }
            if (nodo.getElem().compareTo(elemMin) >= 0 && nodo.getElem().compareTo(elemMax) <= 0) {
                lista.insertar(nodo.getElem(), lista.longitud()+1);
            }
            if (nodo.getElem().compareTo(elemMax) < 0) {
                listarRangoAux(nodo.getDerecho(), lista, elemMin, elemMax);
            }
        }
    }
}

package estructuras.lineales;

public class Lista {
    private Nodo cabecera;
    
    public Lista(){
        this.cabecera = null;
    }

    public boolean insertar(Object elem, int pos){
        boolean exito = true;

        if (pos < 1 || pos > this.longitud()+1) {
            exito = false;
        }else{
            if (pos == 1) {
                if (this.cabecera == null) {
                    this.cabecera = new Nodo(elem, null);
                }else{
                    this.cabecera = new Nodo(elem, cabecera);
                }
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos-1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int pos){
        boolean exito = true;
        if (this.cabecera == null) {
            exito = false;
        }else{
            if (pos == 1) {
                this.cabecera = cabecera.getEnlace();
            }else if(pos <= longitud()){
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos-1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo temp = aux.getEnlace();
                temp = temp.getEnlace();
                aux.setEnlace(temp);
            }
        }
        return exito;
    }

    public Object recuperar(int pos){
        Object elemento = null;
        if (this.cabecera != null && pos > 0) {
            if (pos == 1) {
                elemento = cabecera.getDato();
            }else if(pos <= this.longitud()){
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                elemento = aux.getDato();
            }
        }
        return elemento;
    }

    public int localizar(Object elemento){
        Nodo aux = this.cabecera;
        int pos = -1;
        int i = 1;
        int limit = longitud();
        boolean encontrado = false;
        while (i < limit && !encontrado) {
            if (aux.getDato().toString().equals(elemento.toString())) {
                pos = i;
                encontrado = true;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }

    public int longitud(){
        int longitud = 0;
        Nodo aux = this.cabecera;
        while (aux != null) {
            aux = aux.getEnlace();
            longitud++;
        }
        return longitud;
    }

    public boolean esVacia(){
        boolean vacia = false;
        if (longitud() == 0) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar(){
        this.cabecera = null;
    }

    /**
     * Retorna un clon de la lista
     * @return un objeto de tipo Lista
     */
    public Lista clone(){
        Lista clon = new Lista();
        Nodo aux = this.cabecera;
        int pos = 1;
        int limit = longitud();
        while (pos <= limit) {
            clon.insertar(aux.getDato(), pos);
            aux = aux.getEnlace();
            pos++;
        }
        return clon;
    }


    public String toString(){
        Nodo aux = this.cabecera;
        String cadena = "[";
        while (aux != null) {
            cadena += aux.getDato().toString()+",";
            aux = aux.getEnlace();
        }
        cadena += "]";
        return cadena;
    }

    public Lista obtenerMultiplos(int num){
        Lista nuevaLista = new Lista();
        if (cabecera != null) {
            int i = 1;
            Nodo aux = cabecera;
            Nodo auxNuevaList = nuevaLista.cabecera;
            while (aux != null) {
                aux = aux.getEnlace();
                if (i%num == 0) {
                    if (auxNuevaList == null) {
                        auxNuevaList = new Nodo(aux.getDato(), null);
                    }
                    auxNuevaList = auxNuevaList.getEnlace();
                }
                i++;
            }
        }
        return nuevaLista;
    }

    public void eliminarAparicion(Object x){
        if (cabecera != null) {
            String xString = x.toString();
            Nodo aux = cabecera;
            if (aux.getDato().toString().equals(xString)) {
                aux = aux.getEnlace();
            }
            while (aux != null) {
                if (aux.getEnlace().getDato().toString().equals(xString)) {
                    Nodo aux2 = aux.getEnlace();
                    aux.setEnlace(aux2.getEnlace());
                }
                aux = aux.getEnlace();
            }
        }
    }

    
    //promocion

    //ejercicio 2 parcial 1

    public boolean moverAAnteultimaPosicion(int pos){
        boolean exito = false;
        if (cabecera != null && cabecera.getEnlace() != null) {
            exito = moverAAnteultimaPosicionAux(pos);
        }
        return exito;
    }

    private boolean moverAAnteultimaPosicionAux(int pos){
        boolean exito = false;
        if (cabecera.getEnlace().getEnlace() != null) {
            exito = moverAAnteultimaPosicionGeneral(pos);
        }else if(pos == 1){
            exito = true;
        }else{
            Object aux = cabecera.getDato();
            cabecera.setDato(cabecera.getEnlace().getDato());
            cabecera.getEnlace().setDato(aux);
            exito = true;
        }
        return exito;
    }

    private boolean moverAAnteultimaPosicionGeneral(int pos){
        boolean exito = true;
        Nodo nodo = cabecera;
        int posActual = 0;
        Nodo nodoBuscado = null;
        Nodo anteUltimoNodo = null;
        while (nodo != null && (nodoBuscado != null && anteUltimoNodo != null)) {
            if (nodo.getEnlace().getEnlace() == null) {
                if (pos == posActual) {
                    exito = true;
                }else{
                    anteUltimoNodo = nodo;
                }
            }
            if (posActual == pos) {
                nodoBuscado = nodo;
            }
            nodo = nodo.getEnlace();
            posActual++;
        }
        if (nodoBuscado != null && anteUltimoNodo != null) {
            Object aux = nodoBuscado.getDato();
            nodoBuscado.setDato(anteUltimoNodo.getDato());
            anteUltimoNodo.setDato(aux);
            exito = true;
        }
        return exito;
    }

}

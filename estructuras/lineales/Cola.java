package estructuras.lineales;
public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola(){
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem){
        Nodo nuevo = new Nodo(elem, null);
        if (this.esVacia()) {
            this.frente = nuevo;
        } else {
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;
        return true;
    }

    public boolean sacar(){
        boolean sacado;
        if (this.esVacia()) {
            sacado = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            sacado = true;
        }
        return sacado;
    }

    public Object obtenerFrente(){
        Object frente;
        if (this.esVacia()) {
            frente = null;
        } else {
            frente = this.frente.getDato();
        }
        return frente;
    }

    public boolean esVacia(){
        return this.frente == null;
    }

    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }

    public Cola clone(){
        Cola clon = new Cola();
        if (!this.esVacia()) {
            clon.frente = new Nodo(this.frente.getDato(), null);
            Nodo aux = this.frente.getEnlace();
            Nodo auxClon = clon.frente;
            while (aux != null) {
                auxClon.setEnlace(new Nodo(aux.getDato(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
            clon.fin = auxClon;
        }
        return clon;
    }

    public String toString(){
        String cadena = "[";
        if (!this.esVacia()) {
            Nodo aux = this.frente;
            while (aux != null) {
                cadena += aux.getDato().toString() + " ";
                aux = aux.getEnlace();
            }
        }
        cadena += "]";
        return cadena;
    }
}

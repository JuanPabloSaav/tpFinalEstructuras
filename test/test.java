package test;

import estructuras.grafo.Grafo;
import estructuras.lineales.Lista;

public class test {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarArco("A", "B", 0);
        grafo.insertarArco("A", "C", 0);
        grafo.insertarArco("B", "D", 0);
        grafo.insertarArco("C", "D", 0);
        Lista lista = grafo.caminoMasCorto("A", "C");
        for (int i = 1; i <= lista.longitud(); i++) {
            System.out.println(lista.recuperar(i));
        }
    }
}

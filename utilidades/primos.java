package utilidades;
import java.util.Arrays;

public class primos {
    
    /**
     * Metodo que devuelve un arreglo de booleanos con los numeros primos hasta n
     * con una complejidad de O(n log log n)
     * @param n limite de los numeros primos
     * @return arreglo de booleanos con los numeros primos hasta n
     */
    public static boolean[] cribaDeEratostenes(int n){
        boolean[] esPrimo = new boolean[n + 1];
        boolean exito = false;
        Arrays.fill(esPrimo, true);
        int inicio = 2;
        while (!exito) {
            esPrimo[inicio] = false;
            for (int i = 2; i*inicio <= n; i++) {
                if (esPrimo[i*inicio]) {
                    esPrimo[i*inicio] = false;
                }
            }
            if (Math.pow(inicio, 2) < n) {
                inicio++;
            }else{
                exito = true;
            }
        }
        return esPrimo;
    }


    /**
     * Metodo que filtra la criba de eratostenes y devuelve el primo mas cercano al numero dado
     * @param numero numero al que se le buscara el primo mas cercano
     * @return primo mas cercano al numero dado
     */
    public static int getPrimoCercano(int numero){
        boolean[] numeroPrimos = cribaDeEratostenes(numero);
        while (!numeroPrimos[numero] && numero > 2) {
            numero--;
        }
        return numero;
    }

}

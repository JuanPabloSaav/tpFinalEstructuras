package utilidades;
import java.util.Arrays;

public class primos {
    
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

    public static int getPrimoCercano(int numero){
        boolean[] numeroPrimos = cribaDeEratostenes(numero);
        while (!numeroPrimos[numero] && numero > 2) {
            numero--;
        }
        return numero;
    }

}

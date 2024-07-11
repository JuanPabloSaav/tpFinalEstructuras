package objetos;

public class rangoPartido {
    private int golesEq1;
    private int golesEq2;
    private String ronda;

    /**
     * Constructor de la clase rangoPartido que toma los goles de los equipos y la ronda del partido
     * @param golesEq1 Los goles del equipo 1
     * @param golesEq2 Los goles del equipo 2
     * @param ronda La ronda del partido
     */
    public rangoPartido(int golesEq1, int golesEq2, String ronda){
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
        this.ronda = ronda;
    }

    /**
     * Metodo que retorna los goles del equipo 1
     * @return Los goles del equipo 1 de tipo int
     */
    public int getGolesEq1() {
        return golesEq1;
    }

    /**
     * Metodo que retorna los goles del equipo 2
     * @return Los goles del equipo 2 de tipo int
     */
    public int getGolesEq2() {
        return golesEq2;
    }

    /**
     * Metodo que retorna la ronda del partido
     * @return La ronda del partido de tipo String
     */
    public String getRonda() {
        return ronda;
    }

    /**
     * Metodo que retorna una representacion en cadena de la instancia
     * @return La representacion en cadena de la instancia de tipo String
     */
    public String toString(){
        return "Goles Eq1: "+ golesEq1 + "\nGoles Eq2: "+ golesEq2 + "\nRonda: "+ ronda + "\n";
    }
}

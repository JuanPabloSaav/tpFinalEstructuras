package objetos;

public class PartidoDatos {
    private int golesEq1;
    private int golesEq2;
    private String nombreEstadio;
    private String ronda;
    private Ciudad ciudad;

    /**
     * Constructor de la clase rangoPartido que toma los goles de los equipos y la ronda del partido
     * @param golesEq1 Los goles del equipo 1
     * @param golesEq2 Los goles del equipo 2
     * @param ronda La ronda del partido
     */
    public PartidoDatos(String ronda, Ciudad ciudad, String nombreEstadio, int golesEq1, int golesEq2){
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
        this.nombreEstadio = nombreEstadio;
        this.ronda = ronda;
        this.ciudad = ciudad;
    }

    /**
     * Metodo que retorna la ciudad del partido
     * @return  La ciudad del partido de tipo Ciudad
     */
    public Ciudad getCiudad(){
        return ciudad;
    }

    /**
     * Metodo que retorna el estadio del partido
     * @return El estadio del partido de tipo String
     */
    public String getEstadio(){
        return nombreEstadio;
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
     * Metodo que retorna el nombre del estadio
     * @return El nombre del estadio de tipo String
     */
    public String getNombreEstadio(){
        return nombreEstadio;
    }

    /**
     * Metodo que asigna los goles del equipo 1
     * @param golesEq1 Los goles del equipo 1
     */
    public void setNombreEstadio(String nombreEstadio){
        this.nombreEstadio = nombreEstadio;
    }

    /**
     * Metodo que asigna los goles del equipo 1
     * @param golesEq1 Los goles del equipo 1
     */
    public void setGolesEq1(int golesEq1) {
        this.golesEq1 = golesEq1;
    }

    /**
     * Metodo que asigna los goles del equipo 2
     * @param golesEq2 Los goles del equipo 2
     */
    public void setGolesEq2(int golesEq2) {
        this.golesEq2 = golesEq2;
    }

    /**
     * Metodo que asigna la ronda del partido
     * @param ronda La ronda del partido
     */
    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    /**
     * Metodo que asigna la ciudad del partido
     * @param ciudad La ciudad del partido
     */
    public void setCiudad(Ciudad ciudad){
        this.ciudad = ciudad;
    }



    /**
     * Metodo que retorna una representacion en cadena de la instancia
     * @return La representacion en cadena de la instancia de tipo String
     */
    public String toString(){
        return "Goles Eq1: "+ golesEq1 + "\nGoles Eq2: "+ golesEq2 + "\nRonda: "+ ronda + "\n";
    }
}

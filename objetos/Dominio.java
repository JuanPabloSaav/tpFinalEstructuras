package objetos;

public class Dominio {
    private Equipo eq1;
    private Equipo eq2;
    // se almacena la clave para mejorar la eficiencia (total no la voy a cambiar)
    private int clave;

    /**
     * Constructor de la clase dominioPartido que toma dos equipos, 
     * genera una clave "unica" y la asigna a la instancia
     * @param eq1 El primer equipo
     * @param eq2 El segundo equipo
     */
    public Dominio(Equipo eq1, Equipo eq2){
        this.eq1 = eq1;
        this.eq2 = eq2;
        // se genera la clave y se almacena en la instancia para mejorar la eficiencia
        clave = genClave();
    }

    /**
     * Metodo que genera una clave unica para la instancia
     * @return La clave generada de tipo int
     */
    private int genClave(){
        return eq1.hashCode() + eq2.hashCode();
    }

    /**
     * Metodo que retorna la clave de la instancia
     * @return La clave de la instancia de tipo int
     */
    public int getClave(){
        return clave;
    }

    /**
     * Metodo que retorna el primer equipo de la instancia
     * @return El primer equipo de la instancia de tipo Equipo
     */
    public Equipo getEq1() {
        return eq1;
    }

    /**
     * Metodo que retorna el segundo equipo de la instancia
     * @return El segundo equipo de la instancia de tipo Equipo
     */
    public Equipo getEq2() {
        return eq2;
    }

    public boolean equals(Dominio dominio){
        return this.clave == dominio.getClave();
    }

}

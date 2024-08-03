package objetos;

public class PartidoClave {
    private Equipo eq1;
    private Equipo eq2;
    // se almacena la clave para mejorar la eficiencia (total no la voy a cambiar)

    /**
     * Constructor de la clase dominioPartido que toma dos equipos, 
     * genera una clave "unica" y la asigna a la instancia
     * @param eq1 El primer equipo
     * @param eq2 El segundo equipo
     */
    public PartidoClave(Equipo eq1, Equipo eq2){
        this.eq1 = eq1;
        this.eq2 = eq2;
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

    public boolean equals(Object dominio){
        boolean igual = false;
        if (dominio != null && getClass() == dominio.getClass()) {
            PartidoClave pk = (PartidoClave) dominio;
            igual = this.eq1.equals(pk.eq1) && this.eq2.equals(pk.eq2);
        }
        return igual;
    }

    public int hashCode(){
        return eq1.hashCode() + eq2.hashCode();
    }

}

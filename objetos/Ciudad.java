package objetos;

public class Ciudad {
    private String nombre;
    private String pais;
    private boolean alojamientoDisponible;
    private boolean esSede;

    public Ciudad(String nombre, String pais, boolean alojamientoDisponible, boolean esSede){
        this.nombre = nombre;
        this.pais = pais;
        this.alojamientoDisponible = alojamientoDisponible;
        this.esSede = esSede;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getPais(){
        return this.pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public boolean getAlojamientoDisponible(){
        return this.alojamientoDisponible;
    }

    public void setAlojamientoDisponible(boolean alojamientoDisponible){
        this.alojamientoDisponible = alojamientoDisponible;
    }

    public boolean getEsSede(){
        return this.esSede;
    }

    public void setEsSede(boolean esSede){
        this.esSede = esSede;
    }

    public String toString(){
        return "Nombre: " + this.nombre + "\nPais: " + this.pais + "\nAlojamiento disponible: " + this.alojamientoDisponible + "\nEs sede: " + this.esSede + "\n";
    }

    @Override
    public int hashCode(){
        int suma = 0;
        for (int i = 0; i < this.nombre.length(); i++){
            suma += (int) this.nombre.charAt(i);
        }
        for (int i = 0; i < this.pais.length(); i++){
            suma += (int) this.pais.charAt(i);
        }
        return suma;
    }
}

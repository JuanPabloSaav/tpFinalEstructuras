package objetos;

public class Ciudad {
    private String nombre;
    private boolean alojamientoDisponible;
    private boolean esSede;

    public Ciudad(String nombre, boolean alojamientoDisponible, boolean esSede){
        this.nombre = nombre;
        this.alojamientoDisponible = alojamientoDisponible;
        this.esSede = esSede;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
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
        return "Nombre: " + this.nombre + "\nAlojamiento disponible: " + this.alojamientoDisponible + "\nEs sede: " + this.esSede + "\n";
    }

    @Override
    public int hashCode(){
        int suma = 0;
        for (int i = 0; i < this.nombre.length(); i++){
            suma += (int) this.nombre.charAt(i);
        }
        return suma;
    }
}

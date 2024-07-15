package objetos;

public class Ruta {
    private int tiempoVuelo;
    private Ciudad ciudad1;
    private Ciudad ciudad2;

    public Ruta(int tiempoVuelo, Ciudad ciudad1, Ciudad ciudad2){
        this.tiempoVuelo = tiempoVuelo;
        this.ciudad1 = ciudad1;
        this.ciudad2 = ciudad2;
    }

    public int getTiempoVuelo(){
        return this.tiempoVuelo;
    }

    public void setTiempoVuelo(int tiempoVuelo){
        this.tiempoVuelo = tiempoVuelo;
    }

    public Ciudad getCiudad1(){
        return this.ciudad1;
    }

    public void setCiudad1(Ciudad ciudad1){
        this.ciudad1 = ciudad1;
    }

    public Ciudad getCiudad2(){
        return this.ciudad2;
    }

    public void setCiudad2(Ciudad ciudad2){
        this.ciudad2 = ciudad2;
    }   

    public String toString(){
        return "Tiempo de vuelo: " + this.tiempoVuelo + " Ciudad 1: " + this.ciudad1 + " Ciudad 2: " + this.ciudad2;
    }
    

    @Override
    public int hashCode(){
        int suma = 0;
        suma += this.tiempoVuelo;
        suma += this.ciudad1.hashCode();
        suma += this.ciudad2.hashCode();
        return suma;
    }
}

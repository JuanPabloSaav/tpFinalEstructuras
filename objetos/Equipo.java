package objetos;


public class Equipo implements Comparable<Equipo>{
    private String apellidoTecnico;
    private String grupo;
    private String pais;
    private int puntosGanados;
    private int golesAFavor;
    private int golesEnContra;


    public Equipo(String apellidoTecnico, String grupo, String pais){
        this.apellidoTecnico = apellidoTecnico;
        this.grupo = grupo;
        this.pais = pais;
    }

    public Equipo(String pais){
        this.pais = pais;
        this.apellidoTecnico = "";
        this.grupo = "";
        this.puntosGanados = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }

    public String getApellidoTecnico(){
        return this.apellidoTecnico;
    }

    public void setApellidoTecnico(String apellidoTecnico){
        this.apellidoTecnico = apellidoTecnico;
    }

    public String getGrupo(){
        return this.grupo;
    }

    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    public String getPais(){
        return this.pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public int getPuntosGanados(){
        return this.puntosGanados;
    }

    public void setPuntosGanados(int puntosGanados){
        this.puntosGanados = puntosGanados;
    }

    public int getGolesAFavor(){
        return this.golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor){
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra(){
        return this.golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra){
        this.golesEnContra = golesEnContra;
    }

    public String toString(){
        return  "Pais:" + this.pais + "\nGrupo: " + this.grupo +"\nApellido del técnico: " + this.apellidoTecnico + "\nPuntos ganados: " + this.puntosGanados + "\nGoles a favor: " + this.golesAFavor + "\nGoles en contra: " + this.golesEnContra+"\n";
    }

    public boolean equals(Object obj){
        if (obj instanceof Equipo){
            Equipo equipo = (Equipo) obj;
            return this.pais.equals(equipo.getPais());
        }
        return false;
    }

    @Override
    public int compareTo(Equipo equipo){
        return this.pais.compareTo(equipo.getPais());
    }

    @Override
    public int hashCode(){
        int suma = 0;
        for (int i = 0; i < this.pais.length(); i++){
            suma += (int) this.pais.charAt(i);
        }
        return suma;
    }
}
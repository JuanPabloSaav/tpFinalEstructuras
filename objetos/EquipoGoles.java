package objetos;

public class EquipoGoles implements Comparable<EquipoGoles> {
    private String equipo;
    private int goles;

    public EquipoGoles(String equipo, int goles) {
        this.equipo = equipo;
        this.goles = goles;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getGoles() {
        return goles;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    @Override
    public String toString() {
        return "Equipo: " + equipo + " - Goles a favor: " + goles;
    }

    @Override
    public int compareTo(EquipoGoles eg) {
        int res = 0;
        if (this.goles < eg.goles) {
            res = -1;
        } else if (this.goles > eg.goles) {
            res = 1;
        } 
        return res;
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class Cita {private String nombre;
    private String cedula;
    private String fecha;

    public Cita(String nombre, String cedula, String fecha) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
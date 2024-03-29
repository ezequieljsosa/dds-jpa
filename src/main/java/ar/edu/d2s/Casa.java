package ar.edu.d2s;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;



@Entity	
public class Casa {

    public final static String PROPIETARIO = "propietario";
    public final static String HABITACIONES = "habitaciones";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String propietario;

    @Transient
    private List<Habitacion> habitaciones;

    protected Casa() {
        super();
    }

    public Casa(String propietario) {
        super();
        this.habitaciones = new ArrayList<Habitacion>();
        this.propietario = propietario;
    }

    public void addHabitacion(Habitacion h) {
        this.habitaciones.add(h);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int superficie() {
        return this.habitaciones.stream().map(x -> x.getM2()).reduce(0, Integer::sum);
    }

    public String toString() {
        return "Casa de " + this.propietario;
    }
}
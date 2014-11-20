package Entidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Carrera {
    private Integer carreraId;
    private String nombre;
    private String codigo;
    private List<Nivel> niveles = new ArrayList();
    public Carrera() {
    }
    public Carrera(Integer carreraId, String nombre, String codigo) {
        this.carreraId = carreraId;
        this.nombre = nombre;
        this.codigo = codigo.toUpperCase();
    }
    public Integer getCarreraId() {
        return carreraId;
    }
    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }
    public List<Nivel> getNiveles() {
        return niveles;
    }
    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}

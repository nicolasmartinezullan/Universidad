package Entidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Materia {
    private Integer materiaId;
    private String nombre;
    private Integer codigo;
    private List<Materia> aprobadasParaRendir = new ArrayList();
    private List<Materia> aprobadasParaCursar = new ArrayList();
    private List<Materia> cursadasParaCursar = new ArrayList();
    public Materia() {
    }
    public Materia(Integer materiaId, String nombre, Integer codigo) {
        this.materiaId = materiaId;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public Integer getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public void agregarAprobadaParaRendir(Materia materia){
        if (materia!= null) 
            aprobadasParaRendir.add(materia);
    }
    public void agregarCursardaParaCursar(Materia materia){
        if (materia!= null) 
            cursadasParaCursar.add(materia);
    }
    public void agregarAprobadaParaCursar(Materia materia){
        if (materia!= null) 
            aprobadasParaCursar.add(materia);
    }
    @Override
    public String toString() {
        return "Materia{" + "materiaId=" + materiaId + ", nombre=" + nombre + ", codigo=" + codigo + '}';
    }
}

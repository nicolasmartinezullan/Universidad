package Modelo.Entidades;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Estudiante {
    private Integer estudianteId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private Integer dni;
    private Integer legajo;
    private Date fechaEnrolamiento;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private Integer carreraId;
    private Integer cursoId;
    private byte[] foto;
    private final List<Materia> materiasCursadas = new ArrayList();
    private final List<Materia> materiasAprobadas = new ArrayList();
    public Estudiante() {
    }
    public Estudiante(Integer estudianteId, String nombre, 
         String apellido, String direccion, String telefono, 
         Integer dni, Integer legajo, Date fechaEnrolamiento, 
         Date fechaNacimiento, Integer carreraId, Integer cursoId, 
         byte[] foto) {
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
        this.legajo = legajo;
        this.fechaEnrolamiento = fechaEnrolamiento;
        this.fechaNacimiento = fechaNacimiento;
        this.carreraId = carreraId;
        this.cursoId = cursoId;
        this.foto = foto;
    }
    public Integer getEstudianteId() {        return estudianteId;    }
    public void setEstudianteId(Integer estudianteId){        this.estudianteId = estudianteId;    }
    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    public String getApellido() {        return apellido;    }
    public void setApellido(String apellido) {        this.apellido = apellido;    }
    public String getDireccion() {        return direccion;    }
    public void setDireccion(String direccion) {        this.direccion = direccion;    }
    public String getTelefono() {        return telefono;    }
    public void setTelefono(String telefono) {        this.telefono = telefono;    }
    public Integer getDni() {        return dni;    }
    public void setDni(Integer dni) {        this.dni = dni;    }
    public Integer getLegajo() {        return legajo;    }
    public void setLegajo(Integer legajo) {        this.legajo = legajo;    }
    public Date getFechaEnrolamiento() {        return fechaEnrolamiento;    }
    public void setFechaEnrolamiento(Date fechaEnrolamiento) {        this.fechaEnrolamiento = fechaEnrolamiento;    }    
    public Integer getCarreraId() {        return carreraId;    }
    public void setCarreraId(Integer carreraId) {        this.carreraId = carreraId;    }
    public Integer getCursoId() {  return cursoId;   }
    public byte[] getFoto(){return foto;}
    public void setFoto(byte[] foto){this.foto = foto;}
    public Date getFechaNacimiento(){return fechaNacimiento;}
    public void setFechaNacimiento(Date fechaNacimiento){this.fechaNacimiento = fechaNacimiento;}
    public Date getFechaAlta(){return fechaAlta;}
    public void setFechaAlta(Date fechaAlta){this.fechaAlta = fechaAlta;}
    public void setCursoId(Integer cursoId) {        this.cursoId = cursoId;    }
    public List<Materia> getMateriasCursadas(){return materiasCursadas;}
    public List<Materia> getMateriasAprobadas(){return materiasAprobadas;}
    public void agregarMateriaAprobada(Materia materia) {
        if (materia != null)
            materiasAprobadas.add(materia);
    }
    public void agregarMateriaCursada(Materia materia) {
        if (materia != null)
            materiasCursadas.add(materia);
    }
    @Override
    public String toString() {
        return "Estudiante{" + "estudianteId=" + estudianteId + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", telefono=" + telefono + ", dni=" + dni + ", legajo=" + legajo + ", fechaEnrolamiento=" + fechaEnrolamiento + ", fechaNacimiento=" + fechaNacimiento + ", fechaAlta=" + fechaAlta + ", carreraId=" + carreraId + ", cursoId=" + cursoId + '}';
    }
}

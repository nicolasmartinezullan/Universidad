package Formularios;
import Modelo.Entidades.Estudiante;
import Modelo.Entidades.Carrera;
import Negocio.*;
import java.util.List;
import static com.wagnerandade.coollection.Coollection.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Repositorio.refrescarCarreras();
        Repositorio.refrescarCursos();
        Repositorio.refrescarMaterias();
        Repositorio.refrescarNiveles();
        // Cargar las carreras
        NegocioCarrera negocioCarrera = new NegocioCarrera();
        List<Carrera> carreras = negocioCarrera.devolverTodos();
        // Cargar estudiantes
        NegocioEstudiante negocioEstudiante = new NegocioEstudiante();
        List<Estudiante> estudiantes = negocioEstudiante.devolverTodos();
        
        
    }
}

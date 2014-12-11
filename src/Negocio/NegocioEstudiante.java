package Negocio;
import AccesoDatos.ServicioDatosEstudiante;
import Modelo.Entidades.Estudiante;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioEstudiante {
    
    ServicioDatosEstudiante servicioDatosEstudiante = new ServicioDatosEstudiante();
    
    public List<Estudiante> devolverTodos() {
        return servicioDatosEstudiante.devolverTodos();
    }
}

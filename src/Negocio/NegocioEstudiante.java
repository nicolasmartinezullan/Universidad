package Negocio;
import AccesoDatos.ServicioDatosEstudiante;
import Entidades.Estudiante;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioEstudiante {
    public List<Estudiante> devolverTodos() {
        ServicioDatosEstudiante sde = new ServicioDatosEstudiante();
        return sde.devolverTodos();
    }
}

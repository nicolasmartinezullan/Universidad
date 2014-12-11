package Negocio;
import AccesoDatos.ServicioDatosCarrera;
import Modelo.Entidades.Carrera;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioCarrera {
    public List<Carrera> devolverTodos() {
        ServicioDatosCarrera sdc = new ServicioDatosCarrera();
        return sdc.devolverTodos();
    }
}

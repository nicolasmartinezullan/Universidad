package Negocio;
import AccesoDatos.ServicioDatosMateria;
import Entidades.Materia;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioMateria {
    public List<Materia> devolverTodos(){
        ServicioDatosMateria sdm = new ServicioDatosMateria();
        return sdm.devolverTodos();
    }
}

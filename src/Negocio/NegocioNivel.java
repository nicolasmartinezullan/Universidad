package Negocio;
import AccesoDatos.ServicioDatosNivel;
import Entidades.Nivel;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioNivel {
    public List<Nivel> devolverTodos(){
        ServicioDatosNivel sdn = new ServicioDatosNivel();
        return sdn.devolverTodos();
    }
}

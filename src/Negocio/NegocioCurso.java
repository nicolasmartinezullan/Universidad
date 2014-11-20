package Negocio;
import AccesoDatos.ServicioDatosCurso;
import Entidades.Curso;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioCurso {
    public List<Curso> devolverTodos(){
        ServicioDatosCurso sdc = new ServicioDatosCurso();
        return sdc.devolverTodos();
    }
    
    public boolean insertar(int nivelId, int carreraId, int numero){
        boolean esPosibleInsertar = existeEsteCurso(nivelId, carreraId, numero);
        if (esPosibleInsertar) {
            ServicioDatosCurso servicioDatosCurso = new ServicioDatosCurso();
            esPosibleInsertar = servicioDatosCurso.insertar(nivelId, carreraId, numero);
        }
        return esPosibleInsertar;
    }
    
    public boolean existeEsteCurso(int nivelId, int carreraId, int numero){
        ServicioDatosCurso servicioDatosCurso = new ServicioDatosCurso();
        return servicioDatosCurso.existeEsteCurso(nivelId, carreraId, numero);
    }
}

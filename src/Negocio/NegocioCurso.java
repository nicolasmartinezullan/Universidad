package Negocio;
import AccesoDatos.ServicioDatosCurso;
import Modelo.Entidades.Curso;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class NegocioCurso {
    private final ServicioDatosCurso servicioDatosCurso = new ServicioDatosCurso();
    
    public List<Curso> devolverTodos(){
        return servicioDatosCurso.devolverTodos();
    }
    
    public boolean insertar(int nivelId, int carreraId, int numero){
        boolean exito;
        if(servicioDatosCurso.estaEliminado(nivelId, carreraId, numero)){
            servicioDatosCurso.revivir(nivelId, carreraId, numero);
            exito = true;
        }
        else
            exito = servicioDatosCurso.insertar(nivelId, carreraId, numero);
        return exito;
    }
    
    public boolean existeEstaCombinacion(int nivelId, int carreraId, int numero){
        return servicioDatosCurso.existeEstaCombinacion(nivelId, carreraId, numero);
    }
    
    public void modificar(int cursoId, int nivelId, int carreraId, int numero){
        servicioDatosCurso.modificar(cursoId, nivelId, carreraId, numero);
    }
    
    public void eliminar(int cursoId){
        servicioDatosCurso.eliminar(cursoId);
    }
}

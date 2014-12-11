package Negocio;
import Modelo.Entidades.Nivel;
import Modelo.Entidades.Curso;
import Modelo.Entidades.Carrera;
import Modelo.Entidades.Materia;
import java.util.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Repositorio {
    private static List<Curso> cursos = new ArrayList();
    private static List<Carrera> carreras = new ArrayList();
    private static List<Nivel> niveles = new ArrayList();
    private static List<Materia> materias = new ArrayList();
    
    public static void refrescarCursos(){
        NegocioCurso negocioCurso = new NegocioCurso();
        cursos = negocioCurso.devolverTodos();
    }
    public static void refrescarCarreras(){
        NegocioCarrera negocioCarrera = new NegocioCarrera();
        carreras = negocioCarrera.devolverTodos();
    }
    public static void refrescarNiveles(){
        NegocioNivel negocioNivel = new NegocioNivel();
        niveles = negocioNivel.devolverTodos();
    }
    public static void refrescarMaterias(){
        NegocioMateria negocioMateria = new NegocioMateria();
        materias = negocioMateria.devolverTodos();
    }
    public static List<Curso> getCursos(){if (cursos.isEmpty())refrescarCursos(); return cursos;}
    public static List<Carrera> getCarreras(){if(carreras.isEmpty())refrescarCarreras(); return carreras;}
    public static List<Nivel> getNiveles(){if(niveles.isEmpty())refrescarNiveles(); return niveles;}
    public static List<Materia> getMaterias(){if(materias.isEmpty())refrescarMaterias();return materias;}
}

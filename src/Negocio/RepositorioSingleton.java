package Negocio;
import Modelo.Entidades.*;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public final class RepositorioSingleton {
    private List<Curso> cursos;
    private List<Carrera> carreras;
    private List<Nivel> niveles;
    private List<Materia> materias;
    private List<Estudiante> estudiantes;
    private static RepositorioSingleton repo;
    private RepositorioSingleton(){
        refrescarCarreras();
        refrescarCursos();
        refrescarMaterias();
        refrescarNiveles();
    }
    public static RepositorioSingleton getInstance(){
        if(repo == null)
            repo = new RepositorioSingleton();
        return repo;
    }
    public void refrescarCursos(){
        NegocioCurso negocioCurso = new NegocioCurso();
        cursos = negocioCurso.devolverTodos();
    }
    public void refrescarCarreras(){
        NegocioCarrera negocioCarrera = new NegocioCarrera();
        carreras = negocioCarrera.devolverTodos();
    }
    public void refrescarNiveles(){
        NegocioNivel negocioNivel = new NegocioNivel();
        niveles = negocioNivel.devolverTodos();
    }
    public void refrescarMaterias(){
        NegocioMateria negocioMateria = new NegocioMateria();
        materias = negocioMateria.devolverTodos();
    }
    public void refrescarEstudiantes(){
        NegocioEstudiante negocioEstudiante = new NegocioEstudiante();
        estudiantes = negocioEstudiante.devolverTodos();
    }
    public List<Curso> getCursos(){return cursos;}
    public List<Carrera> getCarreras(){return carreras;}
    public List<Nivel> getNiveles(){return niveles;}
    public List<Materia> getMaterias(){return materias;}
    public List<Estudiante> getEstudiantes(){return estudiantes;}
}

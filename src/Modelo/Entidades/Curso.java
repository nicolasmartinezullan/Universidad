package Modelo.Entidades;
import Negocio.Repositorio;
import static com.wagnerandade.coollection.Coollection.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Curso {
    private Integer cursoId;
    private Integer nivelId;
    private Integer carreraId;
    private Integer numero;
    public Curso() {
    }
    public Curso(Integer cursoId, Integer nivelId, Integer carreraId, Integer numero) {
        this.cursoId = cursoId;
        this.nivelId = nivelId;
        this.carreraId = carreraId;
        this.numero = numero;
    }
    public Integer getCursoId() {
        return cursoId;
    }
    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
    public Integer getCarreraId() {
        return carreraId;
    }
    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
    }
    public Integer getNivelId() {
        return nivelId;
    }
    public void setNivelId(Integer nivelId) {
        this.nivelId = nivelId;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getCodigoAlfaNumerico(){
        return from(Repositorio.getNiveles()).where("getNivelId", eq(nivelId)).first().getNivel()
                + from(Repositorio.getCarreras()).where("getCarreraId", eq(carreraId)).first().getCodigo()
                + numero;
    }
    @Override
    public String toString() {return getCodigoAlfaNumerico();}
}

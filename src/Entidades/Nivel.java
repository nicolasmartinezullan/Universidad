package Entidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class Nivel {
    private Integer nivelId;
    private Integer nivel;
    private List<Materia> materias = new ArrayList();
    public Nivel() {
    }
    public Nivel(Integer nivelId, Integer nivel) {
        this.nivelId = nivelId;
        this.nivel = nivel;
    }
    public Integer getNivelId() {
        return nivelId;
    }
    public void setNivelId(Integer nivelId) {
        this.nivelId = nivelId;
    }
    public Integer getNivel() {
        return nivel;
    }
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    @Override
    public String toString() {
        return "Nivel{" + "nivelId=" + nivelId + ", nivel=" + nivel + '}';
    }
}

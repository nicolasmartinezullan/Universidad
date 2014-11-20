package AccesoDatos;
import Entidades.Materia;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.wagnerandade.coollection.Coollection.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class ServicioDatosMateria extends ServicioDatosBase {
    public ServicioDatosMateria() {
        super();
    }
    /**
     * Devuelve todos las materias que no tengan borrado logico
     *
     * @return lista de materias activas
     */
    public List<Materia> devolverTodos() {
        List<Materia> materias = new ArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sql;
            boolean results;
            sql = ""
                    + "SELECT MateriaId "
                    + ",Nombre "
                    + ",Codigo "
                    + "FROM Materia "
                    + "WHERE Eliminado = 0";
            stmt = getConexion().createStatement();
            results = stmt.execute(sql);
            if (results) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    materias.add(new Materia(
                            rs.getInt("MateriaId"),
                            rs.getString("Nombre"),
                            rs.getInt("Codigo")
                    ));
                }
                rs.close();
            }
            stmt.close();
            sql = ""
                    + "SELECT MateriaId, MateriaRequeridaId "
                    + "FROM MateriaCursarCursada ";
            stmt = getConexion().createStatement();
            results = stmt.execute(sql);
            if (results) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaId"))).first()
                            .agregarCursardaParaCursar(
                                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaRequeridaId"))).first());
                }
            }
            sql = ""
                    + "SELECT MateriaId, MateriaRequeridaId "
                    + "FROM MateriaRendirAprobada ";
            stmt = getConexion().createStatement();
            results = stmt.execute(sql);
            if (results) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaId"))).first()
                            .agregarAprobadaParaRendir(
                                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaRequeridaId"))).first());
                }
                rs.close();
            }
            stmt.close();
            sql = ""
                    + "SELECT MateriaId, MateriaRequeridaId "
                    + "FROM MateriaCursarAprobada ";
            stmt = getConexion().createStatement();
            results = stmt.execute(sql);
            if (results) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaId"))).first()
                            .agregarAprobadaParaCursar(
                                    from(materias).where("getMateriaId", eq(rs.getInt("MateriaRequeridaId"))).first());
                }
                rs.close();
            }
            stmt.close();
        }
        catch (Exception ex) {
            Logger.getLogger(ServicioDatosNivel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (rs != null && !rs.isClosed())
                    rs.close();
                if (stmt != null && !stmt.isClosed())
                    stmt.close();
            }
            catch (Exception ex) {
                Logger.getLogger(ServicioDatosNivel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return materias;
    }
}

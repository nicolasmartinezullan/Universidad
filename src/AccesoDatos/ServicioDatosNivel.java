package AccesoDatos;
import Entidades.Nivel;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class ServicioDatosNivel extends ServicioDatosBase {
    public ServicioDatosNivel() {
        super();
    }
    /**
     * Devuelve todos los cursos que no tengan borrado logico
     *
     * @return lista de cursos activos
     */
    public List<Nivel> devolverTodos() {
        CallableStatement cstmt;
        ResultSet rs = null;
        List<Nivel> niveles = new ArrayList();
        try {
            cstmt = super.getConexion().prepareCall(
                    "{call dbo.Nivel_DevolverTodos}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            boolean resultados = cstmt.execute();
            int filasAfectadas = 0;
            while (resultados || filasAfectadas != -1) {
                if (resultados) {
                    rs = cstmt.getResultSet();
                    break;
                }
                else {
                    filasAfectadas = cstmt.getUpdateCount();
                }
                resultados = cstmt.getMoreResults();
            }
            while (rs.next()) {
                niveles.add(new Nivel(
                        rs.getInt("nivelId"),
                        rs.getInt("nivel")
                ));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ServicioDatosNivel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (super.getConexion() != null && !super.getConexion().isClosed()) {
                    super.getConexion().close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(ServicioDatosNivel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return niveles;
    }
}

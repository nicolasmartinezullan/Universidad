package AccesoDatos;
import Entidades.Carrera;
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
public class ServicioDatosCarrera extends ServicioDatosBase {
    public ServicioDatosCarrera() {
        super();
    }
    /**
     * Devuelve todas las carreras que no tengan borrado logico
     *
     * @return lista de carreras activas
     */
    public List<Carrera> devolverTodos() {
        CallableStatement cstmt;
        ResultSet rs = null;
        List<Carrera> carreras = new ArrayList();
        try {
            cstmt = super.getConexion().prepareCall(
                    "{call dbo.Carrera_DevolverTodos}",
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
                carreras.add(new Carrera(
                        rs.getInt("carreraId")
                        ,rs.getString("nombre")
                        ,rs.getString("codigo")
                ));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ServicioDatosCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (super.getConexion() != null && !super.getConexion().isClosed()) {
                    super.getConexion().close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(ServicioDatosCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return carreras;
    }
}

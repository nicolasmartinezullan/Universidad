package AccesoDatos;
import Entidades.Estudiante;
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
public class ServicioDatosEstudiante extends ServicioDatosBase {
    public ServicioDatosEstudiante() {
        super();
    }
    /**
     * Devuelve todos los estudiantes que no tengan borrado logico
     *
     * @return lista de estudiantes activos
     */
    public List<Estudiante> devolverTodos() {
        CallableStatement cstmt;
        ResultSet rs = null;
        List<Estudiante> estudiantes = new ArrayList();
        try {
            cstmt = super.getConexion().prepareCall(
                    "{call dbo.Estudiante_DevolverTodos}",
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
                estudiantes.add(new Estudiante(
                        rs.getInt("estudianteId"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("dni"),
                        rs.getInt("legajo"),
                        rs.getDate("fechaEnrolamiento"),
                        rs.getInt("carreraId"),
                        rs.getInt("cursoId")
                ));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ServicioDatosEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (super.getConexion() != null && !super.getConexion().isClosed()) {
                    super.getConexion().close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(ServicioDatosEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return estudiantes;
    }
}

package AccesoDatos;
import Modelo.Entidades.Estudiante;
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
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Estudiante> estudiantes = new ArrayList();
        try {
            cstmt = getConexion().prepareCall("{call dbo.Estudiante_DevolverTodos}");
            rs = cstmt.executeQuery();
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
                        rs.getDate("fechaNacimiento"),
                        rs.getInt("carreraId"),
                        rs.getInt("cursoId"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch (SQLException e) {
            Logger.getLogger(ServicioDatosEstudiante.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (getConexion() != null && !getConexion().isClosed()) 
                    getConexion().close();
                if(rs != null && !rs.isClosed())
                    rs.close();
                if(cstmt != null && !cstmt.isClosed())
                    cstmt.close();
            }
            catch (SQLException e) {
                Logger.getLogger(ServicioDatosEstudiante.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e.getMessage());
            }
        }
        return estudiantes;
    }
}

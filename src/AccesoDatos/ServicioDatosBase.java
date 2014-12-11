package AccesoDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion - Noviembre 2014
 */
public abstract class ServicioDatosBase {
    private Connection conexion;
    public Connection getConexion(){return conexion;}
    public ServicioDatosBase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;database=Universidad;integratedSecurity=true;";
            conexion = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServicioDatosBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Ejecuta un SP sin retornar valor
     * @param ownerPuntoSP de ser del tipo [owner].[nombreSP] -> dbo.Curso_DevolverTodos
     */
    protected void ejecutarSPSinRetorno(String ownerPuntoSP){
        CallableStatement cstmt = null;
        try {
            cstmt = getConexion().prepareCall(ownerPuntoSP);
            cstmt.execute();
        }
        catch (Exception e) {
                Logger.getLogger(ServicioDatosBase.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            try {
                if(cstmt != null && !cstmt.isClosed())
                    cstmt.close();
            }
            catch (Exception e) {
                Logger.getLogger(ServicioDatosBase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    protected void ejecutarConsultaSinRetorno(String sql){
        Statement stmt = null;
        try {
            stmt = getConexion().createStatement();
            stmt.executeUpdate(sql);
        }
        catch (Exception e) {
                Logger.getLogger(ServicioDatosBase.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            try {
                if(stmt != null && !stmt.isClosed())
                    stmt.close();
            }
            catch (Exception e) {
                Logger.getLogger(ServicioDatosBase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}

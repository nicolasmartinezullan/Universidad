package AccesoDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}

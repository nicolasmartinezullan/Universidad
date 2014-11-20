package AccesoDatos;
import Entidades.Curso;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class ServicioDatosCurso extends ServicioDatosBase {
    public ServicioDatosCurso() {
        super();
    }
    /**
     * Devuelve todos los cursos que no tengan borrado logico
     *
     * @return lista de cursos activos
     */
    public List<Curso> devolverTodos() {
        CallableStatement cstmt;
        ResultSet rs = null;
        List<Curso> cursos = new ArrayList();
        try {
            cstmt = getConexion().prepareCall(
                    "{call dbo.Curso_DevolverTodos}",
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
                cursos.add(new Curso(
                        rs.getInt("CursoId"),
                        rs.getInt("nivelId"),
                        rs.getInt("carreraId"),
                        rs.getInt("numero")
                ));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (super.getConexion() != null && !super.getConexion().isClosed()) {
                    super.getConexion().close();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cursos;
    }
    
    public boolean existeEsteCurso(int nivelId, int carreraId, int numero){
        int count = 0;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            String sql = ""
                    + "SELECT COUNT(1) "
                    + "FROM Curso "
                    + "WHERE NivelId = " + nivelId + " "
                    + "AND CarreraId = " + carreraId + " "
                    + "AND Numero = " + numero;
            stmt = getConexion().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) 
                count = rs.getInt(1);
            
            
        }
        catch (Exception e) {
                Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            try {
                if (rs != null && !rs.isClosed()) 
                    rs.close();
                if (stmt != null && !stmt.isClosed()) 
                    stmt.close();
            }
            catch (Exception e) {
                Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return (count == 0);
    }
    
    public boolean insertar(int nivelId, int carreraId, int numero){
        CallableStatement cstmt = null;
        ResultSet rs = null;
        int cursoId = -1;
        try {
            cstmt = super.getConexion().prepareCall(
                    "{call dbo.Curso_Insertar(?,?,?)}");
            cstmt.setInt("nivelId", nivelId);
            cstmt.setInt("carreraId", carreraId);
            cstmt.setInt("numero", numero);
            rs = cstmt.executeQuery();
            rs.next();
            cursoId = rs.getInt(1);
        }
        catch (Exception e) {
                Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            try {
                if(rs != null && !rs.isClosed())
                    rs.close();
                if(cstmt != null && !cstmt.isClosed())
                    cstmt.close();
            }
            catch (Exception e) {
                Logger.getLogger(ServicioDatosCurso.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return (cursoId != -1);
    }
}

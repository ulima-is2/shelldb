
package pe.edu.ulima.is2.shelldb;

import java.sql.SQLException;

/**
 *
 * @author hernan (hquintan@ulima.edu.pe)
 */
public interface BDAdapter {
    public void conectarse(String nombreBD) throws SQLException, ClassNotFoundException;
    public String ejecutar(String sql) throws SQLException;
}

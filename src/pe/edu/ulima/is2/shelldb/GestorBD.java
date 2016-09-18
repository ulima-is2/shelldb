
package pe.edu.ulima.is2.shelldb;

import java.sql.SQLException;

/**
 *
 * @author hernan (hquintan@ulima.edu.pe)
 */
public class GestorBD {
    public String realizarOperacion(String tipoBD, String nombreBD, String sql){
        BDFactory factory = new BDFactory();
        BDAdapter adapter = factory.obtenerAdapter(tipoBD);
        
        if (adapter == null){
            return "No especificó un tipo de BD permitido";
        }else{
        
            try {
                adapter.conectarse(nombreBD);
                return adapter.ejecutar(sql);
            } catch (SQLException | ClassNotFoundException se) {
                return se.getMessage();
            }
        }
        
    }
}

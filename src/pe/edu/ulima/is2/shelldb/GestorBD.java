
package pe.edu.ulima.is2.shelldb;

import java.sql.SQLException;

/**
 *
 * @author hernan (hquintan@ulima.edu.pe)
 */
public class GestorBD {
    private static GestorBD instance = null;
    
    private GestorBD(){}
    
    public static GestorBD getInstance(){
        if (instance == null){
            instance = new GestorBD();
        }
        return instance;
    }
    public String realizarOperacion(String tipoBD, String nombreBD, String sql){
        BDFactory factory = BDFactory.getInstance();
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


package pe.edu.ulima.is2.shelldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hernan Quintana (hquintan@ulima.edu.pe)
 */
public class ShellDB {

    /**
     * @param tipoBD Tipo de la base de datos
     * @param nombreBD Nombre de la base de datos
     * @param sentenciaSQL Sentencia SQL a ejecutar
     */
    public static void main(String[] args) {
        /*
         - Obtener el argumento 1: Tipo de Base de Datos
         - Obtener el argumento 2: Nombre de la bd
         - Obtener el argumento 3: Sentencia SQL
         - Ejecutar la sentencia SQL
        */
        String tipoBD = args[0];
        String nombreBD = args[1];
        String sentenciaSQL = args[2];
        
        BDAdapter adapter;
        if (tipoBD.toUpperCase().equals("DERBY")){
            // Base de datos Derby
            try {
                adapter = new DerbyBDAdapter();
                adapter.conectarse(nombreBD);
                String resp = adapter.ejecutar(sentenciaSQL);
                System.out.println(resp);
            } catch (SQLException se)  {
                System.err.println(se);
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
            }
        }else if (tipoBD.toUpperCase().equals("SQLITE")){
            // Base de datos SQLite
            try {
                adapter = new SQLiteBDAdapter();
                adapter.conectarse(nombreBD);
                String resp = adapter.ejecutar(sentenciaSQL);
                System.out.println(resp);
            } catch (SQLException se)  {
                System.err.println(se);
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
            }
            
        }else{
            System.err.println("No especificó un tipo de BD permitido");
        }
    }
    
}

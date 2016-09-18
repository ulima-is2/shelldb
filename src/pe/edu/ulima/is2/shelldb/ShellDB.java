
package pe.edu.ulima.is2.shelldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        
        System.out.println("TipoBD:" + tipoBD);
        System.out.println("sentenciaSQL:" + sentenciaSQL);
        
        if (tipoBD.toUpperCase().equals("DERBY")){
            // Base de datos Derby
            try {
                Connection conn = 
                        DriverManager.getConnection("jdbc:derby:"+ nombreBD +
                                ";create=true;user=is2;password=123");
                
                Statement statement = conn.createStatement();
                if (statement.execute(sentenciaSQL)){
                    // Entrega un resulset
                    ResultSet rs = statement.getResultSet();
                    while(rs.next()){
                        int numColumns = rs.getMetaData().getColumnCount();
                        //String[] valoresColumna = new String[numColumns];
                        for (int i=0; i<numColumns; i++){
                            String nombreColumna = 
                                    rs.getMetaData().getColumnName(i+1);
                            int tipoColumna = 
                                    rs.getMetaData().getColumnType(i+1);
                            
                            String valorColumna = "";
                            if (tipoColumna == java.sql.Types.VARCHAR){
                                valorColumna = rs.getString(nombreColumna);
                            }else if (tipoColumna == java.sql.Types.INTEGER){
                                valorColumna = 
                                        String.valueOf(rs.getInt(nombreColumna));
                            }
                            //valoresColumna[i] = valorColumna;
                            System.out.print(valorColumna + "\t\t");
                        }
                        System.out.println();
                    }
                }else{
                    System.out.println(sentenciaSQL);
                    
                    System.out.println("Se ejecutó la sentencia correctamente");
                }
                
            } catch (SQLException se)  {
                System.err.println(se);
            }
        }else if (tipoBD.toUpperCase().equals("SQLITE")){
            try {
                Class.forName("org.sqlite.JDBC");
                
                Connection conn = 
                        DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
                
                Statement statement = conn.createStatement();
                if (statement.execute(sentenciaSQL)){
                    // Entrega un resulset
                    ResultSet rs = statement.getResultSet();
                    while(rs.next()){
                        int numColumns = rs.getMetaData().getColumnCount();
                        //String[] valoresColumna = new String[numColumns];
                        for (int i=0; i<numColumns; i++){
                            String nombreColumna = 
                                    rs.getMetaData().getColumnName(i+1);
                            int tipoColumna = 
                                    rs.getMetaData().getColumnType(i+1);
                            
                            String valorColumna = "";
                            if (tipoColumna == java.sql.Types.VARCHAR){
                                valorColumna = rs.getString(nombreColumna);
                            }else if (tipoColumna == java.sql.Types.INTEGER){
                                valorColumna = 
                                        String.valueOf(rs.getInt(nombreColumna));
                            }
                            //valoresColumna[i] = valorColumna;
                            System.out.print(valorColumna + "\t\t");
                        }
                        System.out.println();
                    }
                }else{
                    System.out.println(sentenciaSQL);
                    
                    System.out.println("Se ejecutó la sentencia correctamente");
                }
            } catch ( Exception se ) {
                System.err.println(se);
            }
        }else{
            System.err.println("No especificó un tipo de BD permitido");
        }
    }
    
}

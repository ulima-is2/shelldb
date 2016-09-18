package pe.edu.ulima.is2.shelldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hernan (hquintan@ulima.edu.pe)
 */
public class SQLiteBDAdapter implements BDAdapter {

    private Connection mConn;

    @Override
    public void conectarse(String nombreBD) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        mConn = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
    }

    @Override
    public String ejecutar(String sql) throws SQLException{
        Statement statement = mConn.createStatement();
        String resp = "";
        if (statement.execute(sql)) {
            // Entrega un resulset
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                //String[] valoresColumna = new String[numColumns];
                for (int i = 0; i < numColumns; i++) {
                    String nombreColumna
                            = rs.getMetaData().getColumnName(i + 1);
                    int tipoColumna
                            = rs.getMetaData().getColumnType(i + 1);

                    String valorColumna = "";
                    if (tipoColumna == java.sql.Types.VARCHAR) {
                        valorColumna = rs.getString(nombreColumna);
                    } else if (tipoColumna == java.sql.Types.INTEGER) {
                        valorColumna
                                = String.valueOf(rs.getInt(nombreColumna));
                    }
                    //valoresColumna[i] = valorColumna;
                    resp += valorColumna + "\t\t";
                }
                resp += "\n";
            }
        } else {
            resp += sql + "\n";

            resp += "Se ejecutó la sentencia correctamente\n";
        }
        return resp;
    }

}

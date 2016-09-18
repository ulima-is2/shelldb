
package pe.edu.ulima.is2.shelldb;

/**
 *
 * @author hernan (hquintan@ulima.edu.pe)
 */
public class BDFactory {
    private static BDFactory instance = null;
    
    private BDFactory(){}
    
    public static BDFactory getInstance(){
        if (instance == null){
            instance = new BDFactory();
        }
        return instance;
    }
    
    public BDAdapter obtenerAdapter(String tipoBD){
        if (tipoBD.equals("derby")){
            return new DerbyBDAdapter();
        }else if (tipoBD.equals("sqlite")){
            return new SQLiteBDAdapter();
        }else{
            return null;
        }
    }
}

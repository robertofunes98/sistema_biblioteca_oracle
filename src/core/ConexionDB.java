package core;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;



/**
 *
 * @author CATOLICA
 */
public class ConexionDB{
    public Connection conexion=null;
    //Constructor
    
    String db, usuario, clave;
    
    public ConexionDB (String dbR, String usuarioR, String claveR) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        db=dbR;
        usuario=usuarioR;
        clave=claveR;
        
        //instanciando la conexion.
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        conexion=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","SYSTEM","Am029166*");
    }

    
    public DatabaseMetaData getMetaData() throws SQLException
    {
        return conexion.getMetaData();
    }
    
    public ResultSet ejecutar(String consulta) throws SQLException
    {
        //Objeto tipo statement que maneja la consulta.
        Statement cons = this.conexion.createStatement();
        //Ejecutando la consulta.
        return cons.executeQuery(consulta);
    }
    
    public int ejecutarComando(String comando) throws SQLException {
        //Objeto tipo Statement que maneja el comando.
        Statement com = this.conexion.createStatement();
        //Ejecuta el comando.
        int cantidadFilas=com.executeUpdate(comando);
        return cantidadFilas;
    }
    
    private boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public String ejecutarFuncionOracle(String comando, ArrayList<String> alParametros, int typeReturn) throws SQLException {
        CallableStatement cs = null;
        
        //Se realiza la llamada a la funcion de BBDD que retornará un String
        cs = this.conexion.prepareCall(comando);

        cs.registerOutParameter(1, typeReturn); 
        
        for(int i=0; i < alParametros.size(); i++)
        {
            if(isNumeric(alParametros.get(i)))
                cs.setInt(i+2, Integer.parseInt(alParametros.get(i)));
            else
                cs.setString(i+2, alParametros.get(i));
        }
        
        cs.execute(); 
        //se recupera el resultado de la funcion pl/sql
        return cs.getString(1);
    }
    
    public Connection getConex() {
        return conexion;
    }
    
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }
    
    public LinkedList convertirRsToArrayList(ResultSet rsResultado) throws SQLException
    {
        LinkedList<LinkedList> arrayListResultado=new LinkedList<>();
        
        ResultSetMetaData rsmd = rsResultado.getMetaData(); 

        int numeroColumnas = rsmd.getColumnCount(); 
        
        System.out.println("numero de columnas: "+numeroColumnas);
        
        while(rsResultado.next())
        {
            LinkedList<String> datos=new LinkedList<>();
            
            for(int i=1; i<=numeroColumnas; i++)
            {
                datos.add(rsResultado.getString(i));
            }
            
            arrayListResultado.add(datos);
        }
        return arrayListResultado;
            
    }
}
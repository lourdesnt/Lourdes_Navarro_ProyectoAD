package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase donde realizamos la conexion con la base de datos "festival2dam"
 * 
 * @author Lourdes Navarro Tocon
 */
public class ConexionBD {
	
	/**
     * Atributo donde declaramos la instancia del singleton (tipo Connection)
     */
	private static Connection instance = null;
	/**
     * Atributo donde guardamos la url de la base de datos (tipo String)
     */
	private static final String JDBC_URL = "jdbc:mysql://localhost:3308/festival2dam";

	/**
     * Constructor predeterminado
     */
	public ConexionBD(){
        
    }
	
	/**
     * Metodo getConnection donde se devuelve la instancia. Si no existe, nos la crea 
     * @return instance 
     */
	public static Connection getConnection(){
        if (instance == null) {
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "");
            try {
				instance = DriverManager.getConnection(JDBC_URL, props);
				System.out.println("Conexión con la base de datos creada con éxito");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al conectar con la base de datos");
				e.printStackTrace();
			}
        }
        return instance;
    }
    
	/**
	 * Metodo cerrarConexion donde se cierra la conexion con la base de datos
	 */
	public void cerrarConexion(){
        try{
            instance.close();
        }catch(SQLException ex){
            System.out.println("ERROR: No se ha podido cerrar la conexión");
            ex.printStackTrace();
        }
    }
}

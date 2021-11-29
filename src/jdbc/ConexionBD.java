package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConexionBD {
	
	private static Connection instance = null;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3308/festival2dam";

	public ConexionBD(){
        
    }
	
	public static Connection getConnection(){
        if (instance == null) {
            Properties props = new Properties();
            props.put("user", "lnavtoc");
            props.put("password", "root");
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
    
public void cerrarConexion(){
        try{
            instance.close();
        }catch(SQLException ex){
            System.out.println("ERROR: No se ha podido cerrar la conexión");
            ex.printStackTrace();
        }
    }
}

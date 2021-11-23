package jdbc;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceConnection {

	public DataSourceConnection(){
        
    }
    
    public Connection crearConexion(){
        BasicDataSource bdSource = new BasicDataSource();
        bdSource.setUrl("jdbc:mysql://localhost:3308/festival2dam");
        bdSource.setUsername("root");
        bdSource.setPassword("");
        Connection con = null;
        try{
            con = bdSource.getConnection();
			System.out.println("Conexión creada satisfactoriamente");
        } catch (Exception e){
            System.out.println("Error: "+e.toString());
        }
        return con;
    }
}

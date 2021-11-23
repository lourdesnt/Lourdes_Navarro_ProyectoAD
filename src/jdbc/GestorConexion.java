package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexion {

	Connection conn = null;
	
	public GestorConexion(){
        try{
            String JDBC_URL = "jdbc:mysql://localhost:3308/festival2dam";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(JDBC_URL, user, password);
            if(conn != null){
                System.out.println("Éxito al conectar con la base de datos");
            }
        } catch (SQLException ex){
            System.out.println("ERROR: No se ha podido conectar con la base de datos");
            ex.printStackTrace();
        }
    }
	
	public void cerrarConexion(){
        try{
            conn.close();
            System.out.println("Conexión con la base de datos cerrada correctamente");
        }catch(SQLException ex){
            System.out.println("ERROR: No se ha podido cerrar la conexión");
            ex.printStackTrace();
        }
    }
	
	public void creacionTabla() {
		try {
			Statement stmt = conn.createStatement();
			//String query1 = "DROP TABLE IF EXISTS 'Track'";
			//stmt.executeUpdate(query1);
			String query = "CREATE TABLE 'Track' ("
					+ "'trackURI' varchar(100), "
					+ "'trackName' varchar(100), "
					+ "'artistURIs' varchar(100), "
					+ "'artistNames' varchar(100), "
					+ "'albumURI' varchar(100), "
					+ "'albumName' varchar(100), "
					+ "'albumArtistURIs' varchar(100), "
					+ "'albumArtistNames' varchar(100), "
					+ "'albumReleaseDate' varchar(100),  "
					+ "'albumImageURL' varchar(100), "
					+ "'discNumber' varchar(100),  "
					+ "'trackNumber' varchar(100),  "
					+ "'trackDuration' varchar(100), "
					+ "'trackPreviewURL' varchar(100), "
					+ "'explicit' varchar(100), "
					+ "'popularity' varchar(100), "
					+ "'addedBy' varchar(100), "
					+ "'addedAt' varchar(100), "
					+ "PRIMARY KEY ('trackURI'))";
			stmt.executeUpdate(query);
		} catch(SQLException ex) {
			System.out.println("ERROR: no se ha realizado la creación de la tabla correctamente");
            ex.printStackTrace();
		}
	}
	
	public void insertarTrack(){
        try{
            conn.setAutoCommit(false);
            Statement sta = conn.createStatement();
            sta.executeUpdate("INSERT INTO Track " + "VALUES ()");
            conn.commit();
        } catch(SQLException ex){
            System.out.println("ERROR: no se ha realizado la insercción correctamente");
            try{
                if(conn!=null) conn.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            ex.printStackTrace();
        }
    }
}

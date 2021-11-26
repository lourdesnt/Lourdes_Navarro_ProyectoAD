package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import classes.Track;

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
					+ "'trackURI' varchar(255), "
					+ "'trackName' varchar(255), "
					+ "'artistURIs' varchar(255), "
					+ "'artistNames' varchar(255), "
					+ "'albumURI' varchar(255), "
					+ "'albumName' varchar(255), "
					+ "'albumArtistURIs' varchar(255), "
					+ "'albumArtistNames' varchar(255), "
					+ "'albumReleaseDate' varchar(255),  "
					+ "'albumImageURL' varchar(255), "
					+ "'discNumber' varchar(255),  "
					+ "'trackNumber' varchar(255),  "
					+ "'trackDuration' varchar(255), "
					+ "'trackPreviewURL' varchar(255), "
					+ "'explicit' varchar(255), "
					+ "'popularity' varchar(255), "
					+ "'addedBy' varchar(255), "
					+ "'addedAt' varchar(255), "
					+ "PRIMARY KEY ('trackURI'))";
			stmt.executeUpdate(query);
		} catch(SQLException ex) {
			System.out.println("ERROR: no se ha realizado la creación de la tabla correctamente");
            ex.printStackTrace();
		}
	}
	
	public void insertarTrack(Track t){
        try{
        	conn.setAutoCommit(false);
            Statement sta = conn.createStatement();
            sta.executeUpdate("INSERT INTO Track " + "VALUES ('"
            			+t.getTrackURI()+"','"
            			+t.getTrackName()+"','"
            			+t.getArtistURIs()+"','"
            			+t.getArtistNames()+"','"
            			+t.getAlbumURI()+"','"
            			+t.getAlbumName()+"','"
            			+t.getAlbumArtistURIs()+"','"
            			+t.getAlbumArtistNames()+"','"
            			+t.getAlbumReleaseDate()+"','"
            			+t.getAlbumImageURL()+"','"
            			+t.getDiscNumber()+"','"
            			+t.getTrackNumber()+"','"
            			+t.getTrackDuration()+"','"
            			+t.getTrackPreviewURL()+"','"
            			+t.getExplicit()+"','"
            			+t.getPopularity()+"','"
            			+t.getAddedBy()+"','"
            			+t.getAddedAt()+"')");
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
	
	public void insertarTracks(List<Track> playlist){
        for(Track t : playlist) {
        	insertarTrack(t);
        }
    }
	
	
}

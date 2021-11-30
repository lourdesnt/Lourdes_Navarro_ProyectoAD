package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Track;

/**
 * Clase que implementa diferentes metodos para el manejo de una base de datos a
 * traves del conector JDBC
 * 
 * @author Lourdes Navarro Tocon
 *
 */
public class GestorConexion {

	/**
	 * Atributo Conexion
	 */
	Connection con = null;

	/**
	 * Constructor predeterminado donde obtenemos la conexión con la base de datos
	 */
	public GestorConexion() {
		con = ConexionBD.getConnection();

	}

	/**
	 * Metodo para crear la tabla Track con todas sus caracteristicas que
	 * encontramos en el xml
	 */
	public void creacionTabla() {
		try {
			Statement sta = con.createStatement();
			String query = "CREATE TABLE Track (" + "trackURI varchar(255), " + "trackName varchar(100), "
					+ "artistURIs varchar(255), " + "artistNames varchar(255), " + "albumURI varchar(255), "
					+ "albumName varchar(255), " + "albumArtistURIs varchar(255), " + "albumArtistNames varchar(255), "
					+ "albumReleaseDate varchar(255),  " + "albumImageURL varchar(255), " + "discNumber varchar(255),  "
					+ "trackNumber varchar(255),  " + "trackDuration varchar(255), " + "trackPreviewURL varchar(255), "
					+ "explicit varchar(255), " + "popularity varchar(255), " + "addedBy varchar(255), "
					+ "addedAt varchar(255), " + "PRIMARY KEY (trackName))";
			sta.executeUpdate(query);
			sta.close();
			System.out.println("Creación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: no se ha realizado la creación de la tabla correctamente");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para modificar la tabla Track
	 */
	public void modificacionTabla() {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("ALTER TABLE Track ADD caratula VARCHAR(100)"); // La modificacion consiste en añadir una
																				// columna mas
			sta.close();
			System.out.println("Modificación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para eliminar la tabla Track
	 */
	public void eliminarTabla() {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("DROP TABLE IF EXISTS Track");
			sta.close();
			System.out.println("Eliminación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para introducir una nueva track en la base de datos
	 * 
	 * @param t Nueva track a introducir
	 */
	public void insertarTrack(Track t) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Track VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, t.getTrackURI());
			ps.setString(2, t.getTrackName());
			ps.setString(3, t.getArtistURIs());
			ps.setString(4, t.getArtistNames());
			ps.setString(5, t.getAlbumURI());
			ps.setString(6, t.getAlbumName());
			ps.setString(7, t.getAlbumArtistURIs());
			ps.setString(8, t.getAlbumArtistNames());
			ps.setString(9, t.getAlbumReleaseDate());
			ps.setString(10, t.getAlbumImageURL());
			ps.setString(11, t.getDiscNumber());
			ps.setString(12, t.getTrackNumber());
			ps.setString(13, t.getTrackDuration());
			ps.setString(14, t.getTrackPreviewURL());
			ps.setString(15, t.getExplicit());
			ps.setString(16, t.getPopularity());
			ps.setString(17, t.getAddedBy());
			ps.setString(18, t.getAddedAt());

			ps.executeUpdate();
			ps.close();
			System.out.println("Se ha realizado la insercción correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: no se ha realizado la insercción correctamente");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para añadir una lista de tracks a la base de datos
	 * 
	 * @param playlist Lista de tracks
	 */
	public void insertarTracks(List<Track> playlist) {
		for (Track t : playlist) {
			insertarTrack(t);
		}
	}

	/**
	 * Metodo para modificar una track de la base de datos
	 * 
	 * @param t Track que queremos modificar, con los datos nuevos
	 */
	public void modificarTrack(Track t) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE Track SET " + "trackURI = ?" + ", trackName = ?" + ", artistURIs = ?" + ", artistNames = ?"
							+ ", albumURI = ?" + ", albumName = ?" + ", albumArtistURIs = ?" + ", albumArtistNames = ?"
							+ ", albumReleaseDate = ?" + ", albumImageURL = ?" + ", discNumber = ?"
							+ ", trackNumber = ?" + ", trackDuration = ?" + ", trackPreviewURL = ?" + ", explicit = ?"
							+ ", popularity = ?" + ", addedBy = ?" + ", addedAt = ?" + " WHERE trackName = ?");
			ps.setString(1, t.getTrackURI());
			ps.setString(2, t.getTrackName());
			ps.setString(3, t.getArtistURIs());
			ps.setString(4, t.getArtistNames());
			ps.setString(5, t.getAlbumURI());
			ps.setString(6, t.getAlbumName());
			ps.setString(7, t.getAlbumArtistURIs());
			ps.setString(8, t.getAlbumArtistNames());
			ps.setString(9, t.getAlbumReleaseDate());
			ps.setString(10, t.getAlbumImageURL());
			ps.setString(11, t.getDiscNumber());
			ps.setString(12, t.getTrackNumber());
			ps.setString(13, t.getTrackDuration());
			ps.setString(14, t.getTrackPreviewURL());
			ps.setString(15, t.getExplicit());
			ps.setString(16, t.getPopularity());
			ps.setString(17, t.getAddedBy());
			ps.setString(18, t.getAddedAt());
			ps.setString(19, t.getTrackName()); // La trackName, a ser su clave primaria, no se va a modificar

			ps.executeUpdate();
			ps.close();
			System.out.println("Se ha realizado la modificación correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la modificación correctamente");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para eliminar una track de la base de datos
	 * 
	 * @param trackName Nombre del track a eliminar
	 */
	public void eliminarTrack(String trackName) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Track WHERE trackName = ?");
			ps.setString(1, trackName);

			ps.executeUpdate();
			ps.close();
			System.out.println("Se ha realizado la eliminación correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la eliminación correctamente");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo para eliminar una track de la base de datos
	 * 
	 * @param t Track a eliminar
	 */
	public void eliminarTrack(Track t) {
		eliminarTrack(t.getTrackName()); // Eliminara esa track a traves de su clave primaria
	}

	/**
	 * Metodo para listar todas las tracks de la base de datos
	 * 
	 * @return Lista con todas las tracks
	 */
	public List<Track> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Track> result = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT * FROM Track");
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new Track(rs.getString("trackURI"), rs.getString("trackName"), rs.getString("artistURIs"),
						rs.getString("artistNames"), rs.getString("albumURI"), rs.getString("albumName"),
						rs.getString("albumArtistURIs"), rs.getString("albumArtistNames"),
						rs.getString("albumReleaseDate"), rs.getString("albumImageURL"), rs.getString("discNumber"),
						rs.getString("trackNumber"), rs.getString("trackDuration"), rs.getString("trackPreviewURL"),
						rs.getString("explicit"), rs.getString("popularity"), rs.getString("addedBy"),
						rs.getString("addedAt")));

			}

			rs.close();
			ps.close();

			System.out.println("Se ha realizado la consulta correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: No se ha podido realizar la consulta correctamente");
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * Metodo para mostrar una track de la base de datos
	 * 
	 * @param trackName Nombre de la track
	 * @return Track de la base de datos
	 */
	public Track findByID(String trackName) {
		Track result = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Track WHERE trackName = ?");
			ps.setString(1, trackName);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = new Track(rs.getString("trackURI"), rs.getString("trackName"), rs.getString("artistURIs"),
						rs.getString("artistNames"), rs.getString("albumURI"), rs.getString("albumName"),
						rs.getString("albumArtistURIs"), rs.getString("albumArtistNames"),
						rs.getString("albumReleaseDate"), rs.getString("albumImageURL"), rs.getString("discNumber"),
						rs.getString("trackNumber"), rs.getString("trackDuration"), rs.getString("trackPreviewURL"),
						rs.getString("explicit"), rs.getString("popularity"), rs.getString("addedBy"),
						rs.getString("addedAt"));

			}
			rs.close();
			ps.close();
			
			System.out.println("Se ha realizado la consulta correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: No se ha podido realizar la consulta correctamente");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Metodo para cerrar la conexion con la base de datos
	 */
	public void cerrarConexion() {
		try {
			con.close();
			System.out.println("Conexión cerrada correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al cerrar la conexión");
			e.printStackTrace();
		}
	}

}

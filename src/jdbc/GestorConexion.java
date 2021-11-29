package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import classes.Track;

public class GestorConexion {

	private static Connection con = null;

	public GestorConexion() {
		con = ConexionBD.getConnection();
	}

	public void creacionTabla() {
		try {
			Statement stmt = con.createStatement();
			// String query1 = "DROP TABLE IF EXISTS 'Track'";
			// stmt.executeUpdate(query1);
			String query = "CREATE TABLE 'Track' (" + "'trackURI' varchar(255), " + "'trackName' varchar(255), "
					+ "'artistURIs' varchar(255), " + "'artistNames' varchar(255), " + "'albumURI' varchar(255), "
					+ "'albumName' varchar(255), " + "'albumArtistURIs' varchar(255), "
					+ "'albumArtistNames' varchar(255), " + "'albumReleaseDate' varchar(255),  "
					+ "'albumImageURL' varchar(255), " + "'discNumber' varchar(255),  "
					+ "'trackNumber' varchar(255),  " + "'trackDuration' varchar(255), "
					+ "'trackPreviewURL' varchar(255), " + "'explicit' varchar(255), " + "'popularity' varchar(255), "
					+ "'addedBy' varchar(255), " + "'addedAt' varchar(255), " + "PRIMARY KEY ('trackURI'))";
			stmt.executeUpdate(query);
			stmt.close();
			System.out.println("Creación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: no se ha realizado la creación de la tabla correctamente");
			ex.printStackTrace();
		}
	}

	public void modificacionTabla() {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("ALTER TABLE track ADD caratula VARCHAR(100)");
			sta.close();
			System.out.println("Modificación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void eliminarTabla() {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("DROP TABLE track");
			sta.close();
			System.out.println("Eliminación de la tabla realizada correctamente");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertarTrack(Track t) {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("INSERT INTO Track " + "VALUES ('" + t.getTrackURI() + "','" + t.getTrackName() + "','"
					+ t.getArtistURIs() + "','" + t.getArtistNames() + "','" + t.getAlbumURI() + "','"
					+ t.getAlbumName() + "','" + t.getAlbumArtistURIs() + "','" + t.getAlbumArtistNames() + "','"
					+ t.getAlbumReleaseDate() + "','" + t.getAlbumImageURL() + "','" + t.getDiscNumber() + "','"
					+ t.getTrackNumber() + "','" + t.getTrackDuration() + "','" + t.getTrackPreviewURL() + "','"
					+ t.getExplicit() + "','" + t.getPopularity() + "','" + t.getAddedBy() + "','" + t.getAddedAt()
					+ "')");
			sta.close();
			System.out.println("Se ha realizado la insercción correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: no se ha realizado la insercción correctamente");
			ex.printStackTrace();
		}
	}

	public void insertarTracks(List<Track> playlist) {
		for (Track t : playlist) {
			insertarTrack(t);
		}
	}

	public void modificarTrack(Track t) {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("UPDATE track SET " + "trackURI = " + t.getTrackURI() + ", trackName = "
					+ t.getTrackName() + ", artistURIs = " + t.getArtistURIs() + ", artistNames = " + t.getArtistNames()
					+ ", albumURI = " + t.getAlbumURI() + ", albumName = " + t.getAlbumName() + ", albumArtistURIs = "
					+ t.getAlbumArtistURIs() + ", albumArtistNames = " + t.getAlbumArtistNames()
					+ ", albumReleaseDate = " + t.getAlbumReleaseDate() + ", albumImageURL = " + t.getAlbumImageURL()
					+ ", discNumber = " + t.getDiscNumber() + ", trackNumber = " + t.getTrackNumber()
					+ ", trackDuration = " + t.getTrackDuration() + ", trackPreviewURL = " + t.getTrackPreviewURL()
					+ ", explicit = " + t.getExplicit() + ", popularity = " + t.getPopularity() + ", addedBy = "
					+ t.getAddedBy() + ", addedAt = " + t.getAddedAt() + ", WHERE trackURI = " + t.getTrackURI());
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la modificación correctamente");
			ex.printStackTrace();
		}
	}

	public void eliminarTrack(String trackURI) {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("DELETE FROM track WHERE trackURI = " + trackURI);
			sta.close();
			System.out.println("Se ha realizado la eliminación correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la eliminación correctamente");
			ex.printStackTrace();
		}
	}

	public void eliminarTrack(Track t) {
		eliminarTrack(t.getTrackURI());
	}
}

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import classes.Track;


public class GestorConexion {

	private static Connection con = null;

	public GestorConexion() {
		con = ConexionBD.getConnection();
	}

	public void creacionTabla() {
		try {
			Statement sta = con.createStatement();
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
			sta.executeUpdate(query);
			sta.close();
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
			PreparedStatement ps = con.prepareStatement("INSERT INTO track VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1,t.getTrackURI());
			ps.setString(2,t.getTrackName());
			ps.setString(3,t.getArtistURIs());
			ps.setString(4,t.getArtistNames());
			ps.setString(5,t.getAlbumURI());
			ps.setString(6,t.getAlbumName());
			ps.setString(7,t.getAlbumArtistURIs());
			ps.setString(8,t.getAlbumArtistNames());
			ps.setString(9,t.getAlbumReleaseDate());
			ps.setString(10,t.getAlbumImageURL());
			ps.setString(11,t.getDiscNumber());
			ps.setString(12,t.getTrackNumber());
			ps.setString(13,t.getTrackDuration());
			ps.setString(14,t.getTrackPreviewURL());
			ps.setString(15,t.getExplicit());
			ps.setString(16,t.getPopularity());
			ps.setString(17,t.getAddedBy());
			ps.setString(18,t.getAddedAt());

			ps.close();
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
			PreparedStatement ps = con.prepareStatement("UPDATE track SET " + 
														"trackURI = ?" +
														", trackName = ?" + 
														", artistURIs = ?" +
														", artistNames = ?" +
														", albumURI = ?" +
														", albumName = ?" +
														", albumArtistURIs = ?" +
														", albumArtistNames = ?" + 
														", albumReleaseDate = ?" +
														", albumImageURL = ?" +
														", discNumber = ?" +
														", trackNumber = ?" +
														", trackDuration = ?" +
														", trackPreviewURL = ?" +
														", explicit = ?" +
														", popularity = ?" +
														", addedBy = ?" +
														", addedAt = ?" +
														", WHERE trackURI = ?");
			ps.setString(1,t.getTrackURI());
			ps.setString(2,t.getTrackName());
			ps.setString(3,t.getArtistURIs());
			ps.setString(4,t.getArtistNames());
			ps.setString(5,t.getAlbumURI());
			ps.setString(6,t.getAlbumName());
			ps.setString(7,t.getAlbumArtistURIs());
			ps.setString(8,t.getAlbumArtistNames());
			ps.setString(9,t.getAlbumReleaseDate());
			ps.setString(10,t.getAlbumImageURL());
			ps.setString(11,t.getDiscNumber());
			ps.setString(12,t.getTrackNumber());
			ps.setString(13,t.getTrackDuration());
			ps.setString(14,t.getTrackPreviewURL());
			ps.setString(15,t.getExplicit());
			ps.setString(16,t.getPopularity());
			ps.setString(17,t.getAddedBy());
			ps.setString(18,t.getAddedAt());
			ps.setString(19,t.getTrackURI());
			ps.close();
			System.out.println("Se ha realizado la modificación correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la modificación correctamente");
			ex.printStackTrace();
		}
	}

	public void eliminarTrack(String trackURI) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM track WHERE trackURI = ?");
			ps.setString(1, trackURI);
			ps.close();
			System.out.println("Se ha realizado la eliminación correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: No se ha realizado la eliminación correctamente");
			ex.printStackTrace();
		}
	}

	public void eliminarTrack(Track t) {
		eliminarTrack(t.getTrackURI());
	}
	
	public List<Track> findAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Track> result = null;
		try {
			ps = con.prepareStatement("SELECT * FROM track");
			rs = ps.executeQuery();
			while (rs.next()) {
				if (result == null) {
					result = new ArrayList<>();
					result.add(new Track(
			        					rs.getString("trackURI"),
			        					rs.getString("trackName"),
			        					rs.getString("artistURIs"),
			        					rs.getString("artistNames"),
			        					rs.getString("albumURI"),
			        					rs.getString("albumName"),
			        					rs.getString("albumArtistURIs"),
			        					rs.getString("albumArtistNames"),
			        					rs.getString("albumReleaseDate"),
			        					rs.getString("albumImageURL"),
			        					rs.getString("discNumber"),
			        					rs.getString("trackNumber"),
			        					rs.getString("trackDuration"),
			        					rs.getString("trackPreviewURL"),
			        					rs.getString("explicit"),
			        					rs.getString("popularity"),
			        					rs.getString("addedBy"),
			        					rs.getString("addedAt")
			        					)
								);
				}
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
	
	public Track findByID(String trackURI) {
		Statement sta = null;
		Track result = null;
		try {
			sta = con.createStatement();
			String query = "SELECT * FROM track WHERE trackURI = "+trackURI;
		
			ResultSet rs = sta.executeQuery(query);

			if (rs.next()) {
				result = new Track(
						rs.getString("trackURI"),
						rs.getString("trackName"),
						rs.getString("artistURIs"),
						rs.getString("artistNames"),
						rs.getString("albumURI"),
						rs.getString("albumName"),
						rs.getString("albumArtistURIs"),
						rs.getString("albumArtistNames"),
						rs.getString("albumReleaseDate"),
						rs.getString("albumImageURL"),
						rs.getString("discNumber"),
						rs.getString("trackNumber"),
						rs.getString("trackDuration"),
						rs.getString("trackPreviewURL"),
						rs.getString("explicit"),
						rs.getString("popularity"),
						rs.getString("addedBy"),
						rs.getString("addedAt")
						);
				
			}
			rs.close();
			System.out.println("Se ha realizado la consulta correctamente");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			System.out.println("ERROR: No se ha podido realizar la consulta correctamente");
			e.printStackTrace();
		}

		return result;
    }
	
}

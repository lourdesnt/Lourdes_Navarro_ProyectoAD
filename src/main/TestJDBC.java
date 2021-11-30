package main;

import java.io.File;

import classes.Track;
import dom.AccesoXML;
import jdbc.GestorConexion;

public class TestJDBC {

	public static void main(String[] args) {
		File xml = new File("src/data/festival2DAM.xml");
		AccesoXML dom = new AccesoXML();
		dom.abrirXMLDOM(xml);
		dom.recorrerDOM();
	
		GestorConexion con = new GestorConexion();
		con.eliminarTabla();
		con.creacionTabla();
		con.insertarTracks(dom.playlist);
		Track track = new Track("spotify:track:3aPGTMB6sFfDYQ3YvSabc1", "Venom",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "spotify:album:5t7953yu6zYf5A543XRiCH", "Kamikaze",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "2018-08-31",
				"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "1", "13", "43000",
				"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
				"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
		con.insertarTrack(track);
		Track newTrack = new Track("spotify:track:3aPGTMB6sFfDYQ3Yvadwr3", "Venom",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "spotify:album:5t7953yu6zYf5A543XRiCH", "Music To Be Murdered By",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "2020-01-17",
				"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "10", "8", "60900",
				"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
				"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
		con.modificarTrack(newTrack);
		System.out.println(con.findAll());
		System.out.println(con.findByID(newTrack.getTrackName()));
		con.eliminarTrack(newTrack.getTrackName());
		
	}
}

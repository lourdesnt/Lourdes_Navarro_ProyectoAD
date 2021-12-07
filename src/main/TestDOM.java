package main;

import java.io.File;

import classes.Track;
import dom.AccesoXML;
import jdbc.GestorConexion;

/**
 * Clase Test donde probaremos los distintos métodos DOM para el manejo de documento XML
 * @author Lourdes Navarro Tocon
 *
 */
public class TestDOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File xml = new File("src/data/festival2DAM.xml");

		AccesoXML dom = new AccesoXML();
		dom.abrirXMLDOM(xml);
		Track track = new Track("spotify:track:3aPGTMB6sFfDYQ3YvSabc1", "Venom",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "spotify:album:5t7953yu6zYf5A543XRiCH", "Kamikaze",
				" spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "2018-08-31",
				"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "1", "13", "43000",
				"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
				"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
		dom.aniadirTrack(track);
		Track newTrack = new Track("spotify:track:3aPGTMB6sFfDYQ3YvSabc1", "Darkness",
				"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "spotify:album:5t7953yu6zYf5A543XRiCH", "Music To Be Murdered By",
				" spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", "Eminem", "2020-01-17",
				"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "10", "8", "60900",
				"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
				"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
		dom.modificarTrack("Venom", newTrack);
		dom.eliminarTrack("Darkness");
		String datos = dom.recorrerDOM();
		dom.writeXML();
		System.out.println(datos);
	}

}

package main;

import java.io.File;
import java.util.Scanner;

import classes.Track;
import dom.AccesoXML;

/**
 * Clase Test donde el usuario podrá acceder y trabajar con datos del xml a través de un menú usando DOM
 * 
 * @author Lourdes Navarro Tocon
 *
 */
public class MenuDOM {

	static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sc = new Scanner(System.in);

		String opcion;

		File xml = new File("src/data/festival2DAM.xml");
		AccesoXML dom = new AccesoXML();
		dom.abrirXMLDOM(xml);

		do {
			menu();
			opcion = sc.nextLine();

			System.out.println("");

			switch (opcion) {
			case "0":
				break;
			case "1":
				System.out.println(dom.recorrerDOM());
				break;
			case "2":
				System.out.println("Nombre del track:");
				String trackName = sc.nextLine();
				System.out.println("Nombre del artista:");
				String artistName = sc.nextLine();
				System.out.println("Nombre del album:");
				String albumName = sc.nextLine();
				Track t = new Track("spotify:track:3aPGTMB6sFfDYQ3YvSabc1", trackName,
						"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", artistName, "spotify:album:5t7953yu6zYf5A543XRiCH",
						albumName, "spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", artistName, "2020-01-17",
						"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "10", "8", "60900",
						"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
						"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
				dom.aniadirTrack(t);
				dom.writeXML();
				break;
			case "3":
				System.out.println("Nombre del track a modificar:");
				String trackNameOld = sc.nextLine();
				System.out.println("Nuevo nombre del track:");
				String trackNameNew = sc.nextLine();
				System.out.println("Nombre del artista del track a modificar:");
				String artistNameNew = sc.nextLine();
				System.out.println("Nombre del album del track a modificar:");
				String albumNameNew = sc.nextLine();
				Track tNew = new Track("spotify:track:3aPGTMB6sFfDYQ3YvSabc1", trackNameNew,
						"spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", artistNameNew, "spotify:album:5t7953yu6zYf5A543XRiCH",
						albumNameNew, "spotify:artist:7dGJo4pcD2V6oG8kP0tJRR", artistNameNew, "2020-01-17",
						"https://i.scdn.co/image/bc67616d0000b273dbb3dd83da45b7d7f13b1b43", "10", "8", "60900",
						"https://p.scdn.co/mp3-preview/9aed324f09bb71798f1f0943dcsjfhds38b8?cid=9950ac751e34487dbbe027c4fd7f8e99",
						"true", "70", "spotify:user:lysun", "2021-11-23T18:36:15Z");
				dom.modificarTrack(trackNameOld, tNew);
				dom.writeXML();
				break;
			case "4":
				System.out.println("Nombre del track a eliminar:");
				String trackNameDelete = sc.nextLine();
				dom.eliminarTrack(trackNameDelete);
				dom.writeXML();
				break;
			default:
				System.out.println("Opción no válida. Introduzca una opción válida, por favor.");
			}

		} while (!opcion.equals("0"));

		sc.close();

	}

	public static void menu() {
		System.out.println("Elige una opción:");
		System.out.println("===========================");
		System.out.println("1. Listar Tracks");
		System.out.println("2. Añadir Track");
		System.out.println("3. Modificar Track");
		System.out.println("4. Eliminar Track");
		System.out.println("0. SALIR DE LA APLICACIÓN\n");
		System.out.print("Introduzca una opcion: ");
	}

}

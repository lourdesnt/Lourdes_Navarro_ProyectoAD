package dom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import classes.Track;

/**
 * Clase que implemente diferentes metodos para el manejo de datos de un documento XML a traves de DOM
 * 
 * @author Lourdes Navarro Tocon
 *
 */
public class AccesoXML {

	Document doc;

	public List<Track> playlist;
	
	

	public AccesoXML() {
		playlist = new ArrayList<Track>();
	}

	/**
	 * Metodo que genera el árbol DOM del XML
	 * 
	 * @param fichero Fichero XML al que vamos a acceder
	 * @return 0 si se ha realizado con éxito, -1 si ha habido algún fallo
	 */
	public int abrirXMLDOM(File fichero) {
		doc = null;
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setIgnoringComments(true);

			factory.setIgnoringElementContentWhitespace(true);

			factory.setValidating(false);

			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(fichero);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Metodo que recorre el documento XML
	 * 
	 * @return datos del XML
	 */
	public String recorrerDOM() {
		String[] datosNodos = null;
		String salida = "";
		Node node;

		Node raiz = doc.getFirstChild();

		NodeList hijos = raiz.getChildNodes();

		for (int i = 0; i < hijos.getLength(); i++) {
			node = hijos.item(i);

			Track t = new Track();

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				datosNodos = procesarTracks(node);

				t.setTrackURI(datosNodos[0]);
				t.setTrackName(datosNodos[1]);
				t.setArtistURIs(datosNodos[2]);
				t.setArtistNames(datosNodos[3]);
				t.setAlbumURI(datosNodos[4]);
				t.setAlbumName(datosNodos[5]);
				t.setAlbumArtistURIs(datosNodos[6]);
				t.setAlbumArtistNames(datosNodos[7]);
				t.setAlbumReleaseDate(datosNodos[8]);
				t.setAlbumImageURL(datosNodos[9]);
				t.setDiscNumber(datosNodos[10]);
				t.setTrackNumber(datosNodos[11]);
				t.setTrackDuration(datosNodos[12]);
				t.setTrackPreviewURL(datosNodos[13]);
				t.setExplicit(datosNodos[14]);
				t.setPopularity(datosNodos[15]);
				t.setAddedBy(datosNodos[16]);
				t.setAddedAt(datosNodos[17]);
				
				playlist.add(t);

				salida = salida + "\n"+t.toString();
				salida = salida + "\n ------------------------------------------";
			}
		}
		return salida;
	}

	/**
	 * Metodo que procesa el documento XML
	 * 
	 * @param node Nodo del XML
	 * @return Nodos del XML
	 */
	public String[] procesarTracks(Node node) {
		String[] datos = new String[18];
		Node temp = null;
		int c = 0;

		NodeList nodos = node.getChildNodes();

		for (int i = 0; i < nodos.getLength(); i++) {
			temp = nodos.item(i);

			if (temp.getNodeType() == Node.ELEMENT_NODE) {
				datos[c] = temp.getTextContent();
				c++;
			}
		}
		return datos;
	}

	/**
	 * Metodo para añadir una nueva track al documento XML
	 * @param doc XML
	 * @param track Track a añadir
	 * @return 0 si se ha realizado con exito, -1 si ha habido algun fallo
	 */
	private int aniadirTrack(Document doc, Track track) {
		try {
			Node ntrack = doc.createElement("track");
			Node raiz = doc.getFirstChild(); // es igual a Node raiz = doc.getChildNode().item(0);
			raiz.appendChild(ntrack);

			Node ntrackURI = doc.createElement("TrackURI");
			Node ntrackURI_txt = doc.createTextNode(track.getTrackURI());
			ntrack.appendChild(ntrackURI);
			ntrackURI.appendChild(ntrackURI_txt);

			Node ntrackName = doc.createElement("TrackName");
			Node ntrackName_txt = doc.createTextNode(track.getTrackName());
			ntrack.appendChild(ntrackName);
			ntrackName.appendChild(ntrackName_txt);

			Node nartistURIs = doc.createElement("ArtistURIs");
			Node nartistURIs_txt = doc.createTextNode(track.getArtistURIs());
			ntrack.appendChild(nartistURIs);
			nartistURIs.appendChild(nartistURIs_txt);

			Node nartistNames = doc.createElement("ArtistNames");
			Node nartistNames_txt = doc.createTextNode(track.getArtistNames());
			ntrack.appendChild(nartistNames);
			nartistNames.appendChild(nartistNames_txt);

			Node nalbumURI = doc.createElement("AlbumURI");
			Node nalbumURI_txt = doc.createTextNode(track.getAlbumURI());
			ntrack.appendChild(nalbumURI);
			nalbumURI.appendChild(nalbumURI_txt);

			Node nalbumName = doc.createElement("AlbumName");
			Node nalbumName_txt = doc.createTextNode(track.getAlbumName());
			ntrack.appendChild(nalbumName);
			nalbumName.appendChild(nalbumName_txt);

			Node nalbumArtistURIs = doc.createElement("AlbumArtistURIs");
			Node nalbumArtistURIs_txt = doc.createTextNode(track.getAlbumArtistURIs());
			ntrack.appendChild(nalbumArtistURIs);
			nalbumArtistURIs.appendChild(nalbumArtistURIs_txt);

			Node nalbumArtistNames = doc.createElement("AlbumArtistNames");
			Node nalbumArtistNames_txt = doc.createTextNode(track.getAlbumArtistNames());
			ntrack.appendChild(nalbumArtistNames);
			nalbumArtistNames.appendChild(nalbumArtistNames_txt);

			Node nalbumReleaseDate = doc.createElement("AlbumReleaseDate");
			Node nalbumReleaseDate_txt = doc.createTextNode(track.getAlbumReleaseDate());
			ntrack.appendChild(nalbumReleaseDate);
			nalbumReleaseDate.appendChild(nalbumReleaseDate_txt);

			Node nalbumImageURL = doc.createElement("AlbumImageURL");
			Node nalbumImageURL_txt = doc.createTextNode(track.getAlbumImageURL());
			ntrack.appendChild(nalbumImageURL);
			nalbumImageURL.appendChild(nalbumImageURL_txt);

			Node ndiscNumber = doc.createElement("DiscNumber");
			Node ndiscNumber_txt = doc.createTextNode(track.getDiscNumber());
			ntrack.appendChild(ndiscNumber);
			ndiscNumber.appendChild(ndiscNumber_txt);

			Node ntrackNumber = doc.createElement("TrackNumber");
			Node ntrackNumber_txt = doc.createTextNode(track.getTrackNumber());
			ntrack.appendChild(ntrackNumber);
			ntrackNumber.appendChild(ntrackNumber_txt);

			Node ntrackDuration = doc.createElement("TrackDuration");
			Node ntrackDuration_txt = doc.createTextNode(track.getTrackDuration());
			ntrack.appendChild(ntrackDuration);
			ntrackDuration.appendChild(ntrackDuration_txt);

			Node ntrackPreviewURL = doc.createElement("TrackPreviewURL");
			Node ntrackPreviewURL_txt = doc.createTextNode(track.getTrackPreviewURL());
			ntrack.appendChild(ntrackPreviewURL);
			ntrackPreviewURL.appendChild(ntrackPreviewURL_txt);

			Node nexplicit = doc.createElement("Explicit");
			Node nexplicit_txt = doc.createTextNode(track.getExplicit());
			ntrack.appendChild(nexplicit);
			nexplicit.appendChild(nexplicit_txt);

			Node npopularity = doc.createElement("Popularity");
			Node npopularity_txt = doc.createTextNode(track.getPopularity());
			ntrack.appendChild(npopularity);
			npopularity.appendChild(npopularity_txt);

			Node naddedBy = doc.createElement("AddedBy");
			Node naddedBy_txt = doc.createTextNode(track.getAddedBy());
			ntrack.appendChild(naddedBy);
			naddedBy.appendChild(naddedBy_txt);

			Node naddedAt = doc.createElement("AddedAt");
			Node naddedAt_txt = doc.createTextNode(track.getAddedAt());
			ntrack.appendChild(naddedAt);
			naddedAt.appendChild(naddedAt_txt);

			playlist.add(track);

			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Metodo para añadir una nueva track al documento XML
	 * @param track Track a añadir
	 * @return 0 si se ha realizado con exito, -1 si ha habido algun fallo
	 */
	public int aniadirTrack(Track track) {
		return aniadirTrack(doc, track);
	}

	/**
	 * Metodo para eliminar una track del documento XML
	 * @param trackName Nombre de la track
	 * @return 0 si se ha realizado con exito, -1 si ha habido algun fallo
	 */
	public int eliminarTrack(String trackName) {
		try {
			NodeList canciones = doc.getElementsByTagName("track");
			for (int i = 0; i < canciones.getLength(); i++) {
				Element track = (Element) canciones.item(i);
				if (track.getElementsByTagName("TrackName").item(0).getTextContent().equals(trackName)
						&& playlist.stream().anyMatch(tr -> tr.getTrackName().equals(trackName))) {
					track.getParentNode().removeChild(track);
					playlist.removeIf(t -> t.getTrackName().equals(trackName));
					return 0;
				}
			}
			System.out.println("No existe la canción");
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Método para modificar una track del documento XML
	 * @param trackName Nombre de la track a eliminar
	 * @param track Track con los datos actualizados
	 * @return 0 si se ha realizado con exito, -1 si ha habido algun fallo
	 */
	public int modificarTrack(String trackName, Track track) {
		try {
			NodeList canciones = doc.getElementsByTagName("track");
			for (int i = 0; i < canciones.getLength(); i++) {
				Element t = (Element) canciones.item(i);
				if (t.getElementsByTagName("TrackName").item(0).getTextContent().equals(trackName)
						&& playlist.stream().anyMatch(tr -> tr.getTrackName().equals(trackName))) {
					t.getElementsByTagName("TrackURI").item(0).setTextContent(track.getTrackURI());
					t.getElementsByTagName("TrackName").item(0).setTextContent(track.getTrackName());
					t.getElementsByTagName("ArtistURIs").item(0).setTextContent(track.getArtistURIs());
					t.getElementsByTagName("ArtistNames").item(0).setTextContent(track.getArtistNames());
					t.getElementsByTagName("AlbumURI").item(0).setTextContent(track.getAlbumURI());
					t.getElementsByTagName("AlbumName").item(0).setTextContent(track.getAlbumName());
					t.getElementsByTagName("AlbumArtistURIs").item(0).setTextContent(track.getAlbumArtistURIs());
					t.getElementsByTagName("AlbumArtistNames").item(0).setTextContent(track.getAlbumArtistNames());
					t.getElementsByTagName("AlbumReleaseDate").item(0).setTextContent(track.getAlbumReleaseDate());
					t.getElementsByTagName("AlbumImageURL").item(0).setTextContent(track.getAlbumImageURL());
					t.getElementsByTagName("DiscNumber").item(0).setTextContent(track.getDiscNumber());
					t.getElementsByTagName("TrackNumber").item(0).setTextContent(track.getTrackNumber());
					t.getElementsByTagName("TrackDuration").item(0).setTextContent(track.getTrackDuration());
					t.getElementsByTagName("TrackPreviewURL").item(0).setTextContent(track.getTrackPreviewURL());
					t.getElementsByTagName("Explicit").item(0).setTextContent(track.getExplicit());
					t.getElementsByTagName("Popularity").item(0).setTextContent(track.getPopularity());
					t.getElementsByTagName("AddedBy").item(0).setTextContent(track.getAddedBy());
					t.getElementsByTagName("AddedAt").item(0).setTextContent(track.getAddedAt());

					playlist.removeIf(tr -> tr.getTrackName().equals(trackName));
					playlist.add(track);
					
					return 0;
				}
			}
			System.out.println("No existe la canción");
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Metodo para escribir un nuevo documento XML
	 */
	public void writeXML() {
		try {

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource domSource = new DOMSource(doc);

			StreamResult streamResult = new StreamResult(new File("src/data/festival2DAMNew.xml")); //Si el nombre del XML fuese el mismo, sobreescribiria el ya existente

			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

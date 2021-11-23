package dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AccesoXML {
	
	Document doc;
	
	/**
     * Este método genera el árbol DOM de un documento XML
     * @param fichero Fichero XML al que vamos a acceder
     * @return 0 si se ha realizado con éxito, -1 si ha habido algún fallo
     */
    public int abrirXMLDOM(File fichero){
        doc = null;
        try{
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            factory.setIgnoringComments(true);
            
            factory.setIgnoringElementContentWhitespace(true);
            
            factory.setValidating(false);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            doc = builder.parse(fichero);
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Método que recorre el documento XML
     * @return 
     */
    public String recorrerDOM(){
        String[] datosNodos = null;
        String salida = "";
        Node node;
        
        Node raiz = doc.getFirstChild();
        
        NodeList hijos = raiz.getChildNodes();
        
        for(int i=0; i<hijos.getLength(); i++){
            node = hijos.item(i);
            
            if(node.getNodeType()==Node.ELEMENT_NODE){
                
                datosNodos = procesarTracks(node);
                
                salida = salida + "\n " + "TrackURI: " + datosNodos[0];
                salida = salida + "\n " + "TrackName: " + datosNodos[1];
                salida = salida + "\n " + "ArtistURIs: " + datosNodos[2];
                salida = salida + "\n " + "ArtistNames: " + datosNodos[3];
                salida = salida + "\n " + "AlbumURI: " + datosNodos[4];
                salida = salida + "\n " + "AlbumName: " + datosNodos[5];
                salida = salida + "\n " + "AlbumArtistURIs: " + datosNodos[6];
                salida = salida + "\n " + "AlbumArtistNames: " + datosNodos[7];
                salida = salida + "\n " + "AlbumReleaseDate: " + datosNodos[8];
                salida = salida + "\n " + "AlbumImageURL: " + datosNodos[9];
                salida = salida + "\n " + "DiscNumber: " + datosNodos[10];
                salida = salida + "\n " + "TrackNumber: " + datosNodos[11];
                salida = salida + "\n " + "TrackDuration: " + datosNodos[12];
                salida = salida + "\n " + "TrackPreviewURL: " + datosNodos[13];
                salida = salida + "\n " + "Explicit: " + datosNodos[14];
                salida = salida + "\n " + "Popularity: " + datosNodos[15];
                salida = salida + "\n " + "AddedBy: " + datosNodos[16];
                salida = salida + "\n " + "AddedAt: " + datosNodos[17];
                salida = salida + "\n ------------------------------------------";
            }
        }
        return salida;
    }
    
    /**
     * Método que procesa el documento XML
     * @param node Nodo del XML
     * @return Nodos del XML
     */
    public String[] procesarTracks(Node node){
        String[] datos = new String[18];
        Node temp = null;
        int c = 0;
        
        NodeList nodos = node.getChildNodes();
        
        for(int i=0; i<nodos.getLength(); i++){
            temp = nodos.item(i);
            
            if(temp.getNodeType()==Node.ELEMENT_NODE){
                datos[c] = temp.getTextContent();
                c++;
            }
        }
        return datos;
    }
    
    private int aniadirDOM(Document doc, String trackURI, String trackName, String artistURIs, String artistNames, String albumURI, String albumName, String albumArtistURIs, String albumArtistNames, String albumReleaseDate, String albumImageURL, String discNumber, String trackNumber, String trackDuration, String trackPreviewURL, String explicit, String popularity, String addedBy, String addedAt){
        try {
            Node ntrack = doc.createElement("track");
            Node raiz = doc.getFirstChild(); //es igual a Node raiz = doc.getChildNode().item(0);
            raiz.appendChild(ntrack);
            
            Node ntrackURI = doc.createElement("TrackURI");
            Node ntrackURI_txt = doc.createTextNode(trackURI);
            ntrack.appendChild(ntrackURI);
            ntrackURI.appendChild(ntrackURI_txt);

            Node ntrackName = doc.createElement("TrackName");
            Node ntrackName_txt = doc.createTextNode(trackName);
            ntrack.appendChild(ntrackName);
            ntrackName.appendChild(ntrackName_txt);
            
            Node nartistURIs = doc.createElement("ArtistURIs");
            Node nartistURIs_txt = doc.createTextNode(artistURIs);
            ntrack.appendChild(nartistURIs);
            nartistURIs.appendChild(nartistURIs_txt);
            
            Node nartistNames = doc.createElement("ArtistNames");
            Node nartistNames_txt = doc.createTextNode(artistNames);
            ntrack.appendChild(nartistNames);
            nartistNames.appendChild(nartistNames_txt);
            
            Node nalbumURI = doc.createElement("AlbumURI");
            Node nalbumURI_txt = doc.createTextNode(albumURI);
            ntrack.appendChild(nalbumURI);
            nalbumURI.appendChild(nalbumURI_txt);
            
            Node nalbumName = doc.createElement("AlbumName");
            Node nalbumName_txt = doc.createTextNode(albumName);
            ntrack.appendChild(nalbumName);
            nalbumName.appendChild(nalbumName_txt);
            
            Node nalbumArtistURIs = doc.createElement("AlbumArtistURIs");
            Node nalbumArtistURIs_txt = doc.createTextNode(albumArtistURIs);
            ntrack.appendChild(nalbumArtistURIs);
            nalbumArtistURIs.appendChild(nalbumArtistURIs_txt);
            
            Node nalbumArtistNames = doc.createElement("AlbumArtistNames");
            Node nalbumArtistNames_txt = doc.createTextNode(albumArtistNames);
            ntrack.appendChild(nalbumArtistNames);
            nalbumArtistNames.appendChild(nalbumArtistNames_txt);
            
            Node nalbumReleaseDate = doc.createElement("AlbumReleaseDate");
            Node nalbumReleaseDate_txt = doc.createTextNode(albumReleaseDate);
            ntrack.appendChild(nalbumReleaseDate);
            nalbumReleaseDate.appendChild(nalbumReleaseDate_txt);
            
            Node nalbumImageURL = doc.createElement("AlbumImageURL");
            Node nalbumImageURL_txt = doc.createTextNode(albumImageURL);
            ntrack.appendChild(nalbumImageURL);
            nalbumImageURL.appendChild(nalbumImageURL_txt);
            
            Node ndiscNumber = doc.createElement("DiscNumber");
            Node ndiscNumber_txt = doc.createTextNode(discNumber);
            ntrack.appendChild(ndiscNumber);
            ndiscNumber.appendChild(ndiscNumber_txt);
            
            Node ntrackNumber = doc.createElement("TrackNumber");
            Node ntrackNumber_txt = doc.createTextNode(trackNumber);
            ntrack.appendChild(ntrackNumber);
            ntrackNumber.appendChild(ntrackNumber_txt);
            
            Node ntrackDuration = doc.createElement("TrackDuration");
            Node ntrackDuration_txt = doc.createTextNode(trackDuration);
            ntrack.appendChild(ntrackDuration);
            ntrackDuration.appendChild(ntrackDuration_txt);
            
            Node ntrackPreviewURL = doc.createElement("TrackPreviewURL");
            Node ntrackPreviewURL_txt = doc.createTextNode(trackPreviewURL);
            ntrack.appendChild(ntrackPreviewURL);
            ntrackPreviewURL.appendChild(ntrackPreviewURL_txt);
            
            Node nexplicit = doc.createElement("Explicit");
            Node nexplicit_txt = doc.createTextNode(explicit);
            ntrack.appendChild(nexplicit);
            nexplicit.appendChild(nexplicit_txt);
            
            Node npopularity = doc.createElement("Popularity");
            Node npopularity_txt = doc.createTextNode(popularity);
            ntrack.appendChild(npopularity);
            npopularity.appendChild(npopularity_txt);
            
            Node naddedBy = doc.createElement("AddedBy");
            Node naddedBy_txt = doc.createTextNode(addedBy);
            ntrack.appendChild(naddedBy);
            naddedBy.appendChild(naddedBy_txt);
            
            Node naddedAt = doc.createElement("AddedAt");
            Node naddedAt_txt = doc.createTextNode(addedAt);
            ntrack.appendChild(naddedAt);
            naddedAt.appendChild(naddedAt_txt);
            
            return 0;
            
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int aniadirDOM(String trackURI, String trackName, String artistURIs, String artistNames, String albumURI, String albumName, String albumArtistURIs, String albumArtistNames, String albumReleaseDate, String albumImageURL, String discNumber, String trackNumber, String trackDuration, String trackPreviewURL, String explicit, String popularity, String addedBy, String addedAt){
        return aniadirDOM(doc, trackURI, trackName, artistURIs, artistNames, albumURI, albumName, albumArtistURIs, albumArtistNames, albumReleaseDate, albumImageURL, discNumber, trackNumber, trackDuration, trackPreviewURL, explicit,popularity,addedBy,addedAt);
    }
    
    public void writeXML() {
    	try {
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource domSource = new DOMSource(doc);
            
            StreamResult streamResult = new StreamResult(new File("src/data/festival2DAMNew.xml"));

            
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


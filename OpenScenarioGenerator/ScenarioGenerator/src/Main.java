import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import tools.*;

/**
 * @author AndreaTagliavini
 * This project is meant to produce several random scenarios in the OpenScenario format
 * {@link http://www.openscenario.org/}
 * This in the main module, which is used to automate the process of scenarios generation
 *
 */
public class Main {

	public static void main(String[] args) throws ParserConfigurationException {
		Lane exa = new Lane(1, "driving", false, false, new LaneWidth(1), new LaneHeight(2,2));
		Lane exa2 = new Lane(2, "driving", false, false, new LaneWidth(1), new LaneHeight(2,2));
		Geometry geo = new Geometry();
		Road exa3 = new Road(1, -1, geo, 0, 0, 0);
		System.out.println("Estraggo xml");
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
//		doc= exa.getXml(doc, rootElement);
//		doc= exa2.getXml(doc, rootElement);
		doc= exa3.getXml(doc, rootElement);
		DOMSource src = new DOMSource(doc);
		
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult(new File("C:/Users/Utente/Desktop/OpenScenarioGenerator/file.xml"));
			transformer.transform(src, result);
		} catch (TransformerConfigurationException e) {
			System.out.println("TransformerConfigurationException");
		} catch (TransformerException e) {
			System.out.println("Transformer");
		}
		
	}
	
	

}

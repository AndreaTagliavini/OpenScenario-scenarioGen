package tools;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;

/**
 * @author AndreaTagliavini
 * This class is used to define the geometry of a road
 * At the meantime only the "line" type is available
 */
public class Geometry {
	/**Absolute position of the geometry*/
	private Coordinate coords;
	/**Source*/
	private double s;
	/**Heading*/
	private double hdg;
	/**length*/
	private double length;
	/**type*/
	private String type;
	
	public Geometry(){
		this.coords= new Coordinate(0,0);
		this.s=0;
		this.hdg=0;
		this.length=0;
		this.type="line";
	}
	
	public Geometry(Coordinate coords, double s, double hdg, double length){
		this.coords=coords;
		this.s=s;
		this.hdg=hdg;
		this.length=length;
		this.type="line";
	}
	
	/***/
	public Document getXml(Document doc, Element father){
		Element geom;
		DocumentFragment fragment= doc.createDocumentFragment();
		geom= this.makeGeom(doc);
		fragment.appendChild(geom);
		father.appendChild(fragment);
		/**geometry "type" tag*/
		if(this.type.equals("line")){
			Element xmlLine = doc.createElement("line");
			geom.appendChild(xmlLine);
		}
		return doc;
	}
	
	/***/
	private Element makeGeom(Document doc){
		Element geomNode = doc.createElement("geometry");
		geomNode.setAttribute("s", String.valueOf(this.s));
		geomNode.setAttribute("x", String.valueOf(this.coords.getX()));
		geomNode.setAttribute("y", String.valueOf(this.coords.getY()));
		geomNode.setAttribute("hdg", String.valueOf(this.hdg));
		geomNode.setAttribute("length", String.valueOf(this.length));
		return geomNode;
	}
}

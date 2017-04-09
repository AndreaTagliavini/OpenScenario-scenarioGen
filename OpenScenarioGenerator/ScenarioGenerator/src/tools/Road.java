package tools;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;

/**
 * @author AndreaTagliavini
 * This class serves the purpose of representing a road in the layout of the OpenScenario simulation
 * At the meantime it is possible to build roads with a single geometry at a time
 * The idea is to use polymorphism to create different types of roads by extending this class
 * e.g. by extending this class you could create a "fullParkingRoad" and treat it as you would with a normal road
 */
public class Road {
	private String name;
	private double length;
	private int id;
	private int junction;
	private boolean link;
	private int linkPredecessor;
	private int linkSuccessor;
	private Geometry geom;
	private Lane[] leftLanes;
	private Lane[] centerLanes;
	private Lane[] rightLanes;
	protected Object[] objects;
	
	/**Empty constructor*/
	public Road(){
		this.name="";
		this.id=0;
		this.length=0;
		this.junction=-1;
		this.link=false;
	}
	
	/**"Objectless" constructors*/
	/**Linkless*/
	public Road(int id, int junction, Geometry geom, int lLanes, int cLanes, int rLanes){
		this.id=id;
		this.junction=junction;
		this.geom=geom;
		if(lLanes>0)
			this.leftLanes= new Lane[lLanes];
		else
			this.leftLanes= null;
		if(cLanes>0)
			this.centerLanes= new Lane[cLanes];
		else
			this.leftLanes= null;
		if(rLanes>0)
			this.rightLanes= new Lane[rLanes];
		else
			this.leftLanes= null;
		
	}
	/**Linked*/
	public Road(int id, int junction, Geometry geom, int linkPredecessor, int linkSuccessor, int lLanes, int cLanes, int rLanes){
		this.id=id;
		this.junction=junction;
		this.geom=geom;
		this.link=true;
		this.linkPredecessor=linkPredecessor;
		this.linkSuccessor=linkSuccessor;
		
		if(lLanes>0)
			this.leftLanes= new Lane[lLanes];
		else
			this.leftLanes= null;
		if(cLanes>0)
			this.centerLanes= new Lane[cLanes];
		else
			this.leftLanes= null;
		if(rLanes>0)
			this.rightLanes= new Lane[rLanes];
		else
			this.leftLanes= null;
	}
	
	/**This method appends to the XML Document the XML code for the defined road
	 * it uses the DocumentFragment interface, in order to not redefine another Document
	 * after generating the code for the road it appends the element to the defined father
	 * and finally returns the document
	 */
	public Document getXml(Document doc, Element father){
		Element road;
		DocumentFragment fragment= doc.createDocumentFragment();
		road= this.makeRoad(doc);
		fragment.appendChild(road);
		father.appendChild(fragment);
		return doc;
	}
	
	/**
	 * Method that creates the xml code for the road
	 * it creates the nodes "link TODO", "plainView", "geometry"(via Geometry.getXml()), "elevationProfile", "lateralProfile", "lanes", "laneSection", "left", "center", "right",
	 * "objects", "signal", "surface"
	 * it generates the xml code for each lane defined in the 3 arrays (left, centre and right)
	 * it generates the xml code for each object defined in the array objects, TODO
	 * it then creates the node "road" and then sets its attributes
	 * finally it appends child nodes to their father node, and then it returns it
	 */
	private Element makeRoad(Document doc){
		
		/**Link tag*/
		Element xmlLink = doc.createElement("link");
		if(this.link){
			System.out.println("Here link parsing");
		}
		
		/**plainView tag*/
		Element xmlPlainView = doc.createElement("plainView");
		
		/**geometry tag*/
		this.geom.getXml(doc, xmlPlainView);
		
		/**elevationProfile tag*/
		Element xmlEleProf= doc.createElement("elevationProfile");
		
		/**lateralProfile tag*/
		Element xmlLatProf= doc.createElement("lateralProfile");
		
		/**lanes tag*/
		Element xmlLanes= doc.createElement("lanes");
		/**laneSection tag*/
		Element xmlLaneSection= doc.createElement("laneSection");
		xmlLaneSection.setAttribute("s", "0"); //Default
		xmlLaneSection.appendChild(xmlLanes);
		
		/**left tag*/
		Element xmlLeft= doc.createElement("left");
		xmlLeft.appendChild(xmlLaneSection);
		if(this.leftLanes!= null)
			for(int i=0; i<this.leftLanes.length; i++)
				this.leftLanes[i].getXml(doc, xmlLeft);
		
		/**center tag*/
		Element xmlCenter= doc.createElement("center");
		xmlCenter.appendChild(xmlLaneSection);
		if(this.centerLanes!= null)
			for(int i=0; i<this.centerLanes.length; i++)
				this.centerLanes[i].getXml(doc, xmlCenter);
		
		/**right tag*/
		Element xmlRight= doc.createElement("right");
		xmlRight.appendChild(xmlLaneSection);
		if(this.rightLanes!= null)
			for(int i=0; i<this.rightLanes.length; i++)
				this.rightLanes[i].getXml(doc, xmlRight);
		
		/**objects tag*/
		Element xmlObjects= doc.createElement("objects");
		
		/**signals tag*/
		Element xmlSignals= doc.createElement("signals");
		
		/**surface tag*/
		Element xmlSurface= doc.createElement("surface");
		
		/**road tag*/
		Element roadNode = doc.createElement("road");
		roadNode.setAttribute("name", this.name);
		roadNode.setAttribute("length", String.valueOf(this.length));
		roadNode.setAttribute("id", String.valueOf(this.id));
		roadNode.setAttribute("junction", String.valueOf(this.junction));
		
		/**Append children*/
		roadNode.appendChild(xmlLink);
		roadNode.appendChild(xmlPlainView);
		roadNode.appendChild(xmlEleProf);
		roadNode.appendChild(xmlLatProf);
		roadNode.appendChild(xmlLanes);
		roadNode.appendChild(xmlObjects);
		roadNode.appendChild(xmlSignals);
		roadNode.appendChild(xmlSurface);
		
		return roadNode;
	}
}

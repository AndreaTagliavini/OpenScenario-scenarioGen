package tools;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;

/**
 * @author AndreaTagliavini
 * This class serves the purpose of representing a single lane in a road
 * a lane in OpenScenario could be a driving lane, a sidewalk, a parking, a cycling track etc...
 */
public class Lane {
	private int id;
	private String type;
	private boolean level;
	private boolean link;
	private int linkPredecessor;
	private int linkSuccessor;
	private LaneWidth width;
	private LaneHeight height;
	
	/**Empty constructor*/
	public Lane(){
		this.id=0;
		this.type="driving";
		this.level=false;
		this.link=false;
		this.width= new LaneWidth(0);
		this.height= new LaneHeight(0,0);
	}
	
	/**Linkless constructor*/
	public Lane(int id, String type, boolean level, boolean link, LaneWidth width, LaneHeight height){
		this.id=id;
		this.type=type;
		this.level=level;
		this.link=link;
		this.width=width;
		this.height=height;
	}
	
	/**Complete constructor*/
	public Lane(int id, String type, boolean level, boolean link, int linkPredecessor, int linkSuccessor, LaneWidth width, LaneHeight height){
		this.id=id;
		this.type=type;
		this.level=level;
		this.link=link;
		this.linkPredecessor=linkPredecessor;
		this.linkSuccessor=linkSuccessor;
		this.width=width;
		this.height=height;
	}
	
	/**This method appends to the XML Document the XML code for the defined lane
	 * it uses the DocumentFragment interface, in order to not redefine another Document
	 * after generating the code for the lane it appends the element to the defined father
	 * and finally returns the document
	 */
	public Document getXml(Document doc, Element father){
		Element lane;
		DocumentFragment fragment= doc.createDocumentFragment();
		lane= this.makeLane(doc);
		fragment.appendChild(lane);
		father.appendChild(fragment);
		return doc;
	}
	
	/**
	 * Method that creates the xml code for the lane
	 * it creates the nodes "link TODO", "width" and "height" with their attributes
	 * it then creates the node "lane" and then sets its attributes
	 * finally it appends child nodes to their father node, and then it returns it
	 */
	private Element makeLane(Document doc){
		
		/**Link tag*/
		Element xmlLink = (doc).createElement("link");
		if(this.link){
			System.out.println("Here link parsing");
		}
		
		/**Width tag*/
		Element xmlWidth = (doc).createElement("width");
		xmlWidth.setAttribute("sOffset", String.valueOf(this.width.getSOffset()));
		xmlWidth.setAttribute("a", String.valueOf(this.width.getA()));
		xmlWidth.setAttribute("b", String.valueOf(this.width.getB()));
		xmlWidth.setAttribute("c", String.valueOf(this.width.getC()));
		xmlWidth.setAttribute("d", String.valueOf(this.width.getD()));
		
		/**Height tag*/
		Element xmlHeight = (doc).createElement("height");
		xmlHeight.setAttribute("sOffset", String.valueOf(this.height.getSOffset()));
		xmlHeight.setAttribute("inner", String.valueOf(this.height.getInner()));
		xmlHeight.setAttribute("outer", String.valueOf(this.height.getOuter()));
		
		/**Lane tag*/
		Element laneNode = doc.createElement("lane");
		laneNode.setAttribute("id", String.valueOf(this.id));
		laneNode.setAttribute("type", type);
		laneNode.setAttribute("level", String.valueOf(this.level));
		
		/**Append children nodes to Lane tag*/
		laneNode.appendChild(xmlLink);
		laneNode.appendChild(xmlWidth);
		laneNode.appendChild(xmlHeight);
		return laneNode;
	}
	
	
}

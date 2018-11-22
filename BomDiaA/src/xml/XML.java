package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/*
 * XML class
 * This class is used to manipulate the XML file to be used as database for the entire project.
 * @author Duque
 * @version 1.00
 */
public class XML {
	/**
	 * Input file to be used as XML database
	 */
	private static File inputFile = new File("DB.xml");
	/**
	 * Content Element node where information is stored in the XML file
	 */
	private static Element content;
	/**
	 * Root node to be called as the base of the XML file
	 */
	private static Element root;
	/**
	 * Specific node to search the XML file
	 */
	private static Element node;
	/*
	 * Time stamp to be used as Node id in the XML file with the specific format
	 * yyyy-MM-dd HH:mm:ss
	 */
	private static String timeStamp;

	/**
	 * 
	 * Main method.
	 * 
	 * To instantiate XML class and manipulate the XML file.
	 * 
	 * @param args obligatory in the main method
	 * @throws Exception failed to start
	 * @since version 1.00
	 */
	public static void main(String[] args) throws Exception {
		start();
	}

	/**
	 * Method used to start
	 * 
	 * @throws Exception failed to start
	 */
	public static void start() throws Exception {
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dbF.newDocumentBuilder();
		Document doc = dB.parse(inputFile);
		doc.getDocumentElement().normalize();
		System.out.println("-File Loaded into DOM view-");
		addContent(doc, "me2@iscte-iul.pt", "eMail", "Delays");
		readFile(doc);
//			saveFile(doc);
	}

	/**
	 * 
	 * This method appends a new content node to the XML file.
	 * 
	 * @param doc         this interface represents the entire XML document.
	 *                    Conceptually, it is the root of the document tree, and
	 *                    provides the primary access to the document's data.
	 * @param from        the senders ID to serve as attribute in the XML file.
	 * @param type        content type to serve as attribute in the XML file.
	 * @param contentText the content of the object to be appended on the XML file,
	 *                    usually in text format.
	 */
	public static void addContent(Document doc, String from, String type, String contentText) {
		System.out.println("-Adding New Content Element-");
		root = doc.getDocumentElement();
		root.appendChild(getContent(doc, getTime(), from, type, contentText));
	}

	/**
	 * This method appends a new Element node to the XML file.
	 * 
	 * @param doc        this interface represents the entire XML document.
	 *                   Conceptually, it is the root of the document tree, and
	 *                   provides the primary access to the document's data.
	 * @param newElement this interface represents an element in an XML document.
	 *                   Elements may have attributes associated with them; since
	 *                   the Element interface inherits from Node, the generic Node
	 *                   interface attribute attributes may be used to retrieve the
	 *                   set of all attributes for an element. There are methods on
	 *                   the Element interface to retrieve either an Attr object by
	 *                   name or an attribute value by name. In XML, where an
	 *                   attribute value may contain entity references, an Attr
	 *                   object should be retrieved to examine the possibly fairly
	 *                   complex sub-tree representing the attribute value.
	 */
//	public static void appendElement(Document doc, Element newElement) {
//		Node n = doc.getDocumentElement();
//		n.appendChild(newElement);
//	}

	/**
	 * 
	 * This method reads the entire XML file in a human readable way.
	 * 
	 * @param doc this interface represents the entire XML document. Conceptually,
	 *            it is the root of the document tree, and provides the primary
	 *            access to the document's data.
	 * @see Node
	 * @see NodeList
	 */
	public static void readFile(Document doc) {
		DocumentTraversal tr = (DocumentTraversal) doc;
		NodeIterator it = tr.createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ALL, new ItemFilter(), true);
		NodeList csList = doc.getElementsByTagName("Content");
		for (int i = 0; i < csList.getLength(); i++) {
			Node cs = csList.item(i);
			if (cs.getNodeType() == Node.ELEMENT_NODE) {
				Element contents = (Element) cs;
				String date = contents.getAttribute("date");
				System.out.println("Content date: " + date);
				NodeList cList = contents.getChildNodes();
				for (int j = 0; j < cList.getLength(); j++) {
					Node content = cList.item(j);
					if (content.getNodeType() == Node.ELEMENT_NODE) {
						Element c = (Element) content;
						System.out.println(c.getTagName() + ": " + c.getTextContent());

					}
				}
			}
		}
	}

	/**
	 * 
	 * This method saves the DOM structure of the XML file in memory to the
	 * database.
	 * 
	 * @param doc this interface represents the entire XML document. Conceptually,
	 *            it is the root of the document tree, and provides the primary
	 *            access to the document's data.
	 * @throws Exception failed to save XML file
	 */
	public static void saveFile(Document doc) throws Exception {
		System.out.println("Saving XML document.");
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new FileOutputStream("DB.xml"));
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
	}

	/**
	 * 
	 * This method is used to get a specific Content element form the XML file.
	 * 
	 * @param doc         this interface represents the entire XML document.
	 *                    Conceptually, it is the root of the document tree, and
	 *                    provides the primary access to the document's data.
	 * @param time
	 * @param from        the senders ID to serve as attribute in the XML file.
	 * @param type        content type to serve as attribute in the XML file.
	 * @param contentText the content of the object to be appended on the XML file,
	 *                    usually in text format.
	 * @return the content of a specific Element.
	 * @see Node
	 */
	private static Node getContent(Document doc, String time, String from, String type, String contentText) {
		content = doc.createElement("Content");
		content.setAttribute("Content", time);
		content.appendChild(getContentElements(doc, content, "From", from));
		content.appendChild(getContentElements(doc, content, "Type", type));
		content.appendChild(getContentElements(doc, content, "Content", contentText));
		return content;
	}

	/**
	 * 
	 * @param doc     this interface represents the entire XML document.
	 *                Conceptually, it is the root of the document tree, and
	 *                provides the primary access to the document's data.
	 * @param element the relative element value.
	 * @param name    the name of the content Element.
	 * @param value   the content of the current Element.
	 * @return the matching Node.
	 * @see Element
	 * @see Node
	 */
	private static Node getContentElements(Document doc, Element element, String name, String value) {
		node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	/**
	 * This method is used to show only the nodes accepted by the designated filter.
	 * 
	 * @author Duque
	 *
	 */
	private static final class ItemFilter implements NodeFilter {
		public short acceptNode(Node n) {
			if (n instanceof Element) {
				if (((Element) n).getTagName().equals("Type")) {
					return NodeFilter.FILTER_ACCEPT;
				}
			}
			return NodeFilter.FILTER_REJECT;
		}
	}

	/**
	 * This method returns the actual time
	 * 
	 * @return returns the current time in a specific format "yyyy-MM-dd HH:mm:ss
	 * @see Calendar
	 * @see SimpleDateFormat
	 */
	public static String getTime() {
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	/**
	 * This method is used to test the XML database as input file
	 * 
	 * @return the input file
	 */
	static File getInputFile() {
		return inputFile;
	}

	/**
	 * This method is used to test the XML database file name
	 * 
	 * @return the input file name
	 */
	static String getInputFileName() {
		return inputFile.getName();
	}

	/**
	 * This method is used to test the getContent Element
	 * 
	 * @return content Element
	 */
	private static Element getContent() {
		return content;
	}

	/**
	 * This method is used to test the root node of the XML file
	 * 
	 * @return root node
	 */
	private static Element getRoot() {
		return root;
	}

	/**
	 * This method is used to test a specific node in the XML file
	 * 
	 * @return desired node
	 */
	private static Element getNode() {
		return node;
	}

	/**
	 * This method is used to test the time stamp
	 * 
	 * @return time stamp
	 */
	private static String getTimeStamp() {
		return timeStamp;
	}

}

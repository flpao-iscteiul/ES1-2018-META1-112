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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {
	static Document doc;

	public static void main(String[] args) {
		File inputFile = new File("src/DB.xml");
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dB = dbF.newDocumentBuilder();
			doc = dB.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("-File Loaded into DOM view-");
			addContent(doc, "me2@iscte-iul.pt", "eMail", "Delays");
			readFile(doc);
			saveFile(doc);
		} catch (Exception e) {
			System.out.println("-Loading File Failed-");
			e.printStackTrace();
		}

	}

	// Adds new content element to the xml document
	public static void addContent(Document doc, String from, String type, String contentText) {
		try {
			System.out.println("-Adding New Content Element-");
			Element root = doc.getDocumentElement();
			root.appendChild(getContent(doc, getTime(), from, type, contentText));
		} catch (Exception e) {
			System.out.println("Failure to add content");
			e.printStackTrace();
		}
	}

	// Appends a new element to the xml document
	public static void appendElement(Document doc, Element newElement) {
		Node n = doc.getDocumentElement();
		n.appendChild(newElement);
	}

	// Reads xml document in a user friendly way
	public static void readFile(Document doc) {
		try {
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
		} catch (Exception e) {
			System.out.println("Read File Failed");
			e.printStackTrace();
		}
	}

	// Save xml document
	public static void saveFile(Document doc) {
		try {
			System.out.println("Saving XML document.");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new FileOutputStream("DB.xml"));
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} catch (Exception e) {
			System.out.println("Saving Failed");
			e.printStackTrace();
		}
	}

	private static Node getContent(Document doc, String time, String from, String type, String contentText) {
		Element content = doc.createElement("Content");
		content.setAttribute("Content", time);
		content.appendChild(getContentElements(doc, content, "From", from));
		content.appendChild(getContentElements(doc, content, "Type", type));
		content.appendChild(getContentElements(doc, content, "Content", contentText));
		return content;
	}

	private static Node getContentElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	public static String getTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

}
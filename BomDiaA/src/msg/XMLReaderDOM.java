package msg;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLReaderDOM {

    public void readFile() {
        String filePath = "DB.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Message");
            //now XML is loaded as Document in memory, lets convert it to Object List
            List<Message> msgList = new ArrayList<Message>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                msgList.add(getMessage(nodeList.item(i)));
            }
            //lets print Employee list information
            for (Message msg : msgList) {
                System.out.println(msg.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

    }


    private static Message getMessage(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
    	Message msg = new Message();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            msg.setDate(getTagValue("Date", element));
            msg.setFrom(getTagValue("From", element));
            msg.setType(getTagValue("Type", element));
            msg.setText(getTagValue("Text", element));
        }

        return msg;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
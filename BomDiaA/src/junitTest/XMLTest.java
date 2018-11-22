package junitTest;

import static org.junit.Assert.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import xml.XML;

public class XMLTest {
	XML xml = new XML();
	File inputFile = new File("DB.xml");

	@Test
	public void testMain() throws Exception {
		xml.start();
		assertEquals(inputFile.getName(), XML.getInputFileName());
	}

	@Test
	public void testAddContent() throws Exception {
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB;
		Document doc;
		dB = dbF.newDocumentBuilder();
		doc = dB.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList list = doc.getElementsByTagName("Test");
		xml.addContent(doc, "Test", "Test", "Testcontent");
		NodeList list2 = doc.getElementsByTagName("Test");
		assertEquals(list.getLength(), list2.getLength());
	}

//	@Test
//	public void testAppendElement() {
//		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dB;
//		Document doc;
//		try {
//			dB = dbF.newDocumentBuilder();
//			doc = dB.parse(inputFile);
//			doc.getDocumentElement().normalize();
//			NodeList list = doc.getElementsByTagName("Test");
//			xml.addContent(doc, "Test", "Test", "Testcontent");
//			NodeList list2 = doc.getElementsByTagName("Test");
//			assertEquals(list.getLength(), list2.getLength());
//		} catch (Exception e) {
//			System.out.println("doc Failure");
//			e.printStackTrace();
//		}
//	}

	@SuppressWarnings("static-access")
	@Test
	public void testReadFile() throws Exception {
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB;
		Document doc;
		dB = dbF.newDocumentBuilder();
		doc = dB.parse(inputFile);
		doc.getDocumentElement().normalize();
		xml.readFile(doc);
		assertEquals(inputFile.getName().isEmpty(), false);
	}

	@Test
	public void testSaveFile() throws Exception {
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB;
		Document doc;
		dB = dbF.newDocumentBuilder();
		doc = dB.parse(inputFile);
		doc.getDocumentElement().normalize();
		xml.saveFile(doc);
		assertEquals(inputFile.getName().isEmpty(), false);
	}

	@Test
	public void testGetTime() {
		String time = xml.getTime();
		assertEquals(time, xml.getTime());
	}

}

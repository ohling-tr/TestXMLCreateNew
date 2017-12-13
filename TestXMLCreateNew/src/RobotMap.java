import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class RobotMap {

	private String mIniFile;
	private Document mIniDoc;
	private XPath m_xPath;
	private Document mNewDoc;
	private Element mRootRobot;
	
	public RobotMap() {
		
		mIniFile = "D:\\robot.ini";

		try {
	         File inputFile = new File(mIniFile);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

  	         mIniDoc = dBuilder.parse(inputFile);
	         mIniDoc.getDocumentElement().normalize();
	         m_xPath =  XPathFactory.newInstance().newXPath();
      
	      } catch (ParserConfigurationException e) {
	          e.printStackTrace();
	      } catch (SAXException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } 
		
	}
	
	public void listXML() {
		
		System.out.println("Script Root: " + mIniDoc.getDocumentElement().getNodeName());
		
		NodeList speedList = mIniDoc.getDocumentElement().getElementsByTagName("speedController");
	    for (int temp = 0; temp < speedList.getLength(); temp++) {
	    	Node nNode = speedList.item(temp);
	        System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element eElement = (Element) nNode;
	        	System.out.println("PidController: " + eElement.getAttribute("speedController"));
	        	try {
	        		System.out.println("Port: " + eElement.getElementsByTagName("port").item(0).getTextContent());
	        	} catch (Exception e) { 
	        		System.out.println("ERROR NO PORT");
	        	}
	        	try {
	        		System.out.println("Model: " + eElement.getElementsByTagName("model").item(0).getTextContent());
	        	} catch (Exception e) { 
	        		System.out.println("Model: Missing/Default");
	        	}
	        }
	    }
	    
		NodeList scriptList = mIniDoc.getDocumentElement().getElementsByTagName("pid");
	    for (int temp = 0; temp < scriptList.getLength(); temp++) {
	    	Node nNode = scriptList.item(temp);
	        System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element eElement = (Element) nNode;
	        	System.out.println("PidController: " + eElement.getAttribute("pidcontroller"));
	        	try {
	        		System.out.println("P Value: " + eElement.getElementsByTagName("p").item(0).getTextContent());
	        	} catch (Exception e) { 
	        		System.out.println("P no value");
	        	}
	        	try {
	        		System.out.println("I Value: " + eElement.getElementsByTagName("i").item(0).getTextContent());
	        	} catch (Exception e) { 
	        		System.out.println("I no value");
	        	}
	        	try {
	        		System.out.println("D Value: " + eElement.getElementsByTagName("d").item(0).getTextContent());
	        	} catch (Exception e) { 		
	        		System.out.println("D no value");
	        	}
	        }
	    }
		
	}
	
	public void writeNewIniInit() {
		
		DocumentBuilderFactory newDbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder newDocBuilder = newDbFactory.newDocumentBuilder();
			mNewDoc = newDocBuilder.newDocument();
			mRootRobot = mNewDoc.createElement("robot");
			mNewDoc.appendChild(mRootRobot);			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeNewIniFinish() {
		
		TransformerFactory xformFactory = TransformerFactory.newInstance();
		try {
			Transformer xformer = xformFactory.newTransformer();
			xformer.setOutputProperty(OutputKeys.METHOD, "xml");
			xformer.setOutputProperty(OutputKeys.INDENT, "yes");
			xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(mNewDoc);
			StreamResult result = new StreamResult(new File(mIniFile + ".new"));
			xformer.transform(source, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeNewIniPID(String pidName, double Pval, double Ival) {
		Element pidController = mNewDoc.createElement("pid");
		mRootRobot.appendChild(pidController);
		Attr attr = mNewDoc.createAttribute("pidcontroller");
		attr.setValue(pidName);
		pidController.setAttributeNode(attr);
		
		Element PIDP = mNewDoc.createElement("p");
		PIDP.appendChild(mNewDoc.createTextNode(String.valueOf(Pval)));
		pidController.appendChild(PIDP);
		Element PIDI = mNewDoc.createElement("i");
		PIDI.appendChild(mNewDoc.createTextNode(String.valueOf(Ival)));
		pidController.appendChild(PIDI);
		
	}
	
}

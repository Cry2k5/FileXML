import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FolderTree {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();
    
       
	    
	public FolderTree() throws Exception {
		Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File f=new File(path);
		Element rootElement = doc.createElement(f.getName());
        doc.appendChild(rootElement);
	    
	    File[] children = f.listFiles();
        if (children != null) {
            for (File child : children) {
            	Element e = null ;
            	addElement(rootElement, e, child);
            }
        }
	    
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\ADMIN\\eclipse-workspace\\XML\\folderTree.xml"));
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, result);
        transformer.transform(source, consoleResult);
    }
	
	public void addElement(Element rootElement, Element e, File file) {
		e = doc.createElement(file.getName());
    	rootElement.appendChild(e);
	}


public static void main(String[] args) throws Exception {
	new FolderTree();
}
}

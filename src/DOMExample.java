import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class DOMExample {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
    public DOMExample() throws Exception{
    	
    	
        
        List<Student> listStudents = new ArrayList<Student>();
        Scanner sc = new Scanner(System.in);
       
        
        int n;
        
        System.out.println("Nhap so luong sv: ");
        n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++)
        { 	
        	System.out.println("Nhap thong tin sv thu " + (i+1) +":");
        	Student st = new Student();
        	
        	st.setName(sc.nextLine());
        	st.setTuoi(Integer.parseInt(sc.nextLine()));
        	st.setGpa(Double.parseDouble(sc.nextLine()));
        	
        	listStudents.add(st);
        }
        
        Element rootElement = doc.createElement("class");
        doc.appendChild(rootElement);
        
        Attr totalStudentAttr = doc.createAttribute("totalStudents");
        totalStudentAttr.setValue(""+n);
        rootElement.setAttributeNode(totalStudentAttr);
        
        for (int i = 0; i < listStudents.size(); i++) {
            Student st = listStudents.get(i);
            addElement(st, rootElement, i);
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\ADMIN\\eclipse-workspace\\XML\\Students.xml"));
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, result);
        transformer.transform(source, consoleResult);
        
       
    }
    
    public void addElement(Student st,Element roote, int i){
    	
    	Element e = doc.createElement("student");
        roote.appendChild(e);
        Attr attr = doc.createAttribute("No");
        attr.setValue(""+(i+1));
        e.setAttributeNode(attr);
       
        
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(st.getName()));
        e.appendChild(name);
        
        Element tuoi = doc.createElement("age");
        tuoi.appendChild(doc.createTextNode(""+st.getTuoi()));
        e.appendChild(tuoi);
        
        Element gpa = doc.createElement("gpa");
        gpa.appendChild(doc.createTextNode(""+st.getGpa()));
        e.appendChild(gpa);
    	
    }
    public static void main(String[] args) throws Exception {
		new DOMExample();
	}
}
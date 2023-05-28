package studentXMl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXMLParser {
	public static void main(String[] args) {

		Student student = new Student("V00001", "Vinod Vukkalam", "Msc", "Male", "A+", "12-a Jupiter");

		generateXMLUsingDOMParser(student);

		parseXMLDataUsingDOM();

		parseXMLDataUsingSAX();
	}

	private static void generateXMLUsingDOMParser(Student student) {
		try {
			DocumentBuilderFactory doc_factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder doc_builder = doc_factory.newDocumentBuilder();

			// Write root element
			Document doc = doc_builder.newDocument();
			Element root_element = doc.createElement("Student");
			doc.appendChild(root_element);

			// Write child elements and add them to the root element
			Element id = doc.createElement("ID");
			id.appendChild(doc.createTextNode(student.getId()));
			root_element.appendChild(id);

			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(student.getName()));
			root_element.appendChild(name);

			Element std = doc.createElement("Standard");
			std.appendChild(doc.createTextNode(student.getStd()));
			root_element.appendChild(std);

			Element gender = doc.createElement("Gender");
			gender.appendChild(doc.createTextNode(student.getGender()));
			root_element.appendChild(gender);

			Element grade = doc.createElement("grade");
			grade.appendChild(doc.createTextNode(String.valueOf(student.getgrade())));
			root_element.appendChild(grade);

			Element address = doc.createElement("Address");
			address.appendChild(doc.createTextNode(student.getAddress()));
			root_element.appendChild(address);

			// write the DOM object to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("student.xml");
			transformer.transform(source, result);

			System.out.println("XML file created successfully.");

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void parseXMLDataUsingDOM() {
		try {
			DocumentBuilderFactory doc_factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder doc_builder = doc_factory.newDocumentBuilder();

			// Parse the XML file
			Document doc = doc_builder.parse("student.xml");

			// Get the root element
			Element root_element = doc.getDocumentElement();

			// Get the child elements
			String id = root_element.getElementsByTagName("ID").item(0).getTextContent();
			String name = root_element.getElementsByTagName("Name").item(0).getTextContent();
			String std = root_element.getElementsByTagName("Standard").item(0).getTextContent();
			String gender = root_element.getElementsByTagName("Gender").item(0).getTextContent();
			String grade = root_element.getElementsByTagName("grade").item(0).getTextContent();
			String address = root_element.getElementsByTagName("Address").item(0).getTextContent();

			// Display the parsed XML data
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
			System.out.println("Standard: " + std);
			System.out.println("Gender: " + gender);
			System.out.println("grade: " + grade);
			System.out.println("Address: " + address);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



    private static void parseXMLDataUsingSAX() {
        try {
            // Create a SAX parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Create a custom SAX handler by extending DefaultHandler
            DefaultHandler handler = new DefaultHandler() {
                boolean isID = false;
                boolean isName = false;
                boolean isStandard = false;
                boolean isGender = false;
                boolean isgrade = false;
                boolean isAddress = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    // Start of an element, set the corresponding boolean flag
                    switch (qName) {
                        case "ID":
                            isID = true;
                            break;
                        case "Name":
                            isName = true;
                            break;
                        case "Standard":
                            isStandard = true;
                            break;
                        case "Gender":
                            isGender = true;
                            break;
                        case "grade":
                            isgrade = true;
                            break;
                        case "Address":
                            isAddress = true;
                            break;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // End of an element, reset the corresponding boolean flag
                    switch (qName) {
                        case "ID":
                            isID = false;
                            break;
                        case "Name":
                            isName = false;
                            break;
                        case "Standard":
                            isStandard = false;
                            break;
                        case "Gender":
                            isGender = false;
                            break;
                        case "grade":
                            isgrade = false;
                            break;
                        case "Address":
                            isAddress = false;
                            break;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    // Process the text content based on the corresponding element
                    if (isID) {
                        String id = new String(ch, start, length);
                        System.out.println("ID: " + id);
                    } else if (isName) {
                        String name = new String(ch, start, length);
                        System.out.println("Name: " + name);
                    } else if (isStandard) {
                        String std = new String(ch, start, length);
                        System.out.println("Standard: " + std);
                    } else if (isGender) {
                        String gender = new String(ch, start, length);
                        System.out.println("Gender: " + gender);
                    } else if (isgrade) {
                        String grade = new String(ch, start, length);
                        System.out.println("Grade: " + grade);
                    } else if (isAddress) {
                        String address = new String(ch, start, length);
                        System.out.println("Address: " + address);
                    }
                }
            };

            // Parse the XML file using the SAX parser and the custom handler
            saxParser.parse("student.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	}

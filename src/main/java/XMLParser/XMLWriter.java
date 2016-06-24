package XMLParser;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

/**
 * Created by Shonen on 24.06.2016.
 */
public class XMLWriter {

    public void write(String filename, List<Excercise> exc) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("excercises");
            doc.appendChild(rootElement);
            if(exc != null) {
                for(int i=0;i< exc.size();i++) {
                    // exercise name=....
                    Element exercise = doc.createElement("exercise");
                    rootElement.appendChild(exercise);
                    // now add the excercise name
                    Attr exc_name = doc.createAttribute("name");
                    exc_name.setValue(exc.get(i).getName());
                    exercise.setAttributeNode(exc_name);

                    // now add the description
                    Element desc = doc.createElement("description");
                    desc.appendChild(doc.createTextNode(exc.get(i).getDescription()));
                    exercise.appendChild(desc);
                    // <classes> .. </classes>
                    Element classes = doc.createElement("classes");
                    exercise.appendChild(classes);
                    for(int j=0;j<exc.get(i).getClassNames().size(); j++) {
                        // class name=....
                        Element class_node =doc.createElement("class");
                        classes.appendChild(class_node);
                        Attr attr_class_name = doc.createAttribute("name");
                        attr_class_name.setValue(exc.get(i).getClassNames().get(j));
                        class_node.setAttributeNode(attr_class_name);
                        // class content now
                        class_node.appendChild(doc.createTextNode(exc.get(i).getClassContent().get(j)));
                    }
                    Element tests = doc.createElement("tests");
                    exercise.appendChild(tests);
                    for(int k=0;k < exc.get(i).getTestClassNames().size(); k++) {
                        // class name=....
                        Element test_node =doc.createElement("test");
                        tests.appendChild(test_node);
                        Attr attr_class_name = doc.createAttribute("name");
                        attr_class_name.setValue(exc.get(i).getTestClassNames().get(k));
                        test_node.setAttributeNode(attr_class_name);
                        // class content now
                        test_node.appendChild(doc.createTextNode(exc.get(i).getTestClassContent().get(k)));
                    }

                    Element config = doc.createElement("config");
                    exercise.appendChild(config);
                    Element babysteps = doc.createElement("babysteps");
                    config.appendChild(babysteps);
                    Attr baby_value = doc.createAttribute("value");
                    baby_value.setValue(String.valueOf(exc.get(i).isBabysteps()));
                    babysteps.setAttributeNode(baby_value);

                    /* time not yet implemented */
                    Element timetracking = doc.createElement("timetracking");
                    config.appendChild(timetracking);
                    Attr trackingvalue = doc.createAttribute("value");
                    trackingvalue.setValue(String.valueOf(exc.get(i).isTimetracking()));
                    timetracking.setAttributeNode(trackingvalue);

                }
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));

            transformer.transform(source, result);


        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}

package XMLParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML Reader for Reading the xml
 *
 * @author Marc
 */

public class XMLReader extends DefaultHandler {

    private List<Excercise> excercises = new ArrayList<Excercise>();
    private Excercise exc = null;
    private ArrayList<String> classNameList;
    private ArrayList<String> classContentList;
    private ArrayList<String> testclassNameList;
    private ArrayList<String> testclassContentList;
    private StringBuffer accumulator = new StringBuffer();

    @Override
    // A start tag is encountered.
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        accumulator.setLength(0);
                if(qName.equals("exercise")) {
                    classNameList = new ArrayList<String>();
                    classContentList = new ArrayList<String>();
                    testclassNameList = new ArrayList<String>();
                    testclassContentList = new ArrayList<String>();
                    exc = new Excercise(classNameList,classContentList,testclassNameList,testclassContentList);
                    exc.setName(attributes.getValue("name"));
                }
                else if(qName.equals("class")) {
                    classNameList.add(attributes.getValue("name"));
                }
                else if(qName.equals("test")) {
                    testclassNameList.add(attributes.getValue("name"));
                }
                else if(qName.equals("acc")) {
                    exc.setAccTestName(attributes.getValue("name"));
                }
                else if(qName.equals("babysteps")) {
                    String isBabystep = attributes.getValue("value");
                    if(isBabystep.equals("true") || isBabystep.equals("True")) {
                        exc.setBabysteps(true);
                        String time = attributes.getValue("time");
                        String[] split = time.split(":");
                        int minutes = Integer.parseInt(split[0]);
                        int seconds = Integer.parseInt(split[1]);
                        long finaltime = TimeUnit.MINUTES.toSeconds(minutes) + (seconds);
                        exc.setBabystepstime(finaltime);
                    }
                }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("exercise")) {
            excercises.add(exc);
        }
        if(qName.equals("class")) {
            classContentList.add(accumulator.toString().trim());
        }
        if(qName.equals("test")) {
            testclassContentList.add(accumulator.toString().trim());
        }
        if(qName.equals("description")) {
            exc.setDescription(accumulator.toString().trim());
        }
        if(qName.equals("acc")) {
            exc.setAccTestCode(accumulator.toString().trim());
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        accumulator.append(ch, start, length);
    }


    public XMLReader(String filename)  {
        try {
            if (filename == "" || filename == null)
                throw new RuntimeException("The name of the XML file is required!");

            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();

            parser.parse(new File(filename), this);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Return a List of Excercises (each element is one <excercise> .... </excercise>
     */
    public List<Excercise> getExcercises() {
        return this.excercises;
    }

}
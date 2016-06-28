/**
 * Created by Shonen on 26.06.2016.
 */
import static org.junit.Assert.*;

import XMLParser.XMLReader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class TestsXMLReader {
    private XMLReader xmlr;

    @Before
    public void initialize() {
        xmlr = new XMLReader("src/main/java/XMLParser/TestFile.xml");
    }

    @Test
    public void shouldhaveTwoExc() {
        assertEquals(2,xmlr.getExcercises().size());
    }

    @Test
    public void shouldhaveExplicitClassName2() {
        assertEquals("RomanNumberConverter",xmlr.getExcercises().get(0).getClassNames().get(0));
        assertEquals("RomanNumberConverter2",xmlr.getExcercises().get(0).getClassNames().get(1));
    }

    @Test
    public void getsrightBabystepsAttributes() {
        assertEquals(true,xmlr.getExcercises().get(0).isBabysteps());
    }

    @Test
    public void getRightBabystepsTime() {
        long time = TimeUnit.MINUTES.toMillis(2) +  TimeUnit.SECONDS.toMillis(30);
        assertEquals(time,xmlr.getExcercises().get(1).getBabystepstime());
    }
}
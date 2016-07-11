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
        xmlr = new XMLReader("src/main/resources/Exercise.xml");
    }

    @Test
    public void shouldhaveFourExc() {
        assertEquals(4,xmlr.getExcercises().size());
    }

    @Test
    public void shouldhaveExplicitClassName2() {
        assertEquals("SMath",xmlr.getExcercises().get(0).getClassNames().get(0));
    }

    @Test
    public void getsrightBabystepsAttributes() {
        assertEquals(false,xmlr.getExcercises().get(1).isBabysteps());
    }

    @Test
    public void getRightBabystepsTime() {
        assertEquals(0,xmlr.getExcercises().get(1).getBabystepstime());
    }
}

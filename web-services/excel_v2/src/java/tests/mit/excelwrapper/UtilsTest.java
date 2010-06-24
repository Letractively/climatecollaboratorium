package mit.excelwrapper;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jun 23, 2010
 * Time: 9:33:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class UtilsTest {

    private static Logger log = Logger.getLogger(UtilsTest.class);

    @Test
    public void testParseString() {
        String input = "[123.4][456.7][678.9]";
        try {
            Utils.parseList("foo",input,DataType.INTEGER);
            Assert.fail("Parsing input string of float as integers should cause an error");
        } catch (RuntimeException e) {
            log.debug("Caught error "+e.toString());
        }

        List<String> result = Utils.parseList("foo",input,DataType.DOUBLE);
        Assert.assertEquals(result.size(),3);
        Assert.assertTrue(result.contains("123.4"));
        Assert.assertTrue(result.contains("456.7"));
        Assert.assertTrue(result.contains("678.9"));
    }
}

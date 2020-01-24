package moss.projectpairmachine;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

/** 
* MultiReaderGenerator Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 24, 2020</pre> 
* @version 1.0 
*/ 
public class MultiStreamReaderGeneratorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: generate()
    *
    */
    @Test
    public void testGenerate() throws Exception {
    //TODO: Test goes here...
            ByteArrayInputStream byteStream1 = new ByteArrayInputStream("A".getBytes());
            ByteArrayInputStream byteStream2 = new ByteArrayInputStream("B".getBytes());
            Collection<InputStream> streams = new ArrayList<>();
            streams.add(byteStream1);
            streams.add(byteStream2);
            MultiStreamReaderGenerator generator = new MultiStreamReaderGenerator(streams);
            Reader reader = generator.generate();
            assert(reader.read() == 'A');
            assert(reader.read() == 'B');

        }

}



package moss.project;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.File;
import java.io.Reader;
import java.nio.file.Paths;

/** 
* ProjectFlatReaderDistributor Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 24, 2020</pre> 
* @version 1.0 
*/ 
public class ProjectFlatReaderDistributorTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: distribute() 
* 
*/ 
@Test
public void testDistribute() throws Exception { 
//TODO: Test goes here...
    ProjectFlatReaderDistributor distributor = new ProjectFlatReaderDistributor(TestObjects.TEST_PROJECT_1_PATH,
            ProjectFlatReaderDistributor.TXT_FILTER);
    Reader reader = distributor.distribute();
    assert(reader.read() == 'c');
} 


} 

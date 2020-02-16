class ProjectFlatReaderDistributorTest
!!!166853.java!!!	before() : void
 
!!!166981.java!!!	after() : void
 
!!!167109.java!!!	testDistribute() : void
 
//TODO: Test goes here...
    ProjectFlatReaderDistributor distributor = new ProjectFlatReaderDistributor(TestObjects.TEST_PROJECT_1_PATH,
            PathFilter.TXT_FILTER);
    Reader reader = distributor.distribute();
    assert(reader.read() == 'c');

class ProjectsTest
!!!167493.java!!!	before() : void
 
!!!167621.java!!!	after() : void
 
!!!167749.java!!!	testCompare() : void
 
//TODO: Test goes here...
    ProjectBuilder projectBuilder = new ProjectBuilder();
    Project project1 = projectBuilder.setName("Test").setPath(TestObjects.TEST_PROJECT_1_PATH).createProject();
    Project project2 = projectBuilder.setName("Test_2").setPath(TestObjects.TEST_PROJECT_2_PATH).createProject();
    double comparison = Projects.compare(project1, project2, new TokenHashingStrategy());
    assert(comparison >= 0.0 && comparison <= 1.0);
    System.out.println("Test result:" + comparison);

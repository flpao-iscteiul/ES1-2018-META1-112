package junitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ EmailTest.class, TestConnect.class, TwitterAppTest.class, XMLTest.class })

public class TestSuite {
}
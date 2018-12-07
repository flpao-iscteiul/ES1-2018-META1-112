package JunitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ App_WindowTest.class, ApplicationTest.class, EmailTest.class, Intro_WindowTest.class,
		MainTest.class, MessageTest.class, SocialMessageTest.class, TestConnect.class, TwitterAppTest.class })

public class TestSuite {
}
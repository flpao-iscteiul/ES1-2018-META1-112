package facebook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TestConnect {

		@Test
		void test() throws FileNotFoundException {
			Connect test = new Connect();
			test.establishConnection();
			test.printPosts();
			assertEquals(test.getPostSize(), test.getCounter());
			test.publishMessageStatus("Welp");
			test.publishLinkStatus("I dont know what im doing", "https://www.youtube.com/watch?v=I8XXfgF9GSc");
			test.publishImageStatus("Tech", "D:\\download.jpg", "Apps");
			assertEquals(test.publicationCounter,test.getUserPublicationIDCounter());
			test.getPublicationCounter();
		}
	}

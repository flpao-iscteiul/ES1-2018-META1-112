package twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterMain  {
	public static void main(String[] args) {
		// http://twitter4j.org
		// http://twitter4j.org/en/code-examples.html
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("HBqCJtodfMrRKHImPrVG6xaRK")
        	  .setOAuthConsumerSecret("2IzcP92bXnsm6TsmMuoaZS74ELGfLV1Rm7A33zx76uLnSKukf2")
        	  .setOAuthAccessToken("1052910409722814465-2DeJZJcm7c4q46OMAaeNopUV0pKxSO")
        	  .setOAuthAccessTokenSecret("MjXeT8YleL2IHQB5v5tmLYAKGO0rrF8Dh0OfgbLTkjq0y");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();
            
            System.out.println("------------------------\n Showing home timeline \n------------------------");
    		
            
           //  twitter4j.Twitter tw = tf.getInstance();
           //  Status stat = tw.updateStatus("OLAAAA ISCTe");
            
            
            int counter=0;
    		int counterTotal = 0;
            for (Status status : statuses) {
            	
				// Filters only tweets from user "catarina"
				if (status.getUser().getName() != null && status.getUser().getName().contains("kaiser")) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					counter++;
				}
				counterTotal++;
            }
    		System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
     }
}
    

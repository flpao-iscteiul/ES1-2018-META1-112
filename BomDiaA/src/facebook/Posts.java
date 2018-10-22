package facebook;


import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Posts {
	public static void main(String[] args) {
		
		String accessToken2 = "EAAEbjUhu2dcBAMnCBP5ZAhYI96q7CZBpiSIT7Uitwfpu7fTsUmGOEad4Dm1tpSZBAlZBwrkEwpRBFsFgI9wteBSb1IQ5q1Q80wM4j6Rm5j76j8z1iWRSrDBRJ6OopcRGxP3brZBOB5yDU0oiZA5FVZCU4ZBYOitpn2kkd1E2nzz7eLNVYorjZAvYuHCpPxelxzbhTicBVKeQT5U9jY3ZA1U7ZAh";
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName()); 

		
		String accessToken4 = "EAAEbjUhu2dcBAMnCBP5ZAhYI96q7CZBpiSIT7Uitwfpu7fTsUmGOEad4Dm1tpSZBAlZBwrkEwpRBFsFgI9wteBSb1IQ5q1Q80wM4j6Rm5j76j8z1iWRSrDBRJ6OopcRGxP3brZBOB5yDU0oiZA5FVZCU4ZBYOitpn2kkd1E2nzz7eLNVYorjZAvYuHCpPxelxzbhTicBVKeQT5U9jY3ZA1U7ZAh";
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		
		String accessToken5 ;//= "EAAGZBEccjciEBAJ37ZAIbHKiL1Mo1HHex2pQTcs41dq8azfBvFGgt4eGgKBq12kSssOof51FKO0niKu7AaVKs3dy8W1ilqp4xcjFD1F9mmjJpVyeDnZAffUXRfh7zXL06BuSwQtfHMJbmJ079qCnkT844brHx966cz73JZBZBFy2Bv1rWu7T1rQddZCVpxywZCO6lDxoWDk2gZDZD";
		accessToken5 = "EAAEbjUhu2dcBAMnCBP5ZAhYI96q7CZBpiSIT7Uitwfpu7fTsUmGOEad4Dm1tpSZBAlZBwrkEwpRBFsFgI9wteBSb1IQ5q1Q80wM4j6Rm5j76j8z1iWRSrDBRJ6OopcRGxP3brZBOB5yDU0oiZA5FVZCU4ZBYOitpn2kkd1E2nzz7eLNVYorjZAvYuHCpPxelxzbhTicBVKeQT5U9jY3ZA1U7ZAh";	
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "ETI"
				if (aPost.getMessage() != null && aPost.getMessage().contains("ETI")) {
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5+"/"+counterTotal);		
	}
}

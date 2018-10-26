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
		
		String accessToken2 = "EAAEbjUhu2dcBABzhwMNVZCKV9ZBjrgwQLFhZAeehOIZCMgwaHewLWM0gjc6ZA6sbXS5Y6ZCj2CuHz2tjVj3Qy795nwJ9B3h7G9y6gYD6WTGIKrOqBr59cqs0vjNci52xflexOFlu6kXjqLK0LnrkxjcTUCopRt0LilXd5BGFlFMj6EXSfyvDJZC49TOjP0H74za4N49BHPQwri7Qn1gNoHe";
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName()); 

		
		String accessToken4 = "EAAEbjUhu2dcBABzhwMNVZCKV9ZBjrgwQLFhZAeehOIZCMgwaHewLWM0gjc6ZA6sbXS5Y6ZCj2CuHz2tjVj3Qy795nwJ9B3h7G9y6gYD6WTGIKrOqBr59cqs0vjNci52xflexOFlu6kXjqLK0LnrkxjcTUCopRt0LilXd5BGFlFMj6EXSfyvDJZC49TOjP0H74za4N49BHPQwri7Qn1gNoHe";
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		
		String accessToken5 ;//= "EAAGZBEccjciEBAJ37ZAIbHKiL1Mo1HHex2pQTcs41dq8azfBvFGgt4eGgKBq12kSssOof51FKO0niKu7AaVKs3dy8W1ilqp4xcjFD1F9mmjJpVyeDnZAffUXRfh7zXL06BuSwQtfHMJbmJ079qCnkT844brHx966cz73JZBZBFy2Bv1rWu7T1rQddZCVpxywZCO6lDxoWDk2gZDZD";
		accessToken5 = "EAAEbjUhu2dcBABzhwMNVZCKV9ZBjrgwQLFhZAeehOIZCMgwaHewLWM0gjc6ZA6sbXS5Y6ZCj2CuHz2tjVj3Qy795nwJ9B3h7G9y6gYD6WTGIKrOqBr59cqs0vjNci52xflexOFlu6kXjqLK0LnrkxjcTUCopRt0LilXd5BGFlFMj6EXSfyvDJZC49TOjP0H74za4N49BHPQwri7Qn1gNoHe";	
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

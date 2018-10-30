package facebook;


import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Connect {

	public void establishConnection() {
		getUserInfo();
		Connection<Post> result = getFacebookClient().fetchConnection("me/posts",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				System.out.println("---- Post "+ counter5 + " ----");
				System.out.println("Id: "+"fb.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter5++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5);		

	}

	public String getAccessToken() {
		String accessToken = "EAAIZBo3jKi8IBABg7BRistNsiTrKKc7P0P65nEh2m1uNAezEKgkvvxkQknsHbuZBGTj48ko7ZBZAsYrfz51QiwzzzeZAZB9lzTv41yedbWxU8F3tFOf1YTl129ZCl7Y5K3TWNDA12KQeuPlV76kRtbkzlqgk0cjZBnoyYNCtcDdWDWtHawSZAkk7NU5AOHOzsfxx1ft7LVQ1ZCFUp2rZAGDBKOt";
		return accessToken;
	}

	public FacebookClient getFacebookClient() {
		@SuppressWarnings("deprecation")
		FacebookClient fbClient5 = new DefaultFacebookClient(getAccessToken());
		return fbClient5;
	}

	public User getUserInfo() {
		User me2 = getFacebookClient().fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());
		return me2;
	}
}

package facebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Group;
import com.restfb.types.Post;
import com.restfb.types.User;

/**
 * 
 * This class connects the user with the Facebook servers.
 * <p>
 * Date: 19/11/2018
 * @author diogo
 * @version 1.2
 */
public class Connect {
	/**
	 * It's a counter used to count the number of posts in the list
	 */
	int counter;
	/**
	 * A counter used to count the number of publications made
	 */
	public int publicationCounter = 0;

	/**
	 * List<Group> to receive the list of groups the user belongs to
	 * 
	 */

	List<Group> aGroup;

	/**
	 * A list used to store posts
	 */
	List<Post> posts;

	/**
	 * List used to store the id's of the publications made by the user
	 */
	List<String> userPublicationId = new ArrayList<String>();

	/**
	 * Connection<Post> receives the list of posts from Facebook  
	 */
	Connection<Post>  result;

	/**
	 * Establish connection:
	 * <p>
	 * 	This method invokes the previously created method "getUserInfo" to establish a connection with the Facebook servers.
	 *   <p>
	 *  result, is a list of all the posts the user shared.
	 *  group, is a list of all the groups the user belongs to.
	 */

	public void establishConnection() {

		getUserInfo();
		Connection<Post> result = getFacebookClient().fetchConnection("me/posts",Post.class);
		Connection<Group> groupResult = getFacebookClient().fetchConnection("me/groups",Group.class);
		this.aGroup = groupResult.getData();
		this.result = result;
	}

	/**
	 * PrintPosts
	 * 
	 * This method goes through the list of posts created by the method "establishConnection" and prints said posts.
	 * <p>
	 * Creates a list of strings containing all the information regarding the posts
	 * from the list "result" and then selects a post to print.
	 */
	public void printPosts() {
		System.out.println("\nPosts:");
		int counter5 = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				this.posts = page; 
				System.out.println("---- Post "+ counter5 + " ----");
				System.out.println("Id: "+"fb.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter5++;
				this.counter = counter5;
			}
		}

		System.out.println("-------------\nNº of Results: " + counter5);	
	}

	/**
	 * 
	 * getAccessToken
	 * <p>
	 * Receives the Access Token necessary to connect to the user's Facebook profile.
	 * 
	 * @return accessToken
	 */
	public String getAccessToken() {
		String accessToken = "EAAIZBo3jKi8IBALcPoIyHwLQ8ICjh4ivHuBsa4vQAWZBDX3uJKy1FGuEEs9eTNZCY0VmlBnZAGQSKhthai0MIzWdanaCZC9sdUiChT1btdmdobPF4blC1e9QjKL5aZCzZCJzELnp8KK4FliR5nOn2F8tNQ7t2gQ0APjufuz9LCjMp8wh4UZCiUKuer2lp5BPOKWp90TDQiQ48iqag1U50uQL";
		return accessToken;
	}
	/**
	 * 
	 * getFacebookCLient
	 * 
	 * Receives the Access Token to obtain the Facebook profile of the user.
	 * 
	 * @return fbClient5
	 */
	public FacebookClient getFacebookClient() {
		@SuppressWarnings("deprecation")
		FacebookClient fbClient5 = new DefaultFacebookClient(getAccessToken());
		return fbClient5;
	}
	/**
	 * getUserInfo
	 * <p>
	 * Uses the method getFacebookClient to fetch the online profile of a specific Facebook user
	 * 
	 * @return The User Profile.
	 */
	public User getUserInfo() {
		User me2 = getFacebookClient().fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());
		return me2;
	}

	/**
	 * publishMessageStatus
	 * <p>
	 * Uses the publish method present in the RestFB API to post a message in the user's group.
	 * 
	 * @param message , receives the message the user wants to publish
	 */
	public void publishMessageStatus(String message) {
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/feed", FacebookType.class, Parameter.with("message", message));
		System.out.println("fb.com/" + response.getId());
		userPublicationId.add(response.getId());
		publicationCounter++;
	}
	/**
	 * publishLinkStatus
	 * <p>
	 * Uses the publish method present in the RestFB API to post a link with an associated message in the user's group. 
	 * 
	 * @param message the message associated with the link
	 * @param link the link the user wishes to share
	 */
	public void publishLinkStatus(String message, String link) {
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/feed", FacebookType.class, Parameter.with("message", message)
				,Parameter.with("link", link));
		System.out.println("fb.com/" + response.getId());
		userPublicationId.add(response.getId());
		publicationCounter++;
	}
	/**
	 * publishImageStatus
	 * <p>
	 * Uses the publish method present in the RestFB API to post an image followed by a message.
	 * 
	 * @param name the of the image
	 * @param directory the directory where the image is stored
	 * @param message the message associated with the image
	 * @throws FileNotFoundException 
	 */
	public void publishImageStatus(String name, String directory, String message) throws FileNotFoundException {
		FileInputStream f = null;
			f = new FileInputStream(new File(directory));
		@SuppressWarnings("deprecation")
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/photos", FacebookType.class, BinaryAttachment.with(name, f),
				Parameter.with("message", message));
		System.out.println("fb.com/" + response.getId());
		userPublicationId.add(response.getId());
		publicationCounter++;
	}
	/**
	 * A function used to obtain the number of posts in a list
	 * @return counter
	 */
	public int getCounter() {
		return this.counter;
	}

	/**
	 * A function used to obtain the size of the list composed of posts.
	 * @return the size of the list
	 */
	public int getPostSize() {
		return posts.size();

	}
	/**
	 * A function used to obtain the size of the list composed of the ID's of the posts published by the user.
	 * @return the size of the list
	 */
	public int getUserPublicationIDCounter() {
		return userPublicationId.size();
	}
	/**
	 * A function used to obtain the number of publications made by the user.
	 * @return the number of posts published
	 */
	public int getPublicationCounter() {
		System.out.println(publicationCounter);
		return publicationCounter;
	}
}

package BDA.facebook;

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
 * This class connects the user with the Facebook servers for post manipulation
 * 
 * Date: 07/12/2018
 * 
 * @author Diogo
 * @version Release
 */
public class Connect {

	/**
	 * List of Posters stored as strings
	 */
	private List<String> from = new ArrayList<String>();
	/**
	 * List of post's dates stored as strings
	 */
	private List<String> date = new ArrayList<String>();
	/**
	 * List of posts contents stored as strings
	 */
	private List<String> message = new ArrayList<String>();
	/**
	 * Used to count the number of posts in the list
	 */
	private int counter;
	/**
	 * Used to count the number of publications made
	 */
	private int publicationCounter = 0;
	/**
	 * List of groups the user belongs to
	 */
	private List<Group> aGroup;
	/**
	 * List used to store posts
	 */
	private List<Post> posts;
	/**
	 * List used to store the id's of the publications made by the user
	 */
	private List<String> userPublicationId = new ArrayList<String>();
	/**
	 * Connection<Post> receives the list of posts from Facebook
	 */
	private Connection<Post> result;

	/**
	 * This method invokes the previously created method "getUserInfo" to establish
	 * a connection with the Facebook servers
	 * 
	 * @see result
	 */
	public void establishConnection() {
		getUserInfo();
		Connection<Post> result = getFacebookClient().fetchConnection("me/posts", Post.class);
		Connection<Group> groupResult = getFacebookClient().fetchConnection("me/groups", Group.class);
		this.aGroup = groupResult.getData();
		this.result = result;
	}

	/**
	 * This method goes through the list of posts created by the method
	 * "establishConnection" and prints said posts. Creates a list of strings
	 * containing all the information regarding the posts from the list "result" and
	 * then selects a post to print
	 */
	public void printPosts() {
		int counter5 = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				this.posts = page;
				counter5++;
				from.add(aPost.getId());
				date.add(aPost.getCreatedTime().toString());
				if (aPost.getMessage() != null) {
					message.add(aPost.getMessage());
				} else {
					String aux = "null";
					message.add(aux);
				}
				this.counter = counter5;
			}
		}
	}

	/**
	 * Receives the Access Token necessary to connect to the user's Facebook profile
	 * 
	 * @return accessToken
	 */
	public String getAccessToken() {
		String accessToken = "EAAIZBo3jKi8IBAMTVgSrli9cADWO8zZBPXQnoCd68kypC1tI5UZCQT2MpGQbmRTv6NqGUHeM6DxrEIIRoVnejffoJPtSPYYqlrGx4djEb9AFZAZApPwCwhZAkZCqexZCAhNdlZA08S7srfpb4T3wCJIFwzB1lsubGclQhA0NKbaWDpjZCekZBYy45NjT0WPvZBSdBgc4AuefF4KBWQZDZD\r\n" + 
				"Fim da conversa de chat\r\n" + 
				"Escreve uma mensagem...\r\n" + 
				"\r\n" + 
				"";
		return accessToken;
	}

	/**
	 * Receives the Access Token to obtain the Facebook's user profile
	 * 
	 * @return fbClient5
	 */
	public FacebookClient getFacebookClient() {
		@SuppressWarnings("deprecation")
		FacebookClient fbClient5 = new DefaultFacebookClient(getAccessToken());
		return fbClient5;
	}

	/**
	 * Uses the publish method present in the RestFB API to post a message in the
	 * user's group
	 * 
	 * @param message receives the message the user wants to publish
	 */
	public void publishMessageStatus(String message) {
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/feed", FacebookType.class,
				Parameter.with("message", message));
		userPublicationId.add(response.getId());
		publicationCounter++;
	}

	/**
	 * Uses the publish method present in the RestFB API to post a link with an
	 * associated message in the user's group
	 * 
	 * @param message the message associated with the link
	 * @param link    the link the user wishes to share
	 */
	public void publishLinkStatus(String message, String link) {
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/feed", FacebookType.class,
				Parameter.with("message", message), Parameter.with("link", link));
		userPublicationId.add(response.getId());
		publicationCounter++;
	}

	/**
	 * Uses the publish method present in the RestFB API to post an image followed
	 * by a message
	 * 
	 * @param name      the of the image
	 * @param directory the directory where the image is stored
	 * @param message   the message associated with the image
	 * @throws FileNotFoundException when file is not found
	 */
	public void publishImageStatus(String name, String directory, String message) throws FileNotFoundException {
		FileInputStream f = null;
		f = new FileInputStream(new File(directory));
		@SuppressWarnings("deprecation")
		FacebookType response = getFacebookClient().publish(aGroup.get(0).getId() + "/photos", FacebookType.class,
				BinaryAttachment.with(name, f), Parameter.with("message", message));
		userPublicationId.add(response.getId());
		publicationCounter++;
	}

	/**
	 * 
	 * Uses the method getFacebookClient to fetch the online profile of a specific
	 * Facebook user
	 * 
	 * @return The User Profile.
	 */
	public User getUserInfo() {
		User me2 = getFacebookClient().fetchObject("me", User.class);
		return me2;
	}

	/**
	 * Used to obtain the number of posts in a list
	 * 
	 * @return counter
	 */
	public int getCounter() {
		return this.counter;
	}

	/**
	 * Used to obtain the size of the list composed of posts.
	 * 
	 * @return the size of the list
	 */
	public int getPostSize() {
		return posts.size();
	}

	/**
	 * Used to obtain the size of the list composed of the ID's of the posts
	 * published by the user.
	 * 
	 * @return the size of the list
	 */
	public int getUserPublicationIDCounter() {
		return userPublicationId.size();
	}

	/**
	 * Used to obtain the number of publications made by the user.
	 * 
	 * @return the number of posts published
	 */
	public int getPublicationCounter() {
		return publicationCounter;
	}

	/**
	 * Used to obtain the list of post creators
	 * 
	 * @return the list of post creators
	 * @see List
	 */
	public List<String> getFrom() {
		return from;
	}

	/**
	 * Used to obtain the list of post dates
	 * 
	 * @return the list of post dates
	 * @see List
	 */
	public List<String> getDate() {
		return date;
	}

	/**
	 * Used to obtain the list of post text content
	 * 
	 * @return the list of post text
	 * @see List
	 */
	public List<String> getMessage() {
		return message;
	}
}

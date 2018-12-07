package BDA.twitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import BDA.msg.SocialMessage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter class
 * This class is used to manipulate, send and receive tweets from the Twitter feed
 * @author Kaiser
 * @version Release
 */
public final class TwitterApp {

	/**
	 * Used to store Tweet statuses
	 */
	private List<Status> statuses;
	/**
	 * Used to store Tweets
	 * @see SocialMessage
	 */
	private List<SocialMessage> twitterList;
	/**
	 * Output String
	 */
	private String output;

	/**
	 * This method uses the user Access Tokens to send Tweets to his/her's tweeter feed
	 * 
	 * @param msg sent tweet
	 */

	public void sendTwitter(String msg) {

		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("HBqCJtodfMrRKHImPrVG6xaRK")
					.setOAuthConsumerSecret("2IzcP92bXnsm6TsmMuoaZS74ELGfLV1Rm7A33zx76uLnSKukf2")
					.setOAuthAccessToken("1052910409722814465-2DeJZJcm7c4q46OMAaeNopUV0pKxSO")
					.setOAuthAccessTokenSecret("MjXeT8YleL2IHQB5v5tmLYAKGO0rrF8Dh0OfgbLTkjq0y");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			statuses = twitter.getHomeTimeline();

			twitter4j.Twitter tw = tf.getInstance();
			Status stat = tw.updateStatus(msg);

		} catch (Exception e) {
		}
	}

	/**
	 * This method fetches all tweets from the timeline
	 */
	public void receiveTwitter() {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("HBqCJtodfMrRKHImPrVG6xaRK")
					.setOAuthConsumerSecret("2IzcP92bXnsm6TsmMuoaZS74ELGfLV1Rm7A33zx76uLnSKukf2")
					.setOAuthAccessToken("1052910409722814465-2DeJZJcm7c4q46OMAaeNopUV0pKxSO")
					.setOAuthAccessTokenSecret("MjXeT8YleL2IHQB5v5tmLYAKGO0rrF8Dh0OfgbLTkjq0y");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			statuses = twitter.getHomeTimeline();

			output = "";
			twitterList = new ArrayList<>();
			for (Status status : statuses) {

				if (status.getUser().getName() != null && status.getUser().getName().contains("kaiser")) {

					output = status.getUser().getName() + ":" + status.getText();
					SocialMessage tw = new SocialMessage(
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),
							status.getUser().getName(), status.getText());
					twitterList.add(tw);
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Get output method
	 * @return output
	 */
	public String getOutput() {
		return output;
	}
	
	/**
	 * This method is used to fetch all the tweets for the xml database file
	 * @return SocialMessage List
	 * @see SocialMessage
	 * @see List
	 */
	public List<SocialMessage> getTwitterList() {
		return twitterList;
	}
}

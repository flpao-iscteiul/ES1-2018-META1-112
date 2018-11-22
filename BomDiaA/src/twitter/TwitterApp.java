package twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/** Atravez desta aplicacao é possivel enviar e listar todos os tweets
 * Date: 19/11/2018
 * @author Kaiser
 * version 1.0
 * 
 */
public final class TwitterApp  {

	/**
	 * statuses Criacao da lista statuses para guardar os tweets
	 */
	List<Status> statuses ;
	String output;
	
	
	
/**
 * Este metodo recebe os tokens do usuario e envia os tweets
 * @param msg retorna o tweet enviado
 * @see envia o tweet
 * @see try recebe os tokens de um certo usuario e envia os tweets
 * @see catch envia uma excepcao.
 */

	public void sendTwitter(String msg) {
		
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("HBqCJtodfMrRKHImPrVG6xaRK")
			.setOAuthConsumerSecret("2IzcP92bXnsm6TsmMuoaZS74ELGfLV1Rm7A33zx76uLnSKukf2")
			.setOAuthAccessToken("1052910409722814465-2DeJZJcm7c4q46OMAaeNopUV0pKxSO")
			.setOAuthAccessTokenSecret("MjXeT8YleL2IHQB5v5tmLYAKGO0rrF8Dh0OfgbLTkjq0y");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();        		
			statuses = twitter.getHomeTimeline();

		    twitter4j.Twitter tw = tf.getInstance();
			Status stat = tw.updateStatus(msg);
			System.out.println("kaiser: " + msg);

			

		} catch (Exception e) { System.out.println(e.getMessage()); }
	}

	/**
	 * Metodo para receber os tweets da timeline
	 * @see try recebe os tokens de um certo usuario e recebe os tweets
     * @see catch envia uma excepcao.
	 * @see int counter para contador dentro do if
	 * @see int counterTotal para o contador total do for
	 * @see Este if serve para filtar somente os tweets do usuario "kaiser"
	 * @see  Imprimir somente os tweets do usuario "kaiser"
	 * 
	 * 
	 */
	
	public void receiveTwitter() {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("HBqCJtodfMrRKHImPrVG6xaRK")
			.setOAuthConsumerSecret("2IzcP92bXnsm6TsmMuoaZS74ELGfLV1Rm7A33zx76uLnSKukf2")
			.setOAuthAccessToken("1052910409722814465-2DeJZJcm7c4q46OMAaeNopUV0pKxSO")
			.setOAuthAccessTokenSecret("MjXeT8YleL2IHQB5v5tmLYAKGO0rrF8Dh0OfgbLTkjq0y");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();        		
			statuses = twitter.getHomeTimeline();
			    

			
			output = "";
			for (Status status : statuses) {

				
				if (status.getUser().getName() != null && status.getUser().getName().contains("kaiser")) {
					
					output=status.getUser().getName() + ":" + status.getText();
					System.out.println(status.getUser().getName() + ":" + status.getText());
				}
			}
			
		
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
	}
	   
	public String getOutput() {
		return output;
	}

	
}


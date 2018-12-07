package BDA.msg;

/**
 * SocialMessage class This class serves as a basic object model to later insert
 * in the xml database file
 * 
 * @author Duque
 * @version Release
 */
public class SocialMessage {

	/**
	 * Message date
	 */
	private String date;
	/**
	 * Message from address
	 */
	private String from;
	/**
	 * Message text content
	 */
	private String content;

	/**
	 * SocialMessage constructor
	 * 
	 * @param date    message date
	 * @param from    message address
	 * @param content message text content
	 */
	public SocialMessage(String date, String from, String content) {
		this.date = date;
		this.from = from;
		this.content = content;

	}

	/**
	 * Message getDate method
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Message getFrom method
	 * 
	 * @return from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Message getContent method
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Message setDate method
	 * 
	 * @param date message date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Message setFrom method
	 * 
	 * @param from message from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Message setContent method
	 * 
	 * @param content message content
	 */
	public void setContent(String content) {
		this.content = content;
	}
}

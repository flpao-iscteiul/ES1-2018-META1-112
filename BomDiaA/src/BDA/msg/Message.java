package BDA.msg;

/**
 * Message class This class serves as a basic object model to later insert in
 * the xml database file
 * 
 * @author Duque
 * @version Release
 */
public class Message {

	/**
	 * Message date
	 */
	private String date;
	/**
	 * Message from address
	 */
	private String from;
	/**
	 * Message type
	 */
	private String type;
	/**
	 * Message text content
	 */
	private String text;

	
	/**
	 * Message toString method
	 */
	@Override
	public String toString() {
		return "Date: " + date + "\n " + "From: " + from + "\n " + "Type: " + type + "\n " + "Text: " + text + "\n";
	}

	/**
	 * Message getDate method
	 * 
	 * @return date message date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Message getFrom method
	 * 
	 * @return from message from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Message getType method
	 * 
	 * @return type message type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Message getText method
	 * 
	 * @return text message text
	 */
	public String getText() {
		return text;
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
	 * @param from message address
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Message setType method
	 * 
	 * @param type message type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Message setText method
	 * 
	 * @param text message text
	 */
	public void setText(String text) {
		this.text = text;
	}
}

package msg;

public class Message {

	private String date;
	private String from;
	private String type;
	private String text;

	@Override
	public String toString() {
		return "Date: " + date + "\n " + "From: " + from + "\n " + "Type: " + type + "\n " + "Text: " + text + "\n";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

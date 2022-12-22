package emlakcepte.model;

public class Message {

	private String title;
	private String content;
	private User from;
	private User to;
	private Integer id;

	public Message(String title, String content, User from, User to) {
		super();
		this.title = title;
		this.content = content;
		this.from = from;
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	
	public Message() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

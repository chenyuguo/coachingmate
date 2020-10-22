package coachingmateanalytics.coachingmate.entity;


public class RequestToken {
	private long id;
	private long userId;
	private String username;
	private String token;
	private String secret;

	public RequestToken() {
		super();
	}

	public RequestToken(String token, String secret) {
		super();
	}

	public RequestToken(String username, String token, String secret) {
		super();
		this.username = username;
		this.token = token;
		this.secret = secret;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
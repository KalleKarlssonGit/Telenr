package hello.com.domain;


public class Greeting {

	private Account account;
	private Type type;
	private Long id;
	private String message;

	public Greeting(Account account, Type type, Long id, String message) {
		super();
		this.account = account;
		this.type = type;
		this.id = id;
		this.message = message;
	}

	public Greeting(Account account, Type type, Long id) {
		super();
		this.account = account;
		this.type = type;
		this.id = id;
	}

	public Greeting() {
		super();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
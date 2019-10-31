package hello.com.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import hello.com.domain.Account;
import hello.com.domain.Greeting;
import hello.com.domain.Type;
import hello.com.exception.GreetingException;
import hello.com.exception.ValidationException;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String getGreetingResponse(String accountStr, String typeStr, Long id) throws GreetingException, ValidationException {
		Greeting greeting = getGreeting(accountStr, typeStr, id);

		return greeting.getMessage();
	}

	@Override
	public Greeting getGreeting(String accountStr, String typeStr, Long id) throws GreetingException, ValidationException {

		validateParams(accountStr, typeStr, id);

		Account account = Account.valueOf(accountStr.toUpperCase());

		Type type = null;
		if (StringUtils.isNotBlank(typeStr)) {
			type = Type.valueOf(typeStr.toUpperCase());
		}

		Greeting greeting = new Greeting(account, type, id);
		String message = getMessage(greeting);
		greeting.setMessage(message);

		return greeting;
	}

	private String getMessage(Greeting greeting) throws GreetingException {

		Account account = greeting.getAccount();
		Type type = greeting.getType();
		Long id = greeting.getId();

		if (Account.PERSONAL.equals(account) && id != null) {

			return "Hi, userId " + id;

		} else if (Account.BUSINESS.equals(account)) {

			if (Type.SMALL.equals(type)) {
				throw new GreetingException("Path not implemented.");
			}

			if (Type.BIG.equals(type)) {
				return "Welcome, business user!";
			}

		}

		throw new GreetingException("Can not create message from input parameters.");
	}

	private void validateParams(String accountStr, String typeStr, Long id) throws ValidationException {

		try {
			Account.valueOf(accountStr.toUpperCase());
		} catch (Exception e) {
			throw new ValidationException("Validation error: Account parameter invalid.");
		}

		if (StringUtils.isBlank(typeStr) && id == null) {
			throw new ValidationException("Validation error: type and id null.");
		}

		if (id != null) {
			if (id.longValue() < 1) {
				throw new ValidationException("Validation error: Id parameter must be positive.");
			}
		}

		if (StringUtils.isNotBlank(typeStr)) {
			try {
				Type.valueOf(typeStr.toUpperCase());
			} catch (Exception e) {
				throw new ValidationException("Validation error: Type parameter invalid.");
			}
		}

	}

}

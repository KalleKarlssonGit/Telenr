package hello.com.service;

import hello.com.domain.Greeting;
import hello.com.exception.GreetingException;
import hello.com.exception.ValidationException;

public interface GreetingService {

	public String getGreetingResponse(String accountStr, String typeStr, Long id) throws GreetingException, ValidationException ;

	public Greeting getGreeting(String accountStr, String typeStr, Long id) throws GreetingException, ValidationException ;
}

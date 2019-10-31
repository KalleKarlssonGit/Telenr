package hello.com.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.com.exception.GreetingException;
import hello.com.exception.ValidationException;
import hello.com.service.GreetingService;

@RestController
public class GreetingController {

	@Autowired
	GreetingService greetingService;

    @RequestMapping("/greeting")
    public String getGreeting(
    		@RequestParam(value="account") String accountStr,
    		@RequestParam(value="type", required = false) String typeStr,
    		@RequestParam (value="id", required = false) @Min(1L) Long id) throws GreetingException, ValidationException {

    	return greetingService.getGreetingResponse(accountStr, typeStr, id);
    }

}

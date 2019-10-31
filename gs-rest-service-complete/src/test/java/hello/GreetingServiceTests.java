package hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hello.com.Application;
import hello.com.domain.Account;
import hello.com.domain.Greeting;
import hello.com.domain.Type;
import hello.com.exception.GreetingException;
import hello.com.exception.ValidationException;
import hello.com.service.GreetingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GreetingServiceTests {

	@Autowired
	GreetingService greetingService;


	/* Test valid greetings start */

    @Test
    public void testValidGreeting1() throws GreetingException, ValidationException {
    	Greeting greeting = greetingService.getGreeting("personal", null, Long.valueOf(123L));
    	assertEquals(greeting.getAccount(), Account.PERSONAL);
    	assertEquals(greeting.getType(), null);
    	assertEquals(greeting.getId(), Long.valueOf(123L));
    	assertEquals(greeting.getMessage(), ("Hi, userId " + Long.valueOf(123L)));
    }

    @Test
    public void testValidGreeting2() throws GreetingException, ValidationException {
    	Greeting greeting = greetingService.getGreeting("business", "big", null);
    	assertEquals(greeting.getAccount(), Account.BUSINESS);
    	assertEquals(greeting.getType(), Type.BIG);
    	assertNull(greeting.getId());
    	assertEquals(greeting.getMessage(), ("Welcome, business user!"));
    }

    /* Test valid greetings end */


    /* Test invalid input start */

    @Test(expected = ValidationException.class)
    public void testInvalidAccount1() throws GreetingException, ValidationException {
    	greetingService.getGreeting(null, "small", Long.valueOf(123L));
    }

    @Test(expected = ValidationException.class)
    public void testInvalidAccount2() throws GreetingException, ValidationException {
    	greetingService.getGreeting("x", "small", Long.valueOf(123L));
    }

    @Test(expected = ValidationException.class)
    public void testInvalidType() throws GreetingException, ValidationException {
    	greetingService.getGreeting("personal", "x", Long.valueOf(123L));
    }

    @Test(expected = ValidationException.class)
    public void testInvalidId1() throws GreetingException, ValidationException {
    	greetingService.getGreeting("personal", null, null);
    }

    @Test(expected = ValidationException.class)
    public void testInvalidId2() throws GreetingException, ValidationException {
    	greetingService.getGreeting("personal", "big", Long.valueOf(-1));
    }

    /* Test invalid input end */


    /* Test invalid messages start */

    @Test(expected = GreetingException.class)
    public void testInvalidValidGreeting12() throws GreetingException, ValidationException {
    	greetingService.getGreeting("business", "small", Long.valueOf(123L));
    }

    @Test(expected = GreetingException.class)
    public void testInvalidValidMessage2() throws GreetingException, ValidationException {
    	greetingService.getGreeting("personal", "small", null);
    }

    /* Test invalid messages end */

}
import controller.ApplicationController;
import io.javalin.http.Context;
import model.Account.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.AccountServiceImpl;
import java.util.*;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
public class LoginTest {
	//private Application app = new Application();
	private ApplicationController applicationController;
	private AccountServiceImpl accountServiceImpl;
	private Context ctx;

	@Mock	AccountServiceImpl mockAccServ;

	@Before public void setUp() {
		accountServiceImpl = new AccountServiceImpl();
	}

	@Test	public void testUserNameExistValid() {
		assertTrue(accountServiceImpl.usernameExist("criticbob"));
	}
	
	@Test	public void testUserNameExistInvalid() {
		assertFalse(accountServiceImpl.usernameExist("'asdf"));
	}

	//testing for login of already created user
	@Test	public void testLogin() {
		Account expectedAccount = accountServiceImpl.login("user", "linus", "s3668218");
		assertEquals("user", expectedAccount.getAccountType());
		assertEquals("linus", expectedAccount.getUsername());
		assertEquals("s3668218", expectedAccount.getPassword());
		assertEquals(true, expectedAccount.getApproved());
		assertEquals("Australia", expectedAccount.getCountry());
		assertEquals("linus@rmit.edu.au", expectedAccount.getEmail());
		assertEquals("Linus", expectedAccount.getFirstName());
		assertEquals("MALE", expectedAccount.getGender());
		assertNull(expectedAccount.getPcoID());
		assertEquals("Kay", expectedAccount.getLastName());
		assertEquals("1", expectedAccount.getUserID());
		assertEquals(true, expectedAccount.getApproved());
		assertEquals(null, expectedAccount.getOrganisation());
		assertEquals(null, expectedAccount.getPhone());
	}

	//testing for invalid accounttype
	@Test	public void testInvalidLogin() {
		Account expectedAccount = accountServiceImpl.login("notavaliduser", "linus", "s3668218");
		assertEquals(null, expectedAccount);
	}
	
	//if this fails we're vulnerable to sql injection, oh well that can be fixed in our next security update
	//able to grab linus' password though so he better watch out
	@Test public void testAccountInjection(){
		Account expectedAccount = accountServiceImpl.login("admin", "admin", "xxx' or 1=1 -- ]");
		System.out.println(expectedAccount.getPassword());
		assertEquals(any(Account.class), expectedAccount);
	}
	
	//if this fails then it possible to access admin account, good thing we're not live yet
	@Test public void testAdminPasswordInjection(){
		Account admin = accountServiceImpl.login("admin", "admin' -- ];", "");
		assertNotEquals("password", admin.getPassword());
	}
}
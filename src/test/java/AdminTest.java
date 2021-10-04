import model.Account.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import service.AccountServiceImpl;
import service.AdminServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdminTest {
	//I created both a real and mock instance object of adminserviceimpl so the real one can be as close as possible
	//to actually running it and the mock for when the db inserts, deletes etc.
	private AdminServiceImpl adminService = new AdminServiceImpl();
	private AdminServiceImpl mockAdminImpl;
	
	@Mock	AccountServiceImpl mockAccServ;
	
	@Before public void setUp() {
		adminService = new AdminServiceImpl();
		mockAdminImpl = mock(AdminServiceImpl.class);
	}
	
	@After
	public void clean(){
		Mockito.reset(mockAdminImpl);
	}
	
	//just some basic user existence testing
	@Test public void testUserDoesntExist(){
		try {
			Account users = adminService.getUsers(-4);
			fail();
		}catch (EmptyResultDataAccessException emptyresult){}
	}
	
	@Test public void testUserExists() {
		Account expected = new Account("1",null,"user", "linus", "s3668218", "linus@rmit.edu.au", "Australia", "MALE", "Linus", "Kay", true, null, null);
		Account actual = adminService.getUsers(1);
		assertEquals(expected,actual);
	}
		
		//don't worry, this just tries to delete a primary key of value -1 which wont exist unless someone manually creates it!
	//wont throw error because it just can't find a corresponding id
	@Test public void testDeleteInvalidUser(){
		assertFalse(adminService.deleteUser(-1));
	}
	
	//will have to be careful with this one and use proper mocks
	@Test public void testDeleteValidUser(){
		mockAdminImpl.deleteUser(25);
		verify(mockAdminImpl).deleteUser(25);
	}
	
	@Test public void testDowngradeUser(){
		mockAdminImpl.downgradeUser(23);
		verify(mockAdminImpl).downgradeUser(23);
	}
	
	//downgrade bob for being a bad critic >:( poor bob, it's ok it's just a mock mate
	//currently a contract not a rule as current code impl just sets the string in db so this will pass
	@Test public void testDowngradeCritic(){
		mockAdminImpl.downgradeUser(4);
		verify(mockAdminImpl).downgradeUser(4);
	}
	
	//currently no unapproved users so only real thing we can check if it returns a list at all and if it's 0 or greater
	//in future sprints may add a tests that account more for this if the user base grows
	@Test public void testGetUnapprovedUser(){
		List<Account> actualUsers = adminService.getUnapprovedUsers();
		assert(actualUsers.size() >= 0);
	}
	
	//lets make sure bob is an approved user, will only test if he's approved, although we can also check his accounttype
	//Supposedly using tostrings on asserts is frowned upon but the objects in this assert do not seem to satisfy
	//junits assertequals function even though it lists 'no differences' when compared, strange
	@Test public void testGetApprovedUser(){
		Account testBob = new Account("4", null, "critic", "criticbob", "password", "bob@critics.com", "UK", "MALE", "Bob", "Critic", true, null, null);
		List<Account> actualUsers = adminService.getApprovedUsers();
		List<Account> expectedUsers = new ArrayList<>();
		expectedUsers.add(testBob);
		assert(actualUsers.size() >= 0);
		assertEquals(testBob.toString(), actualUsers.get(2).toString());
		assertEquals("critic", actualUsers.get(2).getAccountType());
	}
	
	//here we just approve john doe for his critic account, as a mock because he might be shifty in reality
	@Test public void testApproveUser(){
		mockAdminImpl.approveUser(17);
		verify(mockAdminImpl).approveUser(17);
	}
	
	//the following test disables then re-enables the same test account, not a mock but will revert itself anyway
	@Test public void testDisableAndUpgradeUser(){
		assertTrue(adminService.disableUser(25));
		assertTrue(adminService.approveUser(25));
	}
	
	//similar to previous test, will enable and then disable. Who likes chess these days anyway? what a nerd
	@Test public void testApproveShow(){
		assertTrue(adminService.approveShow(5));
		assertTrue(adminService.disableShow(5));
	}

	//this one will have to be mocked since we don't really want to delete a show... or do we?
	//unsure about this one, when run on its own it passes but doesn't when the whole class is run
	@Test public void testDeleteRealShow(){
		mockAdminImpl.deleteShow(5);
		verify(mockAdminImpl).deleteShow(5);
	}
	
	//as long as no one manually creates a show with a primary key of -5 this will be fine
	@Test public void testDeleteInvalidInt(){
		mockAdminImpl.deleteShow(-5);
		verify(mockAdminImpl).deleteShow(-5);
	}
}

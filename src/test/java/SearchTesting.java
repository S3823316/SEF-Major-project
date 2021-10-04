import model.Shows.TVShow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import service.AccountServiceImpl;
import java.util.*;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/*************** This class is responsible for testing the main features of what standard users can do yes I know it
only "tests the database" but I've explained this in the (TestingReadme.txt) file located in this directory **********/

public class SearchTesting {
	private AccountServiceImpl accountServiceImpl;
	@Mock private AccountServiceImpl mockAccountServiceImpl;
	
	//signup is pretty tough to test, so will test it in future sprints
	//mock service to test controllers
	@Before	public void setUp(){
		accountServiceImpl = new AccountServiceImpl();
		mockAccountServiceImpl = mock(AccountServiceImpl.class);
	}
	//cleans the dirty mock otherwise some tests will fail
	@After public void clean(){
		Mockito.reset(mockAccountServiceImpl);
	}
	
	//a bit tough to test ALL shows especially since there are always going to be more added
	//can at least test size and the first result returned which is what this test does
	@Test public void testViewShowsValid(){
		TVShow mrrobot = new TVShow(1,1,"Mr Robot", "Elliot, a cyber-security engineer suffering from anxiety, works for a corporation and hacks felons by night. Panic strikes him after Mr Robot, a cryptic anarchist, recruits him to ruin his company.",50, null ,"English","USA","Drama/Thriller",null,0,null,0,0,true);
		List<TVShow> results = accountServiceImpl.view("id", "%");
		
		assert(results.size() > 0);
		assertEquals(mrrobot.getID(), results.get(0).getID());
		assertEquals(mrrobot.getTitle(), results.get(0).getTitle());
		assertEquals(mrrobot.getSeasons(), results.get(0).getSeasons());
		assertEquals(mrrobot.getApproved(), results.get(0).getApproved());
		assertEquals(mrrobot.getLanguage(), results.get(0).getLanguage());
	}
	
	//even those this passes null to the search it just passes a string null so should return empty list
	@Test public void testViewShowsNull(){
		List<TVShow> results = accountServiceImpl.view("id", null);
		assertEquals(Collections.emptyList(),results);
	}
	
	@Test public void testViewShowsInvalid(){
		try {
			List<TVShow> results = accountServiceImpl.view("id", "hello'");
			fail("shouldn't be able to accept quote marks");
		}catch(BadSqlGrammarException badgram){}
	}
	
	//trying some sql injection, should run but return empty string if prepared statements are used
	@Test public void testViewShowsInjectOR(){
		List<TVShow> results = accountServiceImpl.view("id", "xxx' or 1=1 -- ]");
		System.out.println(results);
		assertEquals(Collections.emptyList(),results);
	}
	
	//I know you can just search for all shows but this is still just grabbing it by manipulating queries like above
	@Test public void testViewShowsInjectAll(){
		List<TVShow> results = accountServiceImpl.view("id", "admin' or 1=1 -- ]");
		System.out.println("Results from id table: \n" + results);
		assertEquals(Collections.emptyList(),results);
	}
	
	//testing for incorrect column name, any search parameter is entered just to make sure
	@Test public void testInvalidSearchView(){
		try {
			List<TVShow> results = accountServiceImpl.view("invalidcolumn", "%");
			fail("No exception");
		} catch (BadSqlGrammarException notwellgrammar){}
	}
	
	//testing for correct column but impossible search parameter to make sure it doesn't throw an exception
	//more than anything and the list just returns empty the following tests basically speak for themselves
	@Test public void testNoResultsSearchView(){
		List<TVShow> expected = new ArrayList<>();
		List<TVShow> results = accountServiceImpl.view("id", "0");
		assertEquals(expected, results);
		assertEquals(0, results.size());
	}
	
	@Test public void testViewSpecificShow(){
		List<TVShow> actualshow = accountServiceImpl.view("title", "Mr Robot");
		assertEquals("Mr Robot", actualshow.get(0).getTitle());
	}
	
	//misplaced or misused argument matcher detected, same with one test on admin
	//figure out later if not delete
	@Test public void testViewSpecificReview(){
		List<Map<String, Object>> actualReview = accountServiceImpl.viewReviews("showid", 1);
		assertEquals("epic", actualReview.get(0).get("review"));
	}
	
	//there are already at least 5 shows so that's why that number is there, as well as testing if the reviews are there
	@Test public void testViewSpecificReview2(){
		List<Map<String, Object>> actualReview = accountServiceImpl.viewReviews("r_approved", 1);
		assert(actualReview.size() >= 5);
		assertEquals("cool", actualReview.get(0).get("review"));
		assertEquals("cool!", actualReview.get(1).get("review"));
		assertEquals("7", actualReview.get(2).get("review"));
	}
	
	@Test public void testViewSpecificReviewNull(){
		try {
			List<Map<String, Object>> actualReview = accountServiceImpl.viewReviews("notacolumn", 1);
			fail();
		}catch(BadSqlGrammarException notgoodgrammar){}
	}
	
	@Test public void testReviewInvalid(){
		boolean result = mockAccountServiceImpl.review(1, 1, 1, "cool story bro", "1", false);
		assertEquals(false, result);
	}
	
	@Test public void testReviewNull(){
		try {
			boolean result = accountServiceImpl.review(0, 0, 5, "", "1", false);
			fail();
		}catch(DataIntegrityViolationException baddata){};
	}
	
	//searching for production companies, pretty much the same as above just different methods
	@Test public void testViewPCoValid(){
		String expected = "Netflix";
		String actual = accountServiceImpl.viewPCo(3);
		assertEquals(expected, actual);
	}
	
	@Test public void testViewPCoInvalid(){
		String expected = null;
		String actual = accountServiceImpl.viewPCo(0);
		assertEquals(expected,actual);
	}
	
	@Test public void testViewAllPCo(){
		List<Map<String,  Object>> actual = accountServiceImpl.viewAllPCo();
		assertEquals("TestCorp", actual.get(1).get("pconame"));
		assertEquals("Netflix", actual.get(2).get("pconame"));
		assert(actual.size() > 0);
	}
}

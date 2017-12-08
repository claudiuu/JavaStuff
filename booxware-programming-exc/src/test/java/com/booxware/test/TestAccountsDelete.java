package com.booxware.test;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Claudiu Mateias
 * 
 */
public class TestAccountsDelete {

	private PersistenceInterface persistenceManager;
	private AccountServiceInterface accountManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void loadManagers() {
		persistenceManager = new PersistenceManager();
		accountManager = new AccountManager(persistenceManager);
		
		accountManager.register("claudiu", "claudiu@test.com", "password1");
		accountManager.register("florian", "florian@test.com", "abc123");
	}
	
	@Test
	public void testAccountDeleteGood() {
		accountManager.deleteAccount("claudiu");
		Account loginClaudiu = persistenceManager.findByName("claudiu");
		assertNull(loginClaudiu);
	}
	
	@Test
	public void testAccountDeleteBad() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(AccountManager.ERROR_INVALID_USERNAME);
		
		accountManager.deleteAccount("bugsBunny");
	}

}

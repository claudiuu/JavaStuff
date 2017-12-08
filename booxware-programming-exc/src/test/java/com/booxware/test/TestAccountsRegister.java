package com.booxware.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Claudiu Mateias
 * 
 */
public class TestAccountsRegister {
	
	private PersistenceInterface persistenceManager;
	private AccountServiceInterface accountManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void loadManagers() {
		persistenceManager = new PersistenceManager();
		accountManager = new AccountManager(persistenceManager);
	}
	
	@Test
	public void testRegisterGood() {
		Account registeredFlorian = accountManager.register("florian", "florian@test.com", "abc123");
		Account registeredClaudiu = accountManager.register("claudiu", "claudiu@test.com", "pasword1");
		
		// check that we can get by id
		Account florianById = persistenceManager.findById(registeredFlorian.getId());
		Account claudiuById = persistenceManager.findById(registeredClaudiu.getId());
		assertThat(registeredFlorian, is(florianById));
		assertThat(registeredClaudiu, is(claudiuById));
		
		// check that we can get by name
		Account florianByName = persistenceManager.findByName("florian");
		Account claudiuByName = persistenceManager.findByName("claudiu");
		assertThat(registeredFlorian, is(florianByName));
		assertThat(registeredClaudiu, is(claudiuByName));
		
		// password should not be saved in plain text
		assertNotEquals("abc123", new String(registeredFlorian.getEncryptedPassword()));
		assertNotEquals("password1", new String(registeredClaudiu.getEncryptedPassword()));
		
		// unregistered user should not be found
		assertNull(persistenceManager.findByName("bugs_bunny"));
		
		// ensure that we have unique ids
		assertNotEquals(registeredClaudiu.getId(), registeredFlorian.getId());
	}
	
	@Test
	public void testRegisterDuplicate() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_ACCOUNT_ALREADY_REGISTERED));
		
		accountManager.register("claudiu", "claudiu@test.com", "pasword1");
		accountManager.register("claudiu", "claudiu@anothertest.com", "abc");
	}
	
	@Test
	public void testRegisterInvalidData1() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_REGISTER_DATA));
		
		accountManager.register("", "", "");
	}
	
	@Test
	public void testRegisterInvalidData2() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_REGISTER_DATA));
		
		accountManager.register("claudiu", "email@test.com", "");
	}
	
	@Test
	public void testRegisterInvalidData3() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_REGISTER_DATA));
		
		accountManager.register("", "email@test.com", "password1");
	}
	
	@Test
	public void testRegisterInvalidData4() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_REGISTER_DATA));
		
		accountManager.register("claudiu", "", "password1");
	}

}

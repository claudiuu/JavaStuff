package com.booxware.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Claudiu Mateias
 * 
 */
public class TestAccountsLogin {

	private PersistenceInterface persistenceManager;
	private AccountServiceInterface accountManager;
	private Account userClaudiu;
	private Account userFlorian;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void loadManagers() {
		persistenceManager = new PersistenceManager();
		accountManager = new AccountManager(persistenceManager);
		
		userClaudiu = accountManager.register("claudiu", "claudiu@test.com", "password1");
		userFlorian = accountManager.register("florian", "florian@test.com", "abc123");
	}
	
	@Test
	public void testLoginGood() {
		Account loginClaudiu = accountManager.login("claudiu", "password1");
		assertNotNull(loginClaudiu);
		assertThat(userClaudiu, is(loginClaudiu));
		
		Account loginFlorian = accountManager.login("florian", "abc123");
		assertNotNull(loginFlorian);
		assertThat(userFlorian, is(loginFlorian));
	}
	
	@Test
	public void testLoginInvalidUser() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_USERNAME));
		
		accountManager.login("bugsBunny", "carrots");
	}
	
	@Test
	public void testLoginInvalidPassword() {
		thrown.expect(AccountServiceException.class);
		thrown.expectMessage(is(AccountManager.ERROR_INVALID_PASSWORD));
		
		accountManager.login("claudiu", "abc");
	}
	
	@Test
	public void testLoginSince() {
		Account loginClaudiu = accountManager.login("claudiu", "password1");
		Account loginFlorian = accountManager.login("florian", "abc123");
		
		Date oneHourAgo = hoursAgo(1);
		Date twoHoursAgo = hoursAgo(2);
		
		loginClaudiu.setLastLogin(oneHourAgo);
		assertTrue(accountManager.hasLoggedInSince("claudiu", twoHoursAgo));
		
		loginFlorian.setLastLogin(twoHoursAgo);
		assertFalse(accountManager.hasLoggedInSince("florian", oneHourAgo));
		
		loginFlorian.setLastLogin(null);
		assertFalse(accountManager.hasLoggedInSince("florian", twoHoursAgo));
	}
	
	/**
	 * @param hoursAgo
	 * @return
	 */
	private Date hoursAgo(int hoursAgo) {
		Instant inst = LocalDateTime.now().minusHours(hoursAgo).toInstant(ZoneOffset.UTC);
		return Date.from(inst);
	}

}

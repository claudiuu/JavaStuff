package com.booxware.test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Claudiu Mateias
 * 
 */
public class AccountManager implements AccountServiceInterface {

	public static final String ERROR_INVALID_USERNAME = "No account was found for the specified username";
	public static final String ERROR_INVALID_PASSWORD = "Password is incorrect";
	public static final String ERROR_INVALID_REGISTER_DATA = "Username, email and password cannot be blank";
	public static final String ERROR_ACCOUNT_ALREADY_REGISTERED = "There is another account registered with the specified username";

	private PersistenceInterface persistenceManager;

	/**
	 * @param persistenceManager
	 */
	public AccountManager(PersistenceInterface persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.AccountServiceInterface#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Account login(String username, String password) {
		Account account = persistenceManager.findByName(username);
		if (account == null) {
			throw new AccountServiceException(ERROR_INVALID_USERNAME);
		}
		validateLoginPassword(account, password);
		account.setLastLogin(new Date());

		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.AccountServiceInterface#register(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Account register(String username, String email, String password) {
		validateRegisterData(username, email, password);

		SecureData secureData = encryptPassword(password);
		Account account = new Account(username, email, secureData.encryptedPassword, secureData.salt);
		persistenceManager.save(account);

		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.AccountServiceInterface#deleteAccount(java.lang.String)
	 */
	@Override
	public void deleteAccount(String username) {
		Account account = persistenceManager.findByName(username);
		if (account == null) {
			throw new AccountServiceException(ERROR_INVALID_USERNAME);
		}
		persistenceManager.delete(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.AccountServiceInterface#hasLoggedInSince(java.lang.String, java.util.Date)
	 */
	@Override
	public boolean hasLoggedInSince(String username, Date date) {
		Account account = persistenceManager.findByName(username);
		if (account == null) {
			throw new AccountServiceException(ERROR_INVALID_USERNAME);
		}
		if (account.getLastLogin() == null) {
			return false;
		}
		return account.getLastLogin().after(date);
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @throws AccountServiceException
	 */
	private void validateRegisterData(String username, String email, String password) throws AccountServiceException {
		if (StringUtils.isAnyBlank(username, email, password)) {
			throw new AccountServiceException(ERROR_INVALID_REGISTER_DATA);
		}
		Account accountByName = persistenceManager.findByName(username);
		if (accountByName != null) {
			throw new AccountServiceException(ERROR_ACCOUNT_ALREADY_REGISTERED);
		}
	}

	/**
	 * @param account
	 * @param password
	 */
	private void validateLoginPassword(Account account, String password) {
		byte[] correctPassword = account.getEncryptedPassword();
		byte[] providedPassword = encryptPassword(password, account.getSalt());

		if (!Arrays.equals(correctPassword, providedPassword)) {
			throw new AccountServiceException(ERROR_INVALID_PASSWORD);
		}
	}

	/**
	 * @param password
	 * @return
	 */
	private SecureData encryptPassword(String password) {
		String salt = generateSalt();
		byte[] pswd = encryptPassword(password, salt);
		return new SecureData(pswd, salt);
	}

	/**
	 * @param password
	 * @param salt
	 * @return
	 */
	private byte[] encryptPassword(String password, String salt) {
		Encoder encoder = Base64.getEncoder();
		String textToEncode = password + salt;

		return encoder.encode(textToEncode.getBytes());
	}

	/**
	 * @return a randomly generated salt
	 */
	private String generateSalt() {
		SecureRandom salter = new SecureRandom();
		byte[] salt = new byte[16];
		salter.nextBytes(salt);
		return new String(salt);
	}

	/**
	 * This is just a wrapper used to hold the encrypted password together with the salt used for encrypting it
	 * 
	 * @author Claudiu Mateias
	 * 
	 */
	private class SecureData {

		private byte[] encryptedPassword;
		private String salt;

		/**
		 * @param password
		 * @param salt
		 */
		SecureData(byte[] password, String salt) {
			this.encryptedPassword = password;
			this.salt = salt;
		}
	}
}

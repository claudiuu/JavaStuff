package com.booxware.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Claudiu Mateias
 * 
 */
public class PersistenceManager implements PersistenceInterface {

	private Map<Long, Account> accounts = new HashMap<>();
	private long lastId = 0;

	/**
	 * 
	 */
	public PersistenceManager() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.PersistenceInterface#save(com.booxware.test.Account)
	 */
	@Override
	public void save(Account account) {
		account.setId(++lastId);
		accounts.put(account.getId(), account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.PersistenceInterface#findById(long)
	 */
	@Override
	public Account findById(long id) {
		return accounts.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.PersistenceInterface#findByName(java.lang.String)
	 */
	@Override
	public Account findByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		Optional<Account> result = accounts.values().stream().filter(a -> a.getUsername().equals(name)).findFirst();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booxware.test.PersistenceInterface#delete(com.booxware.test.Account)
	 */
	@Override
	public void delete(Account account) {
		if (account == null) {
			return;
		}
		Long id = account.getId();
		if (accounts.containsKey(id)) {
			accounts.remove(id);
		}
	}

}

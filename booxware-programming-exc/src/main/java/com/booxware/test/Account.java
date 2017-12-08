package com.booxware.test;

import java.io.Serializable;
import java.util.Date;

/**
 * The encryption can be very simple, we don't put much emphasis on the encryption algorithm.
 */
public class Account implements Serializable {

	private long id;

	private String username;

	private byte[] encryptedPassword;

	private String salt;

	private String email;

	private Date lastLogin;

	/**
	 * @param username
	 * @param email
	 * @param password
	 */
	public Account(String username, String email, byte[] password, String salt) {
		// these properties can only be set through the constructor, not through setters
		this.username = username;
		this.email = email;
		this.encryptedPassword = password;
		this.salt = salt;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the encryptedPassword
	 */
	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [username=" + username + ", email=" + email + "]";
	}

}

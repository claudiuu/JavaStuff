package com.clausoftware.engine;

import com.clausoftware.advertiser.Advertiser;

/**
 * Defines the Result object containing the result of a search and who sponsored the result, if any.
 * 
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class Result {

	private final String text;
	private final Advertiser sponsoredBy;
	
	
	/**
	 * @param text
	 */
	public Result(String text) {
		this(text, null);
	}
	
	/**
	 * @param text
	 * @param sponsoredBy
	 */
	public Result(String text, Advertiser sponsoredBy) {
		this.text = text;
		this.sponsoredBy = sponsoredBy;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return the sponsoredBy
	 */
	public Advertiser getSponsoredBy() {
		return sponsoredBy;
	}
	
	/**
	 * @return
	 */
	public boolean isSponsored() {
		return sponsoredBy != null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(text);
		if (isSponsored()) {
			builder.append(String.format(" (sponsored by %s)", sponsoredBy));
		}
		
		return builder.toString();
	}

}

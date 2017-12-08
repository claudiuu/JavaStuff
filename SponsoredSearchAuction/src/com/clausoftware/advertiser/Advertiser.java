package com.clausoftware.advertiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class Advertiser {

	private String name;
	private List<String> searchTerms = new ArrayList<>();
	private Map<String, Double> bids = new HashMap<>();
	
	
	/**
	 * @param name
	 */
	public Advertiser(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the searchTerms
	 */
	public List<String> getSearchTerms() {
		return searchTerms;
	}
	
	/**
	 * @param searchTerms
	 */
	public void setSearchTerms(List<String> searchTerms) {
		this.searchTerms = searchTerms;
	}
	
	/**
	 * @return the bids
	 */
	public Map<String, Double> getBids() {
		return bids;
	}

	/**
	 * @param bids
	 */
	public void setBids(Map<String, Double> bids) {
		this.bids = bids;
	}
	
	/**
	 * @param searchWord
	 * @param bid
	 */
	public void addBid(String searchWord, double bid) {
		bids.put(searchWord, bid);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Advertiser other = (Advertiser) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}

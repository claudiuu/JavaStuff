package com.clausoftware.auction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.clausoftware.advertiser.Advertiser;
import com.clausoftware.engine.Result;

/**
 * Defines the object that will perform the auction. The type of auction used is defined by the IAuctionHandler field
 * 
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class Auctioneer {

	private List<Advertiser> registeredAdvertisers = new ArrayList<>();
	private IAuctionHandler handler;
	
	/**
	 * 
	 */
	public Auctioneer() {
		
	}
	
	/**
	 * @return the registeredAdvertisers
	 */
	public List<Advertiser> getRegisteredAdvertisers() {
		return registeredAdvertisers;
	}

	/**
	 * @param registeredAdvertisers
	 */
	public void addAvertisers(List<Advertiser> advertisers) {
		registeredAdvertisers.addAll(advertisers);
	}
	
	
	/**
	 * @return the handler
	 */
	public IAuctionHandler getHandler() {
		return handler;
	}

	
	/**
	 * @param handler
	 */
	public void setHandler(IAuctionHandler handler) {
		this.handler = handler;
	}

	/**
	 * @param slots
	 * @param searchWord
	 * @return
	 */
	public List<Result> performAuction(int slots, String searchWord) {
		List<Result> filteredAndSortedList = handler.sortBidders(registeredAdvertisers, searchWord)
													.stream()
													.map(ad -> new Result(searchWord, ad))
													.collect(Collectors.toList());
		return filteredAndSortedList;
	}
}

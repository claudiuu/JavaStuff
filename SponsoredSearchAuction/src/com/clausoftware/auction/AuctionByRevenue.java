package com.clausoftware.auction;

import java.util.List;
import java.util.stream.Collectors;

import com.clausoftware.advertiser.Advertiser;
import com.clausoftware.engine.SearchEngine;


/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class AuctionByRevenue implements IAuctionHandler {

	/**
	 * 
	 */
	public AuctionByRevenue() {
	}

	/* (non-Javadoc)
	 * @see com.clausoftware.main.IAuctionHandler#sortBidders(java.util.List, java.lang.String)
	 */
	@Override
	public List<Advertiser> sortBidders(List<Advertiser> advertisers, String searchWord) {
		return advertisers.stream()
				.filter(ad -> ad.getBids().keySet().contains(searchWord)) // keep only the bidders that bided for the search word
				.sorted((ad1, ad2) -> calculateScore(ad2, searchWord).compareTo(calculateScore(ad1, searchWord))) // sort in descending order based on score value
				.collect(Collectors.toList());
	}
	
	/**
	 * @param advertiser
	 * @param searchWord
	 * @return
	 */
	private Double calculateScore(Advertiser advertiser, String searchWord) {
		Double bid = advertiser.getBids().get(searchWord);
		Double weight = SearchEngine.getInstance().getWeightForAdvertiser(advertiser);
		if (weight == null) {
			weight = 0d;
		}
		
		return bid * weight;
	}

}

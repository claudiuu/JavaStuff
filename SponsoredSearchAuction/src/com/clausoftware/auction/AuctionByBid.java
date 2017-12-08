package com.clausoftware.auction;

import java.util.List;
import java.util.stream.Collectors;

import com.clausoftware.advertiser.Advertiser;


/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class AuctionByBid implements IAuctionHandler {

	/**
	 * 
	 */
	public AuctionByBid() {
	}

	/* (non-Javadoc)
	 * @see com.clausoftware.main.IAuctionHandler#sortBidders(java.util.List, java.lang.String)
	 */
	@Override
	public List<Advertiser> sortBidders(List<Advertiser> advertisers, String searchWord) {
		return advertisers.stream()
						.filter(ad -> ad.getBids().keySet().contains(searchWord)) // keep only the bidders that bided for the search word
						.sorted((ad1, ad2) -> ad2.getBids().get(searchWord).compareTo(ad1.getBids().get(searchWord))) // sort in descending order based on bid value
						.collect(Collectors.toList());
	}

}

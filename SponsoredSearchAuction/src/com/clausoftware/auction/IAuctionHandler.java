package com.clausoftware.auction;

import java.util.List;

import com.clausoftware.advertiser.Advertiser;

/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public interface IAuctionHandler {

	/**
	 * @param advertisers
	 * @param searchWord
	 * @return
	 */
	public List<Advertiser> sortBidders(List<Advertiser> advertisers, String searchWord);
}

package com.clausoftware.auction;

import java.util.List;

import com.clausoftware.advertiser.Advertiser;

/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class AuctioneerBuilder {

	private Auctioneer auctioneer;
	
	/**
	 * 
	 */
	public AuctioneerBuilder() {
		auctioneer = new Auctioneer();
	}
	
	/**
	 * @param advertisers
	 * @return
	 */
	public AuctioneerBuilder registerBidders(List<Advertiser> advertisers) {
		auctioneer.addAvertisers(advertisers);
		return this;
	}
	
	/**
	 * @param handler
	 * @return
	 */
	public AuctioneerBuilder addActionHandler(IAuctionHandler handler) {
		auctioneer.setHandler(handler);
		return this;
	}

	/**
	 * @return
	 */
	public Auctioneer build() {
		if (auctioneer.getHandler() == null) {
			// if it was not set use the default one
			addActionHandler(new DefaultAuctionHandler());
		}
		return auctioneer;
	}
}

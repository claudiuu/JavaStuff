package com.clausoftware.advertiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will create some Advertiser objects to be used for testing
 * 
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class AdvertiserGenerator {

	private Map<String, Advertiser> advertisers = new HashMap<>();
	
	/**
	 * 
	 */
	public AdvertiserGenerator() {
		Advertiser ad1 = new Advertiser("Flower Company 1");
		ad1.addBid("flowers", 45d);
		ad1.addBid("roses", 45d);
		ad1.addBid("perfect gift", 5d);
		
		Advertiser ad2 = new Advertiser("Flower Company 2");
		ad2.addBid("flowers", 50d);
		ad2.addBid("tulips", 30d);
		ad2.addBid("lily", 30d);
	
		Advertiser ad3 = new Advertiser("Lily");
		ad3.addBid("lily", 60d);
		ad3.addBid("flowers", 40d);
		
		advertisers.put(ad1.getName(), ad1);
		advertisers.put(ad2.getName(), ad2);
		advertisers.put(ad3.getName(), ad3);
	}
	
	/**
	 * @return
	 */
	public List<Advertiser> getAdvertisers() {
		return new ArrayList<>(advertisers.values());
	}

}

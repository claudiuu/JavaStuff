package com.clausoftware.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import com.clausoftware.advertiser.Advertiser;
import com.clausoftware.auction.Auctioneer;
import com.clausoftware.auction.AuctioneerBuilder;

/**
 * The Search Engine. This class will take care of performing the search and collect the sponsored results from the Auctioneer
 * 
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class SearchEngine {

	private static SearchEngine engine;
	
	private List<Advertiser> registeredAdvertisers = new ArrayList<>();
	private Map<Advertiser, Double> advertiserWeightValues = new HashMap<>();
	
	/**
	 * 
	 */
	private SearchEngine() {
		
	}
	
	/**
	 * @return the single instance of the SearchEngine
	 */
	public static SearchEngine getInstance() {
		if (engine == null) {
			engine = new SearchEngine();
		}
		return engine;
	}
	
	/**
	 * @param advertiser
	 */
	public void registerAdvertiser(Advertiser advertiser) {
		Random ran = new Random();
		// need to decide who will generate the weight? 
		// assuming that the SearchEngine is doing this but could also be the Auctioneer or a new class?
		advertiserWeightValues.put(advertiser, ran.nextDouble());
		registeredAdvertisers.add(advertiser);
	}
	
	/**
	 * @param advertiser
	 * @return the weight assigned to the advertiser. Will return null if Advertiser is not registered
	 */
	public Double getWeightForAdvertiser(Advertiser advertiser) {
		return advertiserWeightValues.get(advertiser);
	}

	/**
	 * Searches for the word passed as parameter. 
	 * The method will return the list of Results, maximum the number of results specified by the SearchEngineParameters.
	 * The first results from the list will be the ones returned by the Auctioneer.
	 * 
	 * @param searchWord
	 * @return
	 */
	public List<Result> performSearch(String searchWord) {
		List<Result> results = new ArrayList<>();
		// register the bidders to the Auctioneer
		Auctioneer auctioneer = new AuctioneerBuilder().registerBidders(registeredAdvertisers).build();
		// let the Auctioneer perform the auction and retrieve the sponsored results
		List<Result> sponsoredResults = auctioneer.performAuction(SearchEngineParameters.SPONSORED_SLOTS, searchWord);
		
		// add the sponsored results to the final list of results
		results.addAll(sponsoredResults);
		// now add the "real" results to the final list
		results.addAll(generateResults(searchWord, SearchEngineParameters.MAX_RESULTS - sponsoredResults.size()));
		
		return results;
	}
	
	/**
	 * @param searchWord
	 * @param size
	 * @return a list of results for the specified searchWord. The list size will be the size passed as parameter
	 */
	private List<Result> generateResults(String searchWord, int size) {
		List<Result> fakeResults = new ArrayList<>();
		
		IntStream.range(1, size).forEach(i -> fakeResults.add(new Result(searchWord + i)));
		
		return fakeResults;
	}
}

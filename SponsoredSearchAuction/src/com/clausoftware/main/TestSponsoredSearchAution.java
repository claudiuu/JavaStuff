package com.clausoftware.main;

import java.util.List;

import com.clausoftware.advertiser.AdvertiserGenerator;
import com.clausoftware.engine.Result;
import com.clausoftware.engine.SearchEngine;

/**
 * @author claudiu
 * @since Nov 21, 2017
 * 
 */
public class TestSponsoredSearchAution {

	private SearchEngine searchEngine;
	
	/**
	 * 
	 */
	public TestSponsoredSearchAution() {
		searchEngine = SearchEngine.getInstance();
		new AdvertiserGenerator().getAdvertisers()
								.forEach(ad -> searchEngine.registerAdvertiser(ad));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSponsoredSearchAution test = new TestSponsoredSearchAution();
		test.search("flowers");
		test.search("lily");
		test.search("smartphones");

	}
	
	/**
	 * @param word
	 */
	private void search(String word) {
		System.out.println("--------------------------------------");
		
		System.out.println(String.format("Searching for '%s'", word));
		printSearchResult(searchEngine.performSearch(word));
		
		System.out.println("--------------------------------------");
	}

	
	/**
	 * @param results
	 */
	private void printSearchResult(List<Result> results) {
		results.forEach(res -> System.out.println(res));
	}
}

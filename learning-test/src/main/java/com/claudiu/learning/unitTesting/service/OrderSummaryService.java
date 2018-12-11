package com.claudiu.learning.unitTesting.service;

import com.claudiu.learning.unitTesting.beans.OrderSummary;

public class OrderSummaryService {

	private OrderSummary orderSummary;
	
	public OrderSummaryService() {
	}
	
	public void sendOrder() {
		if (this.orderSummary.getItems().isEmpty()) {
			throw new IllegalArgumentException("Order doesn't contain any items");
		}
		
		// do something with the order
	}

}

package com.claudiu.learning.unitTesting.beans;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary {

	private List<OrderItem> items = new ArrayList<>();
	private double discount;
	private double totalPrice;

	public OrderSummary(Basket basket) {
		this.items.addAll(basket.getItems());
	}

	public List<OrderItem> getItems() {
		return this.items;
	}

	public double getDiscount() {
		return this.discount;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}

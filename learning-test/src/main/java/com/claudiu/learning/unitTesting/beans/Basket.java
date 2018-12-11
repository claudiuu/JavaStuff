package com.claudiu.learning.unitTesting.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Basket {

	private List<OrderItem> items = new ArrayList<>();
	
	public List<OrderItem> getItems() {
		return this.items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public boolean isEmpty() {
		return this.items.isEmpty();
	}

	public boolean remove(Object o) {
		return this.items.remove(o);
	}

	public boolean addAll(Collection<? extends OrderItem> c) {
		return this.items.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return this.items.removeAll(c);
	}

	public void add(int index, OrderItem element) {
		this.items.add(index, element);
	}


}

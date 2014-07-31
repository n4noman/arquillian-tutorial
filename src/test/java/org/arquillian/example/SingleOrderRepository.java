package org.arquillian.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Lock(LockType.READ)
@Singleton
public class SingleOrderRepository implements OrderRepository{
	private List<List<String>> orders;
	
	@Lock(LockType.WRITE)
	@Override
	public void addOrder(List<String> order) {
		orders.add(order);
		
	}

	@Override
	public List<List<String>> getOrders() {
		return Collections.unmodifiableList(orders);
	}

	@Override
	public int getOrderCount() {
		return orders.size();
	}
	
    @PostConstruct
    public void initialize() {
    	orders = new ArrayList<List<String>>();
    }

}

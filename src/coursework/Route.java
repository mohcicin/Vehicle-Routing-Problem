package coursework;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.stream.Stream;

public class Route {

	public ArrayList<Customer> customers;
	public int goods;
	public int truckCapacity;
	
	public Route(int truckCapa){
		this.customers = new ArrayList<Customer>();
		this.goods = 0;
		this.truckCapacity = truckCapa;
	}

	public Customer lastDelivery() {
		return customers.get(customers.size()-1);
	}

	// returns true if this truck has the capacity to add a customer needing 'capa' goods
	public boolean hasCapacity(int capa) {
		if(this.goods + capa <= truckCapacity )
			return true;
		return false;
	}
	
	/*
	 * Returns the original route, the one that must be discarded
	 * Modifies RouteY into the final route
	 */
	public Route merge(Route routeY) {
		for(Customer c: this.customers){
			routeY.add(c);
		}
		return this;
	}
	
	public boolean contains(Customer sn){
		if(this.customers.contains(sn))
			return true;
		return false;
	}

	// add a customer to the route
	public boolean add(Customer c) {
		// update the current weight of the packages in the truck
		this.goods += c.c;
		// update the status of the customer : he's been taking care of by a truck now
		c.setInRoute(true);
		// add the customer to the list of customer of the route
		return this.customers.add(c);
	}
	// add a customer anywhere you want in the arrayList
	public void add(int index, Customer c) {
		this.goods += c.c;
		c.setInRoute(true);
		this.customers.add(index,c);
	}
	
	public Customer get(int index){
		return customers.get(index);
	}	
	public int getGoods() {
		return this.goods;
	}
	public String toString(){
		return this.customers.toString();
	}
	public ArrayList<Customer> getCustomers() {
		return this.customers;
	}

}

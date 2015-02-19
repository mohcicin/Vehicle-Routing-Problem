package coursework;

import java.util.*;
//import java.util.function.Function;
//import java.util.function.ToDoubleFunction;
//import java.util.function.ToIntFunction;
//import java.util.function.ToLongFunction;
import java.io.*;

public class VRSolution {
	public static VRProblem prob;
	public List<List<Customer>>soln= new ArrayList<List<Customer>>();

	public ArrayList<Route> routes = new ArrayList<Route>();

	public VRSolution(VRProblem problem){
		this.prob = problem;
	}

	//The dumb solver adds one route per customer
	public void oneRoutePerCustomerSolution(){
		// initialise the list of lists
		this.soln = new ArrayList<List<Customer>>();
		// for each customer
		for(Customer c:prob.customers){
			// create a new route
			ArrayList<Customer> route = new ArrayList<Customer>();
			//add the customer to the route
			route.add(c);
			// add the route to the solution
			soln.add(route);
		}
	}

	private Customer findClosest(Customer cX) {
		Customer res = null;
		double min = this.prob.customers.get(0).distance(this.prob.customers.get(1));
		for(Customer c : this.prob.customers){
			if(c.distance(cX) < min){
				min = c.distance(cX);
				res = c;
			}
		}
		return res;
	}

	public boolean isOverCapacity(ArrayList<Customer> route){
		int total = 0;
		for(Customer c : route){
			total += c.c;
		}
		if(total > this.prob.depot.c)
			return true;
		return false;
	}
	public int goods(ArrayList<Customer> route){
		int goods = 0;
		for(Customer c: route){
			goods+=c.c;
		}
		return goods;
	}

	public int countCustomers(ArrayList<Customer> list){
		int res = 0;
		for(Customer c : list)
			res++;
		return res;
	}

	public void solution1(){
		// init
		this.soln = new ArrayList<List<Customer>>();
		this.routes = new ArrayList<Route>();

		int nbCustPerRoute = this.prob.customers.size() / 6;
		Route route = new Route(this.prob.depot.c);

		// for each customer
		for(Customer cust : this.prob.customers){
			if(route.customers.size() > nbCustPerRoute){// if route contains more than 1/6 of the customers
				System.out.println("TEST1");
				// remove last customer
				route.customers.remove(route.customers.get(route.customers.size()-1));
				this.routes.add(route);
				route = new Route(this.prob.depot.c);			
			}
			if( !cust.isInRoute()){
				System.out.println("TEST2");
				// start a route serving him
				route.add(findClosest(cust));

			}
		}


		// allocate any remaining customer to individual routes
		for(Customer c : this.prob.customers){
			if (! c.isInRoute()){
				Route r = new Route(this.prob.depot.c);
				r.add(c);
				this.routes.add(r);
			}
		}
		// add each route to the solution
		for(Route r : this.routes){
			this.soln.add(r.getCustomers());
		}

	}
	/*
	 * Clarke wright algorithm's implementation without considering potential routes merges
	 */
	public void Solution2(){
		//initialisation
		int maxCustomers = prob.customers.size();
		ArrayList<SavingNode> savings = new ArrayList<SavingNode>();

		// calculate the savings
		for(int i = 0 ; i < maxCustomers; i++){
			for(int j = 0; j < maxCustomers; j++){
				if(i != j){
					double saving = (prob.depot.distance(prob.customers.get(i)) + prob.depot.distance(prob.customers.get(j)) ) - prob.customers.get(i).distance(prob.customers.get(j));
					savings.add(new SavingNode(prob.customers.get(i),prob.customers.get(j),saving));
				}
			}
		}
		Collections.sort(savings);

		Route newR = new Route(this.prob.depot.c);
		for(SavingNode sn : savings){
			// if neither of the customers are in a route already
			if( !( sn.to.isInRoute() ) && !( sn.from.isInRoute() ) ){
				// and their requirements does not exceed the capacity of the truck
				if( (sn.from.c + sn.to.c) <= this.prob.depot.c ){
					// create a new route serving both of them
					newR = new Route(this.prob.depot.c);
					newR.add(sn.from);
					newR.add(sn.to);
					this.routes.add(newR);
					System.out.println("NEW ROUTE");
				}
			} else {
				// the customer 'to' isn't in a route
				if( !sn.to.isInRoute() ){
					for(Route route : routes){
						if(route.lastDelivery() == sn.from){
							if(route.hasCapacity(sn.to.c)){
								route.add(sn.to);
								System.out.println("ADD CUSTOMER TO EXISTING ROUTE");
								break;
							}
						}
					}
				}
				if(! sn.from.isInRoute() ){
					for(Route route: routes){
						if(route.lastDelivery() == sn.to){
							if(route.hasCapacity(sn.from.c)){
								route.add(0,sn.from);
								System.out.println("ADD CUSTOMER TO EXISTING ROUTE");
								break;
							}
						}
					}
				}
			}

		}

		// allocate any remaining customer to individual routes
		for(Customer c : this.prob.customers){
			if (! c.isInRoute()){
				Route r = new Route(this.prob.depot.c);
				r.add(c);
				this.routes.add(r);
				System.out.println("I'm all alone !");
			}
		}

		// add each route to the solution
		int i = 1;
		for(Route r : this.routes){
			this.soln.add(r.getCustomers());
			System.out.println("Route nb :"+i);
			i++;
		}

	}

	public void clarkeWrightAlgorithm(){

		//initialisation
		this.soln = new ArrayList<List<Customer>>();
		int maxCustomers = prob.customers.size();
		ArrayList<SavingNode> savings = new ArrayList<SavingNode>();

		// calculate the savings
		for(int i = 0 ; i < maxCustomers; i++){
			for(int j = 0; j < maxCustomers; j++){
				if(i != j){
					double saving = (prob.depot.distance(prob.customers.get(i)) + prob.depot.distance(prob.customers.get(j)) ) - prob.customers.get(i).distance(prob.customers.get(j));
					savings.add(new SavingNode(prob.customers.get(i),prob.customers.get(j),saving));
				}
			}
		}
		// sort the savings: highest first
		Collections.sort(savings);

		Route newR = new Route(this.prob.depot.c);
		for(SavingNode sn : savings){
			// if neither of the customers are in a route already
			if( !( sn.to.isInRoute() ) && !( sn.from.isInRoute() ) ){
				// and their requirements does not exceed the capacity of the truck
				if( (sn.from.c + sn.to.c) <= this.prob.depot.c ){
					// create a new route serving both of them
					newR = new Route(this.prob.depot.c);
					newR.add(sn.from);
					newR.add(sn.to);
					this.routes.add(newR);
				}
			} else {
				// the customer 'to' isn't in a route
				if( !sn.to.isInRoute() ){
					for(Route route : routes){
						if(route.lastDelivery() == sn.from){
							if(route.hasCapacity(sn.to.c)){
								route.add(sn.to);
								break;
							}
						}
					}
				}
				// the customer 'from' isn't in a route
				if(! sn.from.isInRoute() ){
					for(Route route: routes){
						if(route.lastDelivery() == sn.to){
							if(route.hasCapacity(sn.from.c)){
								route.add(0,sn.from);
								break;
							}
						}
					}
				}
			}

			Route merged = null;
			for(Route routeX: routes){
				if( merged != null ) break; // if a merge has been performed
				if(routeX.lastDelivery() == sn.from){
					for(Route routeY: routes){
						if(routeY.get(0) == sn.to){
							if(routeX != routeY){
								if( (routeX.getGoods() + routeY.getGoods())<= this.prob.depot.c){
									// add the customer of routeX to the routeY
									merged = routeX.merge(routeY);
									break;
								}
							}
						}
					}
				}
			}

			// if a merge was done
			if(merged != null)
				// remove the original route
				routes.remove(merged);

		}


		// allocate any remaining customer to individual routes
		for(Customer c : this.prob.customers){
			if (! c.isInRoute()){
				Route r = new Route(this.prob.depot.c);
				r.add(c);
				this.routes.add(r);
			}
		}

		// add each route to the solution
		for(Route r : this.routes){
			this.soln.add(r.getCustomers());
		}

	}

	/*
	 * 
	 * 
	 * 
	 */
	public void evolutionarySolver(){
		System.out.println("EVOLUTIONARY SOLVER --- START");

		// create the population
		Population myPop = new Population( this.prob.customers, 50, true); // init with random values
		
		FitnessCalc.setDepot(this.prob.depot);

		int cntGeneration = 0;
		while(cntGeneration < 100000 ){ // satisfaction criteria
			cntGeneration++;
			myPop = Algorithm.evolvePopulation( this.prob.customers, myPop);
		}
		System.out.println("Generation: "+cntGeneration + "\tFittest solution: "+ myPop.getFittest() + "\t Fit: " + myPop.getFittest().getFitness());
		

		this.soln = myPop.getFittest().getGenes();
		System.out.println("Population's fittest"+ myPop.getFittest());
		System.out.println("SOLUTION: "+this.soln);
		
		System.out.println("EVOLUTIONARY SOLVER --- END\n");
	}

	//Calculate the total journey
	public double solnCost(){
		double cost = 0;
		for(List<Customer>route:soln){
			Customer prev = this.prob.depot;
			for (Customer c:route){
				cost += prev.distance(c);
				prev = c;
			}
			//Add the cost of returning to the depot
			cost += prev.distance(this.prob.depot);
		}
		return cost;
	}

	public Boolean verify(){
		//Check that no route exceeds capacity
		Boolean okSoFar = true;
		for(List<Customer> route : soln){
			//Start the spare capacity at
			int total = 0;
			for(Customer c:route)
				total += c.c;
			if (total>prob.depot.c){
				System.out.printf("********FAIL Route starting %s is over capacity %d\n",
						route.get(0),
						total
						);
				okSoFar = false;
			}
		}
		//Check that we keep the customer satisfied
		//Check that every customer is visited and the correct amount is picked up
		Map<String,Integer> reqd = new HashMap<String,Integer>();
		for(Customer c:this.prob.customers){
			String address = String.format("%fx%f", c.x,c.y);
			reqd.put(address, c.c);
		}
		for(List<Customer> route:this.soln){
			for(Customer c:route){
				String address = String.format("%fx%f", c.x,c.y);
				if (reqd.containsKey(address))
					reqd.put(address, reqd.get(address)-c.c);
				else
					System.out.printf("********FAIL no customer at %s\n",address);
			}
		}
		for(String address:reqd.keySet())
			if (reqd.get(address)!=0){
				System.out.printf("********FAIL Customer at %s has %d left over\n",address,reqd.get(address));
				okSoFar = false;
			}
		return okSoFar;
	}

	public void readIn(String filename) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String s;
		this.soln = new ArrayList<List<Customer>>();
		while((s=br.readLine())!=null){
			ArrayList<Customer> route = new ArrayList<Customer>();
			String [] xycTriple = s.split(",");
			for(int i=0;i<xycTriple.length;i+=3)
				route.add(new Customer(
						(int)Double.parseDouble(xycTriple[i]),
						(int)Double.parseDouble(xycTriple[i+1]),
						(int)Double.parseDouble(xycTriple[i+2])));
			soln.add(route);
		}
		br.close();
	}

	public void writeSVG(String probFilename,String solnFilename) throws Exception{
		String[] colors = "chocolate cornflowerblue crimson cyan darkblue darkcyan darkgoldenrod".split(" ");
		int colIndex = 0;
		String hdr = 
				"<?xml version='1.0'?>\n"+
						"<!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' '../../svg11-flat.dtd'>\n"+
						"<svg width='8cm' height='8cm' viewBox='0 0 500 500' xmlns='http://www.w3.org/2000/svg' version='1.1'>\n";
		String ftr = "</svg>";
		StringBuffer psb = new StringBuffer();
		StringBuffer ssb = new StringBuffer();
		psb.append(hdr);
		ssb.append(hdr);
		for(List<Customer> route:this.soln){
			ssb.append(String.format("<path d='M%s %s ",this.prob.depot.x,this.prob.depot.y));
			for(Customer c:route)
				ssb.append(String.format("L%s %s",c.x,c.y));
			ssb.append(String.format("z' stroke='%s' fill='none' stroke-width='2'/>\n",
					colors[colIndex++ % colors.length]));
		}
		for(Customer c:this.prob.customers){
			String disk = String.format(
					"<g transform='translate(%.0f,%.0f)'>"+
							"<circle cx='0' cy='0' r='%d' fill='pink' stroke='black' stroke-width='1'/>" +
							"<text text-anchor='middle' y='5'>%d</text>"+
							"</g>\n", 
							c.x,c.y,10,c.c);
			psb.append(disk);
			ssb.append(disk);
		}
		String disk = String.format("<g transform='translate(%.0f,%.0f)'>"+
				"<circle cx='0' cy='0' r='%d' fill='pink' stroke='black' stroke-width='1'/>" +
				"<text text-anchor='middle' y='5'>%s</text>"+
				"</g>\n", this.prob.depot.x,this.prob.depot.y,20,"D");
		psb.append(disk);
		ssb.append(disk);
		psb.append(ftr);
		ssb.append(ftr);
		PrintStream ppw = new PrintStream(new FileOutputStream(probFilename));
		PrintStream spw = new PrintStream(new FileOutputStream(solnFilename));
		ppw.append(psb);
		spw.append(ssb);
		ppw.close();
		spw.close();
	}

	public void writeOut(String filename) throws Exception{
		PrintStream ps = new PrintStream(filename);
		for(List<Customer> route:this.soln){
			boolean firstOne = true;
			for(Customer c:route){
				if (!firstOne)
					ps.print(",");
				firstOne = false;
				ps.printf("%f,%f,%d",c.x,c.y,c.c);
			}
			ps.println();
		}
		ps.close();
	}
}

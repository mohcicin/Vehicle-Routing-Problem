package coursework;

import java.util.ArrayList;
import java.util.List;

public class FitnessCalc {

	static Customer depot;
	
	
	public static void setDepot(Customer dpt){
		depot = dpt;
	}
	
	
	static double getFitness(Individual individual){

		double fitness = 0;
		for(List<Customer>route:individual.getGenes()){
			Customer prev = depot;
			for (Customer c:route){
				fitness += prev.distance(c);
				prev = c;
			}
			//Add the cost of returning to the depot
			fitness += prev.distance(depot);
		}


		return fitness;
	}

}

package coursework;

import java.util.ArrayList;
import java.util.List;

/*
 * A chromosome represents a potential solution of the VRP. See Genetic algorithms
 * 
 * 
 */
public class Chromosome {
	
	private List<List<Customer>> genes = new ArrayList<List<Customer>>();

	// alter the genes to become this type of chromosome :[1,2,3],[4,5,6],[7,8,9]
	public void becomeBalancedChromosome(){
		
	}
	
	// alter the genes to become a random type of chromosome : [6],[3,7,4],[2,1],[9,8,5,10]
	public void becomeRandomChromosome(){
		
	}
	
	// a chromosome can alter its own structure
	public void mutate(){
		
	}

}

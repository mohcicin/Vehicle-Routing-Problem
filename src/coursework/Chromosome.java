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
	private List<List<Integer>> genesModel = new ArrayList<List<Integer>>();

	// alter the genes to become this type of chromosome :[1,2,3],[4,5,6],[7,8,9]
	public void becomeBalancedChromosome(){
		
	}
	
	// alter the genes to become a random type of chromosome : [6],[3,7,4],[2,1],[9,8,5,10]
	public void becomeRandomChromosome(){
		
	}
	
	// a chromosome can alter its own structure
	public void mutate(){
		
	}

	
	public List<List<Customer>> getGenes(){
		return this.genes;
	}
	
	public List<List<Integer>> getGenesModel(){
		updateGenesModel();
		return this.genesModel;
	}
	
	// re-establish the correspondance between the genes and the model (representation of the data to be used by the GA)
	private void updateGenesModel(){
		// find each customer id and put it at the corresponding spot
	}
	
}

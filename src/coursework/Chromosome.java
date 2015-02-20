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
	public void becomeBalancedChromosome(List<Customer> customers){
		
		ArrayList<List<Customer>> threeLists = new ArrayList<List<Customer>>();
		ArrayList<Customer> l1 = new ArrayList<Customer>();
		ArrayList<Customer> l2 = new ArrayList<Customer>();
		ArrayList<Customer> l3 = new ArrayList<Customer>();
		threeLists.add(l1);
		threeLists.add(l2);
		threeLists.add(l3);
		
		for(int i = 0 ; i < customers.size() ; i ++){
			for(List<Customer> li: threeLists){
				li.add(customers.get(i));
				if(li.size()>=customers.size()/3)
					break;
			}
		}
		if(threeLists.size() != customers.size())
			System.out.println("ERROR HERE");
		
	}
	
	// alter the genes to become a random type of chromosome : [6],[3,7,4],[2,1],[9,8,5,10]
	public void becomeRandomChromosome(int chromosomeLength){
		
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

package coursework;

import java.util.ArrayList;

/*
 * A chromosome represents a potential solution of the VRP. See Genetic algorithms
 * 
 * 
 */
public class Chromosome {
	
	private ArrayList<Route> genes;
	
	public Chromosome(){
		
	}
	
	public Chromosome(boolean balanced){
		if(balanced){ 
			// Create this type of chromosome :[1,2,3],[4,5,6],[7,8,9]
		} else{
			// Create a random type of chromosome : [6],[3,7,4],[2,1],[9,8,5,10]
		}
	}
	
	public List<List<int>> getChromosome(){
		
	}
	
	public setGenes

}

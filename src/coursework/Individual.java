package coursework; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {
	
	private List<ArrayList<Customer>> genes = new ArrayList<ArrayList<Customer>>();
	private int nbTruck;
	private double fitness = 0; // means: new individual

	// Why not simply using a constructor ? Because we're creating an individual as a random one
	public void generateIndividual(ArrayList<Customer> customers) {
		
		Random rand = new Random();
		this.nbTruck = rand.nextInt();
		
		ArrayList<ArrayList<Customer>> listOfLists = new ArrayList<ArrayList<Customer>>();
		for(int i = 0 ; i < nbTruck ; i++){
			listOfLists.add(new ArrayList<Customer>());
		} // we have our n sublist into the list, great.
		
		// we fill them up one by one with random indexes
		int indexLeft = 0;
		int indexRight = getRandomIndex( customers, nbTruck);
		int cntLoop = 0;
		for(ArrayList<Customer> currentList : listOfLists){
			cntLoop++;
			// add a random number of customers from the full list of "customers" to 
			for(int i = indexLeft ; i < indexRight ; i++){
				currentList.add(customers.get(i));
			}
			indexLeft = indexRight;
			indexRight = getRandomIndex(customers, nbTruck-cntLoop);
		}
		
		this.genes = listOfLists;
		
		
	}
	
	
	
	// REMINDER SUBLIST:
	// Sublist returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
	
	
	private static int getRandomIndex(List<Customer> list, int nbSubList){
		int max = list.size();
		int min = 
		Random rand = new Random();
		int randomIndex = rand.nextInt( (max - min) + 1 ) + min;
		return randomIndex;
		
	}
	
	
	public double getFitness(){
		if(fitness == 0){ // if this is a new individual
			fitness = FitnessCalc.getFitness(this); // recalculate its fitness
		}
		
		return fitness;
	}
	
	public List<ArrayList<Customer>> getGenes(){
		return this.genes;
	}
	
	public List<Customer> getGene(int index){
		return this.genes.get(index);
	}
	
	public void setGene(int index, ArrayList<Customer> genePart){
		this.genes.add(index, genePart);
		fitness = 0;
	}
	
	public int size (){
		return genes.size();
	}
	
	
	public String toString(){
		String geneString = "";
		for(int i = 0 ; i < size() ; i ++){
			geneString += this.genes.get(i).toString();
		}
		return geneString;
	}
	
}

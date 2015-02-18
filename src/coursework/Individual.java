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
		int remainingCustomer = customers.size();
		int lastIndex = 0;
		// generate the sub-lists (routes)
		for(int i = 0 ; i < nbTruck ; i++){
			ArrayList<Customer> tmpList = new ArrayList<Customer>();
			int lengthNextList = 0;
			if(i == nbTruck - 1){ // the last routes takes all remaining customers
				tmpList = (ArrayList<Customer>) customers.subList(0, remainingCustomer+1);
				this.genes.add(tmpList);
			}else{
				lengthNextList = rand.nextInt( (remainingCustomer - 2) + 2 ) + 2;// 1 < listLength < nbCustomers
				lastIndex = 
				tmpList =  customers.subList(lastIndex, lastIndex+lengthNextList);
				this.genes.add(tmpList);
				remainingCustomer -= lengthNextList;
			}
		}
		// other attempt
		this.nbTruck = rand.nextInt();
		
		ArrayList<ArrayList> listOfLists = new ArrayList<ArrayList>();
		for(int i = 0 ; i < nbTruck ; i++){
			listOfLists.add(new ArrayList<Customer>());
		}
		for(ArrayList<Customer> currentList : listOfLists){
			
			// add a random number of customers from the full list of "customers" to 
			
		}
		
		
	}
	
	
	
	// REMINDER SUBLIST:
	// Sublist returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
	
	
	private static ArrayList<Customer> extractCustomers(List source, int indexFrom, int indexTo){
		ArrayList<Customer> customers = (ArrayList<Customer>) source;
		
		customers = customers.subList(indexFrom, indexTo);
		
		return customers;
	}
	
	
	public double getFitness(){
		if(fitness == 0){ // if this is a new individual
			fitness = FitnessCalc.getFitness(this); // recalculate its fitness
		}
		
		return fitness;
	}
	
	public List<List<Customer>> getGenes(){
		return this.genes;
	}
	
	public List<Customer> getGene(int index){
		return this.genes.get(index);
	}
	
	public void setGene(int index, List<Customer> genePart){
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

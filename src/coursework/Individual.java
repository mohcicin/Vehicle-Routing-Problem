package coursework;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Individual {
	
	private List<List<Customer>> genes = new ArrayList<List<Customer>>();
	
	private double fitness = 0; // means: new individual

	// Why not simply using a constructor ? Because we're creating an individual as a random one
	public void generateIndividual(ArrayList<Customer> customers) {
		// out of all the customers, we create a random list and add it to the list of list of Customer called "genes"
		for(int i = 0; i < size(); i++ ){
			ArrayList<Customer> gene = customers;
			Collections.shuffle(gene);
			this.genes.add(gene);
		}
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

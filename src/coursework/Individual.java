package coursework; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {
	
	private  genes;
	private int nbTruck;
	private double fitness = 0; // means: new individual
	private static Random rand = new Random();
	// Why not simply using a constructor ? Because we're creating an individual as a random one
	public void generateIndividual(ArrayList<Customer> customers) {
		
		this.genes = new ArrayList<List<Customer>>();
		this.nbTruck = (int) customers.size() / 3;
		
		
	}
	
	
	private static int getRandomIndex(List<Customer> list, int nbSubList){
		int max = list.size() - nbSubList;
		int randomIndex;
		int min = 1;
		randomIndex = rand.nextInt( (max - min) + 1 ) + min;
		return randomIndex;
		
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
	
	public void setGene(int index, List<Customer> list){
		this.genes.add(index, list);
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

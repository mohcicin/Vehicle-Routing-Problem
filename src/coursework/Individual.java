package coursework; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {
	
	private Chromosome chromosome;
	private double fitness = 0; // means: new individual
	private static Random rand;
	
	// Why not simply using a constructor ? Because we're creating an individual as a random one
	public void generateIndividual( List<Customer> customers) {
		this.chromosome = new Chromosome();
		this.chromosome.becomeBalancedChromosome(customers); // we're not seeding yet.
		this.rand = new Random();
		
	}
	
	public void mutate(){
		this.chromosome.mutate();
	}
	
	
	private static int getRandomIndex(List<Customer> list, int nbSubList){
		int max = list.size() - nbSubList;
		int randomIndex;
		int min = 1;
		randomIndex = rand.nextInt( (max - min) + 1 ) + min;
		return randomIndex;
		
	}
	
	
	public double getFitness(){
		if(this.fitness == 0){ // if this is a new individual
			this.fitness = FitnessCalc.getFitness(this); // recalculate its fitness
		}
		
		return this.fitness;
	}
	
	public List<List<Customer>> getGenes(){
		return this.chromosome.getGenes();
	}
	
	public void setGene(int index, List<Customer> list){
		this.chromosome.add(index, list);
		this.fitness = 0;
	}
	
	public int size (){
		return chromosome.getGenes().size();
	}
	
	
	public String toString(){
		String geneString = "";
		for(int i = 0 ; i < size() ; i ++){
			geneString += this.chromosome.getGenes().get(i).toString();
		}
		return geneString;
	}
	
}

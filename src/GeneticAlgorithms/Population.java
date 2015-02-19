package GeneticAlgorithms;

import java.util.List;

public class Population {
	
	private Individual[] individuals; // A population is a group of individuals
	
	// create it
	public Population (int populationSize, boolean initialise){
		this.individuals = new Individual[populationSize];
		if(initialise){
			// create each individual of the population
			for(int i = 0; i < size(); i++){ // always use a size method, it's cleaner.
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual(); // creates a RANDOM individual
				saveIndividual(i, newIndividual);	// add it to the population
			}
		}
		
	}
	
	public Individual getIndividual(int index){
		return this.individuals[index];
	}
	
	public void saveIndividual(int index, Individual indiv){
		this.individuals[index] = indiv;
	}
	
	public Individual getFittest(){
		Individual fittest = this.individuals[0];
		
		for(int i = 0 ; i < size(); i++){
			if(fittest.getFitness() < getIndividual(i).getFitness()){
				fittest = this.individuals[i];
			}
		}
		
		return fittest;
	}
	
	public int size(){
		return this.individuals.length;
	}

}

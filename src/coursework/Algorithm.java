package coursework;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {

	// Genetic Algorithms settings
	private static final double uniformRate = 0.5;
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static ArrayList<Customer> customers; // needed to create a Population
	

	public static Population evolvePopulation( ArrayList<Customer> cust, Population pop){
		customers = cust;
		Population newPopulation = new Population( cust, pop.size(), false); // false: must not alter the population

		// keep the best individual
		newPopulation.saveIndividual(0, pop.getFittest());
		
		
		// crossover
		for(int i = 0 ; i < pop.size() ; i++){
			Individual indiv1 = tournamentSelection(pop); // 
			Individual indiv2 = tournamentSelection(pop); // get two different fittest individuals out of 5 random individuals
			Individual newIndiv = crossover( indiv1, indiv2); // randomly combine their genes to get a new solution
			newPopulation.saveIndividual( i, newIndiv); // save the new individual in the new population
		}

		// mutation
		for(int i = 1 ; i < newPopulation.size() ; i++){
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}
	
	// selects the fittest individual of a random selection of individuals in the population
	private static Individual tournamentSelection(Population pop){
		Population tournamentPop = new Population(customers , tournamentSize, false);
		for(int i=0 ; i < tournamentSize ; i++ ){
			int randomId = (int) (Math.random() * pop.size());
			tournamentPop.saveIndividual(i, pop.getIndividual(randomId));
			
		}
		return tournamentPop.getFittest();
		
	}
	
	private static Individual crossover( Individual indiv1, Individual indiv2){
		
	}
	
	private static void mutate (Individual indiv){
		if(Math.random() > mutationRate) // not sure
			indiv.mutate();
	}
	
}

package GeneticAlgorithms;

public class Algorithm {

	// Genetic Algorithms settings
	private static final double uniformRate = 0.5;
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;
	

	public static Population evolvePopulation(Population pop){
		Population newPopulation = new Population( pop.size(), false); // false: must not alter the population

		// keep the best individual
		if(elitism){
			newPopulation.saveIndividual(0, pop.getFittest());
		}
		
		
		int elitismOffset;
		if(elitism){ // if we're elitist we already have an individual in the list, the other must be added after him
			elitismOffset = 1;
		} else{
			elitismOffset = 0;
		}
		
		// crossover (or "recombination")
		for(int i = 0 ; i < pop.size() ; i++){
			Individual indiv1 = tournamentSelection(pop); // 
			Individual indiv2 = tournamentSelection(pop); // get two different fittest individuals out of 5 random individuals
			Individual newIndiv = crossover( indiv1, indiv2); // randomly combine their genes to get a new solution
			newPopulation.saveIndividual( i, newIndiv); // save the new individual in the new population
		}

		// mutate the population
		for(int i = elitismOffset ; i < newPopulation.size() ; i++){
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}
	
	// selects the fittest individual of a random selection of individuals in the population
	private static Individual tournamentSelection(Population pop){
		Population tournament = new Population ( tournamentSize, false); // tournamentSize ?
		
		for(int i = 0 ; i < tournamentSize ; i++ ){
			int randomId = (int) (Math.random() * pop.size());
			tournament.saveIndividual(i, pop.getIndividual(randomId));
		}
		
		Individual fittest = tournament.getFittest();
		return fittest;
	}
	
	private static Individual crossover( Individual indiv1, Individual indiv2){
		Individual newSol = new Individual();
		
		for(int i = 0 ; i < indiv1.size() ; i++){
			// crossover
			if(Math.random() <= uniformRate){ // 1 out of 2 chances that the son'll take indiv1's gene[i]
				newSol.setGene(i, indiv1.getGene(i));
			}else{ // else he'll take indiv2's gene[i]
				newSol.setGene(i, indiv2.getGene(i));
			}
		}
		
		
		return newSol;
	}
	
	private static void mutate (Individual indiv){
		for(int i = 0 ; i < indiv.size() ; i++ ){
			if(Math.random() <= mutationRate ){ // 1.5% probability for an individual to mutate
				byte gene = (byte) Math.round(Math.random());
				indiv.setGene(i, gene);
			}
		}
	}
	
}

package GeneticAlgorithms;

public class GeneticA {
	
	public static void main(String[] args) {
		
		//--------------
		// GA Skeleton
		//--------------
		
		// candidate solution
		//FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
		
		// create the population
		Population myPop = new Population(50, true); // init with random values
		
		int generationCounter = 0;

		while(myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness() ){ // satisfaction criteria
			generationCounter++;
			System.out.println("Generation: "+generationCounter + "\tFittest solution: "+ myPop.getFittest() + "\t Fit: " + myPop.getFittest().getFitness());
			myPop = Algorithm.evolvePopulation(myPop);
		}
		generationCounter++;
		System.out.println("Solution found !");
		System.out.println("Generation:" + generationCounter);
		System.out.println("Generation: "+generationCounter + "\tFittest solution: "+ myPop.getFittest() + "\t Fit: " + myPop.getFittest().getFitness());
		
		
	}

}

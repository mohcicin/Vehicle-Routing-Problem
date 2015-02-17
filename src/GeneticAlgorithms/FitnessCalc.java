package GeneticAlgorithms;

public class FitnessCalc {

	static byte[] solution = new byte[64];

	// in this example we know the actual solution from the beginning
	public static void setsolution(byte[] newSolution){
		solution = newSolution;
	}

	// make it easier for us to add the solution
	public static void setSolution(String newSolution){
		solution = new byte[newSolution.length()];
		// Loop through each character of our string and save it in our byte 
		// array
		for (int i = 0; i < newSolution.length(); i++) {
			String character = newSolution.substring(i, i + 1);
			if (character.contains("0") || character.contains("1")) {
				solution[i] = Byte.parseByte(character);
			} else {
				solution[i] = 0;
			}
		}
	}
	
	static int getFitness(Individual individual){
		int fitness = 0;
		for(int i = 0 ; i < individual.size() && i < solution.length ; i++){
			if(individual.getGene(i) == solution[i]){ // if the individual's genome looks like the solutions genome
				fitness++;
			}
		}
		return fitness;
	}
	
	static int getMaxFitness(){
		int maxFitness = solution.length;
		return maxFitness;
	}

}

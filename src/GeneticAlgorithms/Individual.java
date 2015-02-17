package GeneticAlgorithms;

public class Individual {
	
	static int defaultGeneLength = 64;// default size
	
	private byte[] genes = new byte[defaultGeneLength];
	
	private int fitness = 0; // means: new individual

	// Why not simply using a constructor ? Because we're creating an individual as a random one
	public void generateIndividual() {
		for(int i = 0; i < size(); i++ ){
			byte gene = (byte) Math.round( Math.random() );
			this.genes[i] = gene;
		}
	}
	
	public static void setDefaultGeneLength (int s){
		defaultGeneLength = s;
	}
	
	public int getFitness(){
		if(fitness == 0){ // if this is a new individual
			fitness = FitnessCalc.getFitness(this); // recalculate its fitness
		}
		
		return fitness;
	}
	
	public byte getGene(int index){
		return this.genes[index];
	}
	
	public void setGene(int index, byte genePart){
		this.genes[index] = genePart;
		fitness = 0;
	}
	
	public int size (){
		return genes.length;
	}
	
	
	public String toString(){
		String geneString = "";
		for(int i = 0 ; i < size() ; i ++){
			geneString += this.genes[i];
		}
		return geneString;
	}
	
}

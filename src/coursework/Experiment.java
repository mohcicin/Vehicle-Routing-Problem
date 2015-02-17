package coursework;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.*;
public class Experiment {

	public static void main(String[] args)throws Exception{
		/*
		String [] probs = { // 10 random situations 
				"data/rand00100",
				"data/rand00200","data/rand00300",
				"data/rand00400","data/rand00500",
				"data/rand00600","data/rand00700",
				"data/rand00800","data/rand00900",
				"data/rand01000"
				};
		*/
		String [] probs = {
				"data/rand00010", // from here
				"data/rand00020", //
				"data/rand00030", //
				"data/rand00040", //
				"data/rand00050", // The original clarke wright are available in csv files
				"data/rand00060", //
				"data/rand00070", //
				"data/rand00080", //
				"data/rand00090"/*, // to here
				"data/rand00100",
				"data/rand00200",
				"data/rand00300",
				"data/rand00400",
				"data/rand00500",
				"data/rand00600",
				"data/rand00700",
				"data/rand00800",
				"data/rand00900",
				"data/rand01000"*/
				};
		
		PrintStream psRT = new PrintStream("runtimes.csv");
		PrintStream psCosts = new PrintStream("costs.csv");
		// number of iterations >= 10
		int nbRun = 50;
		
		long avrgTimeCW=0;
		long avrgTimeMyCW=0;
		long avrgTimeWCS=0;
		double avrgCostCW=0;
		double avrgCostMyCW=0;
		double avrgCostWCS=0;
		double singleCost=0;
		long singleRuntime=0;
		long start=0;
		
		// Title csv RUNTIME file
		psRT.printf("VRP,CW,OurCW,WCS");
		psRT.println();
		// Title csv COST file
		psCosts.printf("VRP,CW,OurCW,WCS");
		psCosts.println();
		
		for (String f:probs){
			// reset
			avrgTimeCW=0;
			avrgTimeMyCW=0;
			avrgTimeWCS=0;
			avrgCostCW=0;
			avrgCostMyCW=0;
			avrgCostWCS=0;
			start=0;
			
			
			for(int i=0 ; i<nbRun; i++){ // 
				VRProblem vrp = new VRProblem(f+"prob.csv");
				VRSolution vrs = new VRSolution(vrp);
				
				start = System.nanoTime();
				vrs.clarkeWrightAlgorithm();
				avrgTimeMyCW+=System.nanoTime()- start;
				avrgCostMyCW+=vrs.solnCost();
			}
			avrgTimeMyCW/=nbRun;
			avrgCostMyCW/=nbRun;
			
			/*--- WORST CASE SCENARIO ---*/
			for(int i=0 ; i<nbRun; i++){
				VRProblem vrp = new VRProblem(f+"prob.csv");
				VRSolution vrs = new VRSolution(vrp);
				
				start = System.nanoTime();
				vrs.oneRoutePerCustomerSolution();
				singleRuntime = System.nanoTime()- start;
				avrgTimeWCS+=singleRuntime;
				avrgCostWCS+=vrs.solnCost();
			}
			avrgTimeWCS/=nbRun;
			avrgCostWCS/=nbRun;
			
			/*--- ORIGINAL CLARKE WRIGHT ---*/
			for(int i=0 ; i<nbRun; i++){
				VRProblem vrp = new VRProblem(f+"prob.csv");
				VRSolution vrs = new VRSolution(vrp);
				
				start = System.nanoTime();
				vrs.readIn(f+"cwsn.csv");
				avrgTimeCW+=System.nanoTime()- start;
				avrgCostCW+=vrs.solnCost();
			}
			avrgTimeCW/=nbRun; // run time values are irrelevant
			avrgCostCW/=nbRun;
			
			
			
			psRT.printf("%s,%d,%d,%d", f,avrgTimeCW,avrgTimeMyCW, avrgTimeWCS);
			psRT.println();
			
			psCosts.printf("%s,%f,%f,%f", f,avrgCostCW,avrgCostMyCW, avrgCostWCS);
			psCosts.println();
			
			
			System.out.println("SITUATION :"+f+"\t---CW--- RUNTIME(ns) :"+avrgTimeCW+"\tCOST :"+avrgCostCW+"\t---MY CW--- RUNTIME(ns) :"+avrgTimeMyCW+"\tCOST :"+avrgCostMyCW+"\t---WCS--- :RUNTIME(ns) :"+avrgTimeWCS+"COST"+avrgCostWCS);
		}
		psRT.close();
		psCosts.close();
		System.out.println("RESULTS WRITTEN IN : runtimes.csv AND costs.csv");
		System.out.println("EXPERIMENT FINISHED");
	}

	
	
}

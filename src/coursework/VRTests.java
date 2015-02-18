package coursework;

import java.util.ArrayList;
import java.io.*;

public class VRTests {

	public static void main(String[] args)throws Exception {
		String [] shouldPass = {
				"data/rand00010"/*,
				"data/rand00020",
				"data/rand00030",
				"data/rand00040",
				"data/rand00050",
				"data/rand00060",
				"data/rand00070",
				"data/rand00080",
				"data/rand00090",
				"data/rand00100",
				"data/rand00200",
				"data/rand00300",
				"data/rand00400",
				"data/rand00500",
				"data/rand00600",
				"data/rand00700",
				"data/rand00800",
				"data/rand00900",
				"data/rand01000"
				*/};
		/*
		String [] shouldFail = {
				"data/fail00002",
				"data/fail00004"
				};
		*/
				
		System.out.println("\nShould Pass");
		System.out.println("Problem     \tSoln\tSize\tCost\tValid");
		for (String base:shouldPass){
			VRProblem vrp = new VRProblem(base+"prob.csv");
			VRSolution vrs = new VRSolution(vrp);
			
			vrs.evolutionarySolver();
			System.out.printf("%s\t%s\t%d\t%.0f\t%s\n",base,"GA",vrp.size(),vrs.solnCost(),vrs.verify());
			
			vrs.writeSVG(base+"prob.svg",base+"GA.svg");
			if (new File(base+"cwsn.csv").exists()){
				vrs.readIn(base+"cwsn.csv");

				//Print out results of costing and verifying the solution
				System.out.printf("%s\t%s\t%d\t%.0f\t%s\n",base,"CW",vrp.size(),vrs.solnCost(),vrs.verify());
				
				//Write the SVG file
				vrs.writeSVG(base+"prob.svg",base+"cwsn.svg");
			}
		}
		/*
		System.out.println("\nShould Fail");
		System.out.println("Problem\tSolution\tSize\tCost\tValid");
		
		for (String b:shouldFail){
			VRProblem vrp = new VRProblem(b+"prob.csv");
			VRSolution vrs = new VRSolution(vrp);
			if (new File(b+"soln.csv").exists()){

				//Read an existing solution file
				vrs.readIn(b+"soln.csv");

				//Print out results of costing and verifying the solution
				System.out.printf("%s\t%s\t%d\t%.0f\t%s\n",b,b,vrp.size(),vrs.solnCost(),vrs.verify());
			
				//Write the SVG file
				vrs.writeSVG(b+"prob.svg", b+"soln.svg");
			}
		}
		*/
		
	}
}

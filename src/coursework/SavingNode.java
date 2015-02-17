package coursework;

public class SavingNode implements Comparable{

	public Customer to;
	public Customer from;
	public double saving;
	
	public SavingNode(Customer c1, Customer c2, double s) {
		
		this.from =  c1;
		this.to =  c2;
		this.saving =  s;
	}

	// higher savings first
	@Override
	public int compareTo(Object o) {
		SavingNode sn = (SavingNode) o;
		  
		  if(sn.saving > this.saving){
			  return 1;
		  }else if(sn.saving < this.saving){
			  return -1;
		  }else
			  return 0;
	}
	
}

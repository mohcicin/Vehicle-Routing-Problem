package coursework;

import java.awt.geom.Point2D;

//import com.sun.org.glassfish.external.probe.provider.annotations.Probe;


public class Customer extends Point2D.Double {

	private int id;
	// Requirements of the customer
	public int c;
	private boolean isInRoute;

	public Customer(int x, int y, int requirement){
		this.id = 0; // default for unassigned IDs
		this.x = x;
		this.y = y;
		this.c = requirement;
		this.isInRoute = false;
	}
	
	public boolean isInRoute() {
		return this.isInRoute;
	}
	public void setInRoute(boolean isInRoute) {
		this.isInRoute = isInRoute;
	}
	
}

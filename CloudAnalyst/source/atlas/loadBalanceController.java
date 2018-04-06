package atlas;

import java.util.Iterator;

import cloudsim.ext.Simulation;
import cloudsim.ext.gui.DataCenterUIElement;
import cloudsim.ext.gui.UserBaseUIElement;
import cloudsim.ext.util.ObservableList;

//This class will use information from the traffic monitor and the thresholds
//from the traffic definition to adapt the best load balancing property on the fly
public class loadBalanceController {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Simulation sim = new Simulation(null);
		//sim.testClassLoading(); <======copied this over from simulation.java but I get an error?
	// create traffic monitor for each datacenter... how do we get how many datacenters there are?
	ObservableList<DataCenterUIElement> dataCenters = sim.getDataCenters();
	ObservableList<UserBaseUIElement> userBases = sim.getUserBases(); 

	TrafficMonitor [] tm = new TrafficMonitor[dataCenters.size()];
	UserMonitor [] um = new UserMonitor[userBases.size()];
	
	int counter1 =0;
	for(Iterator<DataCenterUIElement> dc = dataCenters.iterator(); dc.hasNext();){
		DataCenterUIElement elem = dc.next();
		tm[counter1++]= new TrafficMonitor(elem);
		dc.remove();
	}
	
	int counter2=0;
	for(Iterator<UserBaseUIElement> ub = userBases.iterator(); ub.hasNext();){
		UserBaseUIElement elem = ub.next();
		um[counter2++]= new UserMonitor(elem);
		ub.remove();
	}
	
	
	//get the region of the dataCenter & find the userBases in that region
	for(int i=0; i < tm.length; i++) {
		
		for(int j=0; j < um.length; j++) {
			
			if(tm[i].getRegion() == um[j].getRegion()) {
				//add the user to the datacenter 
			}

		}
	}
	
	//for each user in a datacenter, add up _________ & pass the threshold to traffic definition
	//traffic definition will then switch if required
	
	
	// get the data from the monitor about each user base 
	
	
	
	// decide based on the traffic definition which load balancer to use
	
	/* description of load balancers in cloudAnalyst
	 * 
	 * Round Robin - tasks assigned to each process in equal portions and in circular order
	 * Throttled -  find an appropriate not allocated VM for user request
	 * ActiveVM - balances the tasks among available VM's in a way to even out the number of 
	 * active tasks at any given time on each VM
	 * 
	 */
	}

}

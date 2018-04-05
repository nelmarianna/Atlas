package atlas;

import java.util.Iterator;

import cloudsim.ext.Simulation;
import cloudsim.ext.gui.DataCenterUIElement;
import cloudsim.ext.util.ObservableList;

//This class will use information from the traffic monitor and the thresholds
//from the traffic definition to adapt the best load balancing property on the fly
public class loadBalanceController {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Simulation sim = new Simulation(null);
		//sim.testClassLoading(); <======copied this over but I get an error?
	// create traffic monitor for each datacenter... how do we get how many datacenters there are?
	ObservableList<DataCenterUIElement> dataCenters = sim.getDataCenters();
	
	TrafficMonitor [] tm = new TrafficMonitor[dataCenters.size()];
	int i =0;
	for(Iterator<DataCenterUIElement> dc = dataCenters.iterator(); dc.hasNext();){
		DataCenterUIElement elem = dc.next();
		tm[i++]= new TrafficMonitor(elem);
		dc.remove();
	}
	
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

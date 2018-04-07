package atlas;

import java.util.Iterator;
import java.util.List;

import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
import cloudsim.ext.datacenter.DatacenterController;
import cloudsim.ext.gui.DataCenterUIElement;
import cloudsim.ext.gui.UserBaseUIElement;
import cloudsim.ext.util.ObservableList;
import eduni.simjava.Sim_system;
import gridsim.GridSim;

//This class will use information from the traffic monitor and the thresholds
//from the traffic definition to adapt the best load balancing property on the fly
public class loadBalanceController {

	Simulation sim = null;
	double currTime;
	
	public loadBalanceController(Simulation simulation) {
		// TODO Auto-generated method stub

		
		while(Sim_system.running()) {
		
			 sim = simulation;
			 currTime = GridSim.clock();
			 
			//sim.testClassLoading(); <======copied this over from simulation.java but I get an error?
		// create traffic monitor for each datacenter... how do we get how many datacenters there are?
		
			 //ObservableList<DataCenterUIElement> dataCenters = sim.getDataCenters();
		List<DatacenterController> dataCenters = sim.getDataControllers();
		ObservableList<UserBaseUIElement> userBases = sim.getUserBases(); 
	
		TrafficMonitor [] tm = new TrafficMonitor[dataCenters.size()];
		UserMonitor [] um = new UserMonitor[userBases.size()];
		
		int counter1 =0;
		for(Iterator<DatacenterController> dc = dataCenters.iterator(); dc.hasNext();){
			DatacenterController elem = dc.next();
			tm[counter1++]= new TrafficMonitor(elem, sim);
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
					tm[i].addUser(um[j]);
				}
	
			}
		}
		
		
		//test console
		System.out.println("internet delay test ");//+ tm[0].getRegion());
		
		//for each user in a datacenter, add up INTERNETDELAY? & pass the threshold to traffic definition
		//traffic definition will then switch if required
		
		
		
		// get the data from the monitor about each user base 
	
	
	}

	}

}

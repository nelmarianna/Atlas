package atlas;

import java.util.*;

import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
// this class will monitor interactions between VM instances at single 
//data centers to find out what the distribution of users is
import cloudsim.ext.gui.DataCenterUIElement;



public class TrafficMonitor {

	private int region;
	private InternetCharacteristics internetChar;
	private List<UserMonitor> users;
	
	public TrafficMonitor(DataCenterUIElement dataCenter, Simulation sim) {
		// general things we can use:
		this.region = dataCenter.getRegion();
		
		Internet internet = sim.getInternet();
		internetChar = internet.getInternetChar();
		//internetChar.getTotalDelay(region, dest, reqSize);
		
		
	}

	
	public int getRegion(){
		return region;
	}
	
	public void addUser(UserMonitor user){
		users.add(user);
	}

}

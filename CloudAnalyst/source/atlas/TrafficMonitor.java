package atlas;

import java.util.*;

import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
// this class will monitor interactions between VM instances at single 
//data centers to find out what the distribution of users is
import cloudsim.ext.gui.DataCenterUIElement;



public class TrafficMonitor {

	protected int region;
	
	public TrafficMonitor(DataCenterUIElement dataCenter, Simulation sim) {
		// general things we can use:
		this.region = dataCenter.getRegion();
		
		Internet internet = sim.getInternet();
		InternetCharacteristics internetChar = internet.getInternetChar();
		//internetChar.getTotalDelay(region, dest, reqSize);
		
		
	}

	
	public int getRegion(){
		return region;
	}

}

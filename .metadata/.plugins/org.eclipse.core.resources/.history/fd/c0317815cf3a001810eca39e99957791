package atlas;

import java.util.*;

import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
import cloudsim.ext.datacenter.DatacenterController;
// this class will monitor interactions between VM instances at single 
//data centers to find out what the distribution of users is
import cloudsim.ext.gui.DataCenterUIElement;
import cloudsim.ext.gui.UserBaseUIElement;
import cloudsim.ext.stat.HourlyStat;



public class TrafficMonitor {

	private int region;
	private String name;
	private InternetCharacteristics internetChar;
	private List<UserMonitor> users;
	DatacenterController dataCenter;
	
	//create the traffic monitor for the datacenter
	public TrafficMonitor(DatacenterController dataCenter, Simulation sim) {
		// general things we can use:
		this.region = dataCenter.getRegion();
		this.name = dataCenter.getName();
		this.dataCenter = dataCenter;
		Internet internet = sim.getInternet();
		internetChar = internet.getInternetChar();
		//
		
	}

	//return the region of the datacenter
	public int getRegion(){
		return region;
	}
	//add users using this datacenter
	public void addUser(UserMonitor user){
		users.add(user);
	}
	//remove list of users
	public void removeAll(){
		for(Iterator<UserMonitor> um = users.iterator(); um.hasNext();){
			UserMonitor elem = um.next();
			um.remove();
		}
	}
	
	private double getAverageDelays(){
		double aveDelay = 0;
		for(Iterator<UserMonitor> um = users.iterator(); um.hasNext();){
			UserMonitor elem = um.next();
			aveDelay += internetChar.getTotalDelay(name, elem.getName(), elem.getReqSize());
		}
		return aveDelay/users.size();
	}
	
	// getListofRequests
	// match requests to userbases?
	public void getUpdates(){
		
		HourlyStat ProcessingTimes = dataCenter.getHourlyProcessingTimes();
		Map<Integer,Integer> vmStats = dataCenter.getVmAllocationStats();
		int requestsProcessed = dataCenter.getAllRequestsProcessed();
		int requestsMade = dataCenter.getAllRequestsReceived();
		
	}
	

}

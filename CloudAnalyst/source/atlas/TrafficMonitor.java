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
import cloudsim.ext.util.CommPath;



public class TrafficMonitor {

	private int region;
	private String name;
	private List<UserMonitor> users;
	DatacenterController dataCenter;
	int allReqProcessed = 0;
	int allRequestsReceived = 0;
	HourlyStat hourlyProcessingTimes;
	Map<Integer, Integer> vmAllocationStats;
	
	//create the traffic monitor for the datacenter
	public TrafficMonitor(DatacenterController dataCenter, Simulation sim) {
		// general things we can use:
		this.region = dataCenter.getRegion();
		this.name = dataCenter.getDataCenterName();
		this.dataCenter = dataCenter;
		
		//
	
		users = new LinkedList<UserMonitor>();
	}

	public String getName() {
		return name;
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
		int counter2 = 0;
		for(UserMonitor um: users){
			users.remove(um);
		}
	}
	
	public void update(){
		allReqProcessed = dataCenter.getAllRequestsProcessed();
		allRequestsReceived = dataCenter.getAllRequestsReceived();
		hourlyProcessingTimes = dataCenter.getHourlyProcessingTimes();
		vmAllocationStats = dataCenter.getVmAllocationStats();
	}
	
	public int getRequestsProcessed(){
		return allReqProcessed;
	}

	public int getRequestsMade(){
		return allRequestsReceived;
	}

	public List[] getData() {
		return hourlyProcessingTimes.getStat().get_data();
	}
	
	public Map<Integer,Integer> getVmStats(){
		return vmAllocationStats;
	}

	public DatacenterController getDataCenter() {
		return dataCenter;
	}
}

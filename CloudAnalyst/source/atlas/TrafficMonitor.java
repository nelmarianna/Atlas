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
	
//	public double getAverageDelays(){
//		double aveDelay = 0;
//		for(Iterator<UserMonitor> um = users.iterator(); um.hasNext();){
//			UserMonitor elem = um.next();
//			aveDelay += internetChar.getTotalDelay(name, elem.getName(), elem.getReqSize());
//		}
//		return aveDelay/users.size();
//	}
	
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
	
	public int getHourlyProcessingTimes(){
		return hourlyProcessingTimes.getStat().UTILISATION;

	}
	
	public List[] getData() {
		return hourlyProcessingTimes.getStat().get_data();
	}
	
	public Map<Integer,Integer> getVmStats(){
		return vmAllocationStats;
	}
	
	
//	public double getDelay(){
//		Map<String, Double[]> saving = internetChar.getServiceLatencies();
//		Double[] delay = saving.get(name);
//		if(delay[0]!=null){
//			return delay[0];
//		}
//		else{
//			return 0.00;
//		}
//	}
	
	
	

}

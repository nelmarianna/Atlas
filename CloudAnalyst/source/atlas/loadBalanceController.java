package atlas;

import java.util.Iterator;
import java.util.List;

import cloudsim.CloudSim;
import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
import cloudsim.ext.UserBase;
import cloudsim.ext.datacenter.DatacenterController;
import cloudsim.ext.util.ObservableList;
import eduni.simjava.Sim_system;
import gridsim.GridSim;

//This class will use information from the traffic monitor and the thresholds
//from the traffic definition to adapt the best load balancing property on the fly
public class loadBalanceController extends CloudSim{

	boolean cancelled = false;
	Simulation sim = null;
	double currTime;
	double simTime;
	TrafficDefinition trafficDefinition = new TrafficDefinition();
	
	public loadBalanceController(Simulation simulation) throws Exception {
		// TODO Auto-generated method stub
		super("load");
		System.out.println("internet delay test 1 ");
		
		
				 sim = simulation;
				simTime =  sim.getSimulationTime();
			//	 currTime = GridSim.clock();

	}

	public synchronized void cancelRun(){
		cancelled = true;
	}
	 public void body() {
		 //if curr time- last time checked == 2 hours do this stuff:
	//	 while(Sim_system.running()&& !cancelled) {
	// System.out.println("lmao"+ sim.getSimulationTime());
		 	currTime = GridSim.clock();
			while(currTime < simTime && Sim_system.running()) {
				
				
				currTime = GridSim.clock();
				System.out.println("PAUSE STARTINGGGGGGGGGGGGGGGGGGGG @"+ currTime);
				//pause every 2 hours
				sim_pause(7200000);
				currTime = GridSim.clock();
				System.out.println("PAUSE DONEEEEEEEEEEEEEEEEEEEEEEEEE @"+ currTime);
				
				
			 System.out.println("internet delay test 2");
				//sim.testClassLoading(); <======copied this over from simulation.java but I get an error?
			// create traffic monitor for each datacenter... how do we get how many datacenters there are?
			
				 //ObservableList<DataCenterUIElement> dataCenters = sim.getDataCenters();
			List<DatacenterController> dataCenters = sim.getDataControllers();
			List<UserBase> userBases = sim.getUserBase(); 
		
			TrafficMonitor [] tm = new TrafficMonitor[dataCenters.size()];
			UserMonitor [] um = new UserMonitor[userBases.size()];
			
			int counter1 =0;
			for(DatacenterController datacenterController: dataCenters){
				tm[counter1++]= new TrafficMonitor(datacenterController, sim);
			}
			
			int counter2=0;
			for(UserBase userBase: userBases){
				um[counter2++]= new UserMonitor(userBase);
			}
			
			
			//get the region of the dataCenter & find the userBases in that region
		//	for(int i=0; i < tm.length; i++) {
				
				for(int j=0; j < um.length; j++) {
					
					//if(tm[i].getRegion() == um[j].getRegion()) {
						System.out.println("OMGERD  VM ID =  "+ um[j].getInfo());
					//}
		
				}
		//	}
			
			//get response times per vm (how many requests were processed(20%), 
			//what the delay was(50%), request time(30%))
			//THEN do load balancing (thresholds)
			
			//test console
			System.out.println("internet delay test 3");//+ tm[0].getRegion());
			
			//for each user in a datacenter, add up INTERNETDELAY? & pass the threshold to traffic definition
			//traffic definition will then switch if required
			for(TrafficMonitor monitor : tm ){
				monitor.update();
			}

                for (int i = 0; i < tm.length; i++) {
                    System.out.println("----------------------------------");
                    System.out.println(tm[i].getName());
                    System.out.println(tm[i].getRequestsMade());
                    System.out.println(tm[i].getRequestsProcessed());
                    System.out.println("Vm allocation stats:");
                    if(tm[i].getVmStats() != null){
                        for(Integer val: tm[i].getVmStats().values()){
                            System.out.println(val);
                        }
                    }
                    System.out.println("Vm usage stats:");
                    System.out.println(tm[i].getQueueSize());
                    System.out.println("---------------------------------");
                    trafficDefinition.compareThreshold(tm[i].getDataCenter(), (100*tm[i].getRequestsProcessed())/ 70);
                    System.out.println(tm[i].getDataCenter().getLoadPolicy());
                }
			
			
			currTime = GridSim.clock();

		}
		 
	 }

}

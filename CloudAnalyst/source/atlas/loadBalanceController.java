package atlas;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cloudsim.CloudSim;
import cloudsim.Cloudlet;
import cloudsim.CloudletList;
import cloudsim.VirtualMachine;
import cloudsim.VirtualMachineList;
import cloudsim.ext.Internet;
import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.Simulation;
import cloudsim.ext.UserBase;
import cloudsim.ext.datacenter.DatacenterController;
import cloudsim.ext.util.CommPath;
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
	InternetCharacteristics internetChar;
	Internet internet;
	
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
				//sim_pause(7200000);
				sim_pause(14400000);
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
				tm[counter1]= new TrafficMonitor(datacenterController, sim);
				counter1++;
			}
			
			int counter2=0;
			for(UserBase userBase: userBases){
				um[counter2]= new UserMonitor(userBase);
				counter2++;
		//		System.out.println("LMAOOOO");
			}
			
			int dcID = dataCenters.get(0).get_id();
			VirtualMachineList hi = dataCenters.get(0).getVmList();
			VirtualMachine lmao = (VirtualMachine) hi.getLast();
			System.out.println("VM SCHADOOLOEEE DO STUFFFFF vmID  " + lmao.getVmId() +"  dcID" +dcID+"  same?"+lmao.getUserId());
			
			
			
			CloudletList omg = dataCenters.get(0).getCloudletList();
			//Cloudlet lmfao = (Cloudlet) omg.getFirst();
			//System.out.println("ROFLL  "+lmfao.getCloudletId() + "  " + lmfao.getGridletID()+ "  start "+lmfao.getSubmissionTime() + " end "+ lmfao.getFinishTime());
			//System.out.println("ROFLL  "+lmfao.getUserID() + "   " + lmfao.getVmId() + "    "+lmfao.getWaitingTime());
			
			System.out.println(omg.isEmpty());
			System.out.println("ffgs  "+ tm.length +"dfsdfsa "+ um.length);
			//get the region of the dataCenter & find the userBases in that region
			for(int i=0; i < tm.length; i++) {
				
				for(int j=0; j < um.length; j++) {
					
					if(tm[i].getRegion() == um[j].getRegion()) {
						tm[i].addUser(um[j]);
					//	System.out.println("OMGERD   ");
					}
		
				}
				
			}
			
				for(int i=0; i < tm.length; i++) {
			//	for (List list: tm[i].getData()) {
				//		for (Object o: list) {
					//		System.out.println(o);
						//}
//					}
				}
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
			
			internet = sim.getInternet();
			internetChar = internet.getInternetChar();
		
				Map<CommPath, Long> trafficLevels = internetChar.getTrafficLevels();
				System.out.println("========TRAFFIC========");
				for (Long value : trafficLevels.values()){
		            //iterate over values
		            System.out.println(value);
		        }

				System.out.println("========TRAFFIC END========");
			

                for (int i = 0; i < tm.length; i++) {
                    System.out.println("-----------Prince------------------");
                    System.out.println("DATACENTER NAME "+tm[i].getName());
                    System.out.println("REQUESTS MADE "+tm[i].getRequestsMade());
                    System.out.println("REQUESTS PROCESSED "+ tm[i].getRequestsProcessed());
                  //  System.out.println("DELAY: "+tm[i].getDelay());
             //       System.out.println("DELAY "+ tm[i].getAverageDelays());
                    if(tm[i].getVmStats() != null){
                        for(Integer val: tm[i].getVmStats().values()){
                            System.out.println(val);
                        }
                    }
                    System.out.println("---------------------------------");
                }
			
			
			currTime = GridSim.clock();

		}
		 
	 }

}

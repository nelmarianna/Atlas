package atlas;

//Getters for our defined traffic thresholds
// This class will be used from the loadBalanceController to 
//decide when to change the load balancing property
public class TrafficDefinition {

	//percentage of heavy traffic threshold
	private static int heavyTrafficThreshold = 60;
	//percentage of medium traffic threshold
	private static int mediumTrafficThreshold = 40;
	//anything less is light traffic
	private static int lightTrafficThreshold = 20;
	
	public static void main(String[] args) {
		//do nothing I guess...?
		
	}
	
	public static int getHeavyThreshold(){
		return heavyTrafficThreshold;
	}
	public static int getMediumThreshold(){
		return mediumTrafficThreshold;
	}
	public static int getLightThreshold(){
		return lightTrafficThreshold;
	}
	
	public void compareThreshold(int something) {
		
		//from our profs notes:
		 //heavy >>> switch
		//light and not uniform >> switch 
		//intense in a specific region >> switch
		
		//lght and smooth >> DONT CARE
		
		
		// decide based on the traffic definition which load balancer to use
		
		/* description of load balancers in cloudAnalyst
		 * 
		 * Round Robin - tasks assigned to each process in equal portions and in circular order
		 * Throttled -  find an appropriate not allocated VM for user request
		 * ActiveVM - balances the tasks among available VM's in a way to even out the number of 
		 * active tasks at any given time on each VM
		 * 
		 */
		
		
		if(something <= lightTrafficThreshold) {
			//we need to check if its uniform
		}else if(something >= lightTrafficThreshold && something <= mediumTrafficThreshold) {
			
			//check what currently on... switch?
		}else{
			//switch 
		}
	}

}

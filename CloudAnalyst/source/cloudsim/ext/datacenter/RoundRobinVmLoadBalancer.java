package cloudsim.ext.datacenter;

import java.util.Map;

/**
 * This class implements {@link VmLoadBalancer} with a Round Robin policy.
 * 
 * @author Bhathiya Wickremasinghe
 *
 */
public class RoundRobinVmLoadBalancer extends VmLoadBalancer {
	
	private Map<Integer, VirtualMachineState> vmStatesList;
	private int currVm = -1;
	private int[] vmWeight = {1,1,2,3,3};
	private int weight =0;
	
	public RoundRobinVmLoadBalancer(Map<Integer, VirtualMachineState> vmStatesList){
		super();
		
		this.vmStatesList = vmStatesList;
	}
	
	public int getNextAvailableVm(){
		
		if(weight==0){
			currVm++;
			if (currVm >= vmStatesList.size()){
					currVm = 0;
			}
			weight = vmWeight[currVm%5];
		}
		
		
		allocatedVm(currVm);
		weight--;
		return currVm;
	}
}

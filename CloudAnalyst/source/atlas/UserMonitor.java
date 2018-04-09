package atlas;

import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.InternetCloudlet;
import cloudsim.ext.UserBase;

public class UserMonitor {
	
	private int region;
	private long reqSize;
	private String name;
	private InternetCloudlet cloudlet;
	
	public UserMonitor(UserBase userBase) {
		
		this.region = userBase.getRegion();
		//this.reqSize = userBase.getReqSize();
		this.cloudlet = userBase.getCloudlet();
		this.name = userBase.getName();
		
		// general things we can use:
		InternetCharacteristics vm = null;
				vm.getInstance();
	}
	
	public int getRegion(){
		return region;
	}

	public long getReqSize() {
	    return reqSize;
	}

	public String getName() {
	    return name;
    }
	public int getInfo(){
		return cloudlet.getVmId();
		
	}
}

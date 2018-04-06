package atlas;

import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.gui.UserBaseUIElement;

public class UserMonitor {
	
	private int region;
	private long reqSize;
	private String name;
	
	public UserMonitor(UserBaseUIElement userBase) {
		
		this.region = userBase.getRegion();
		this.reqSize = userBase.getReqSize();
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
}

package atlas;

import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.gui.UserBaseUIElement;

public class UserMonitor {
	
	protected int region;
	protected long reqSize;
	
	public UserMonitor(UserBaseUIElement userBase) {
		
		this.region = userBase.getRegion();
		this.reqSize = userBase.getReqSize();
		
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
}

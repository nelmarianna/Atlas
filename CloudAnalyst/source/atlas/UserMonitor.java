package atlas;

import cloudsim.ext.InternetCharacteristics;
import cloudsim.ext.gui.UserBaseUIElement;

public class UserMonitor {
	
	protected int region;
	
	public UserMonitor(UserBaseUIElement userBase) {
		
		this.region = userBase.getRegion();
		
		// general things we can use:
		InternetCharacteristics vm = null;
				vm.getInstance();
		
		
	}
	
	public int getRegion(){
		return region;
	}
}

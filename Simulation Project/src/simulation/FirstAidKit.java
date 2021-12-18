package simulation;

public class FirstAidKit extends medicalintervention {
	boolean someoneNearby=false;
	public FirstAidKit() {
	 Rangeofcaplbility=1;
	 
 }
	
	public void treatmentadminstraition(worker a, int simTime, factory workerfactory){
		if(!a.isCheckedFirsAid()) {
			if(this instanceof smartFirstAidkit) {
				a.setProgressionRate(a.getProgressionRate()-5);
				a.setCheckedFirsAid(true);
				a.usedAK=true;
			}
			else {
			if(workerfactory.getWlist().size()-1-workerfactory.getWlist().indexOf(a)<=(workerfactory.getDensity())) {
				for(int i=workerfactory.getWlist().indexOf(a); i<= workerfactory.getWlist().indexOf(a)-workerfactory.getDensity();i++) {
					if(workerfactory.wlist.get(i).isKnowsHowtoUseMedikit()) {
						someoneNearby=true;
						break;
					}
				}
			}
//			else {
//				for(int i=workerfactory.getWlist().indexOf(a); i<= workerfactory.getWlist().indexOf(a)+workerfactory.getDensity();i--) {
//					if(workerfactory.wlist.get(i).isKnowsHowtoUseMedikit()) {
//						someoneNearby=true;
//						break;
//			}
//				}
//			}
			if(someoneNearby) {
				a.setProgressionRate(a.getProgressionRate()-5);
				a.setCheckedFirsAid(true);
				a.usedAK=true;
				someoneNearby=false;
				}
			}
		}
		
	}
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}




	
	
}

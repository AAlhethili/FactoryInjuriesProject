package simulation;
import java.security.SecureRandom;
public class FirstAidKit extends medicalintervention {
	SecureRandom genertor = new SecureRandom();
	int checkagain=9999999;
	boolean someoneNearby=false;
	public FirstAidKit() {
	 Rangeofcaplbility=1;
	 
 }
	
	public void treatmentadminstraition(worker a,int simDay, int simTime, factory workerfactory){
		if(!a.isCheckedFirsAid()) {
			if(Rangeofcaplbility>=a.getCurrent().level) {

				checkagain=9999999;
				int worker=0;
				for(int i=1; i<= a.getPeopleAround();i++) {
					worker=genertor.nextInt(workerfactory.getWlist().size());
					while
						(worker==checkagain) {
						worker = genertor.nextInt(workerfactory.getWlist().size());
					}
					checkagain=worker;
					if(workerfactory.wlist.get(worker).isKnowsHowtoUseMedikit()) {
						someoneNearby=true;
						break;
						}
				}	
			if(someoneNearby) {
				a.setProgressionRate(a.getProgressionRate()-5);
				a.setUsedAK(true);
				someoneNearby=false;
				}
			}
		}
		a.setCheckedFirsAid(true);
		}
		
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}




	
	
}

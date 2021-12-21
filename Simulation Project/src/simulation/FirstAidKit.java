package simulation;
import java.security.SecureRandom;
public class FirstAidKit extends medicalintervention {
	SecureRandom genertor = new SecureRandom();
	int checkagain=9999999;
	boolean someoneNearby=false; //
	public FirstAidKit() {
	 
 }
	
	public void treatmentadminstraition(worker a,int simDay, int simTime, factory workerfactory){//
		if(!a.isCheckedFirsAid()&&!a.isTreatdByInfermaryRoom()) {
				checkagain=9999999;
				int worker=0;
				//gets number of people around and randomly select the same number form the factory worker list
				for(int i=1; i<= a.getPeopleAround();i++) {
					worker=genertor.nextInt(workerfactory.getWorkerList().size());
					while
						//used to not the same person twice in a row initial condition does only matter to start checking
						(worker==checkagain) {
						worker = genertor.nextInt(workerfactory.getWorkerList().size());
					}
					checkagain=worker;
					if(workerfactory.workerList.get(worker).isKnowsHowtoUseMedikit()) {//check if the person know how to use aid kit
						someoneNearby=true;
						if(workerfactory.workerList.get(worker) instanceof medicallytrainedworker) {
							a.setMedicalWorkerAvailable(true);
						}
						break;
						}
				}	
			if(someoneNearby) {//heals and confirms healing 
				a.setProgressionRate(a.getProgressionRate()-2);
				a.setUsedAidKit(true);
				workerfactory.counterTreatmentByFirstAidKit+=1;
				someoneNearby=false;
				}
			
		}
		a.setCheckedFirsAid(true);//confirms that kit was checked to not check again
		}
		
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}




	
	
}

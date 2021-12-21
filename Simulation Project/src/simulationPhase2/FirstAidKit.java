package simulationPhase2;
import java.security.SecureRandom;
public class FirstAidKit extends medicalintervention {
	SecureRandom genertor = new SecureRandom();
	int checkagain=9999999;
	boolean someoneNearby=false;
	public FirstAidKit() {
	 
 }
	
	public void treatmentadminstraition(worker a,int simDay, int simTime, factory workerfactory){
		if(!a.isCheckedFirsAid()&&!a.isTreatdByInfermaryRoom()) {
				checkagain=9999999;
				int worker=0;
				for(int i=1; i<= a.getPeopleAround();i++) {
					worker=genertor.nextInt(workerfactory.getWorkerList().size());
					while
						(worker==checkagain) {
						worker = genertor.nextInt(workerfactory.getWorkerList().size());
					}
					checkagain=worker;
					if(workerfactory.workerList.get(worker).isKnowsHowtoUseMedikit()) {
						someoneNearby=true;
						if(workerfactory.workerList.get(worker) instanceof medicallytrainedworker) {
							a.setMedicalWorkerAvailable(true);
						}
						break;
						}
				}	
			if(someoneNearby) {
				a.setProgressionRate(a.getProgressionRate()-2);
				a.setUsedAidKit(true);
				workerfactory.counterTreatmentByFirstAidKit+=1;
				someoneNearby=false;
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

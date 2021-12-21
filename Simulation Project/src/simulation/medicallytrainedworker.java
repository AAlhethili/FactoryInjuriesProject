package simulation;

import java.security.SecureRandom;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	int checkagain=9999999;
	SecureRandom generator = new SecureRandom();
	boolean someoneNearby=false;
	
	public medicallytrainedworker() {
		
	}
	public medicallytrainedworker(int id, double avg, double avgCheckUpRate) {
		super(id,avg, avgCheckUpRate);
	
		setKnowsHowtoUseMedikit(true);
	}

	@Override
	public void treatmentadminstraition(worker a,int simDay, int simTime,factory workerfactory) {
		if(!a.isCheckedMedicalWorker()&&!a.isTreatdByInfermaryRoom()) {
				checkagain=9999999;
				int worker=0;
				for(int i=1; i<= a.getPeopleAround();i++) {//gets number of people around and randomly select the same number form the factory worker list
					worker=generator.nextInt(workerfactory.getWorkerList().size());
					while(worker==checkagain) {
						worker = generator.nextInt(workerfactory.getWorkerList().size());
					}
					checkagain=worker;
					if(workerfactory.workerList.get(worker) instanceof medicallytrainedworker) {// sees if the person is medically trained
						someoneNearby=true;
						break;
						}
				}

					
			if(someoneNearby||a.isMedicalWorkerAvailable()) {//heals and confirms healing 
				a.setProgressionRate(a.getProgressionRate()-2);
				a.setCheckedMedicalWorker(someoneNearby);
				a.setTratedByMedicalWorker(true);
				workerfactory.counterTreatmentByMedicalWorker+=1;
				someoneNearby=false;
				}
			a.setWaitTime(6+simTime);//sets 6 minutes delay before reachin infirmary
		}
		a.setCheckedMedicalWorker(true);//confirms that kit was checked to not check again
		
	}

	

	}

